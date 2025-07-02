package Repository;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * Asynchronous operations utility to prevent UI freezing during database operations.
 * Provides clean separation between UI thread and background database operations.
 * 
 * Performance improvements:
 * - Eliminates UI freezes during database operations
 * - Better user experience with responsive interface
 * - Proper error handling for async operations
 * - Configurable thread pool for optimal resource usage
 * 
 * @author Performance Optimization
 */
public class AsyncOperations {
    private static final Logger logger = Logger.getLogger(AsyncOperations.class.getName());
    
    private static AsyncOperations instance;
    
    // Thread pool configuration
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 10;
    private static final long KEEP_ALIVE_TIME = 60L; // seconds
    
    private final ExecutorService executorService;
    
    private AsyncOperations() {
        // Create optimized thread pool for database operations
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadFactory() {
                private int threadNumber = 0;
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r, "AsyncDB-" + (++threadNumber));
                    t.setDaemon(true);
                    t.setPriority(Thread.NORM_PRIORITY);
                    return t;
                }
            }
        );
        
        // Allow core threads to timeout when idle
        threadPool.allowCoreThreadTimeOut(true);
        
        this.executorService = threadPool;
        
        logger.info("AsyncOperations initialized with thread pool: core=" + CORE_POOL_SIZE + 
                   ", max=" + MAX_POOL_SIZE + ", keepAlive=" + KEEP_ALIVE_TIME + "s");
    }
    
    /**
     * Get singleton instance
     */
    public static synchronized AsyncOperations getInstance() {
        if (instance == null) {
            instance = new AsyncOperations();
        }
        return instance;
    }
    
    /**
     * Execute database operation asynchronously with success and error callbacks
     * 
     * @param <T> Result type
     * @param operation Database operation to execute in background
     * @param onSuccess Callback executed on UI thread when operation succeeds
     * @param onError Callback executed on UI thread when operation fails
     * @return Future for the operation
     */
    public <T> Future<T> executeAsync(Supplier<T> operation, 
                                     Consumer<T> onSuccess, 
                                     Consumer<Exception> onError) {
        
        return executorService.submit(() -> {
            try {
                logger.fine("Starting async database operation");
                long startTime = System.currentTimeMillis();
                
                T result = operation.get();
                
                long endTime = System.currentTimeMillis();
                logger.fine("Async operation completed in " + (endTime - startTime) + "ms");
                
                // Execute success callback on UI thread
                if (onSuccess != null) {
                    SwingUtilities.invokeLater(() -> {
                        try {
                            onSuccess.accept(result);
                        } catch (Exception e) {
                            logger.log(Level.WARNING, "Error in success callback", e);
                        }
                    });
                }
                
                return result;
                
            } catch (Exception e) {
                logger.log(Level.WARNING, "Async operation failed", e);
                
                // Execute error callback on UI thread
                if (onError != null) {
                    SwingUtilities.invokeLater(() -> {
                        try {
                            onError.accept(e);
                        } catch (Exception callbackError) {
                            logger.log(Level.SEVERE, "Error in error callback", callbackError);
                        }
                    });
                }
                
                throw new RuntimeException(e);
            }
        });
    }
    
    /**
     * Execute database operation asynchronously with only success callback
     */
    public <T> Future<T> executeAsync(Supplier<T> operation, Consumer<T> onSuccess) {
        return executeAsync(operation, onSuccess, this::defaultErrorHandler);
    }
    
    /**
     * Execute database operation asynchronously without callbacks
     */
    public <T> Future<T> executeAsync(Supplier<T> operation) {
        return executeAsync(operation, null, this::defaultErrorHandler);
    }
    
    /**
     * Execute void database operation asynchronously
     */
    public Future<Void> executeAsyncVoid(Runnable operation, 
                                        Runnable onSuccess, 
                                        Consumer<Exception> onError) {
        
        return executeAsync(() -> {
            operation.run();
            return null;
        }, (result) -> {
            if (onSuccess != null) {
                onSuccess.run();
            }
        }, onError);
    }
    
    /**
     * Execute void database operation asynchronously with only success callback
     */
    public Future<Void> executeAsyncVoid(Runnable operation, Runnable onSuccess) {
        return executeAsyncVoid(operation, onSuccess, this::defaultErrorHandler);
    }
    
    /**
     * Execute void database operation asynchronously without callbacks
     */
    public Future<Void> executeAsyncVoid(Runnable operation) {
        return executeAsyncVoid(operation, null, this::defaultErrorHandler);
    }
    
    /**
     * Execute operation with loading indicator
     */
    public <T> Future<T> executeAsyncWithLoading(Supplier<T> operation,
                                                Consumer<T> onSuccess,
                                                Consumer<Exception> onError,
                                                Runnable showLoading,
                                                Runnable hideLoading) {
        
        // Show loading indicator on UI thread
        if (showLoading != null) {
            SwingUtilities.invokeLater(showLoading);
        }
        
        return executeAsync(operation, 
            (result) -> {
                // Hide loading and execute success callback
                if (hideLoading != null) {
                    hideLoading.run();
                }
                if (onSuccess != null) {
                    onSuccess.accept(result);
                }
            },
            (error) -> {
                // Hide loading and execute error callback
                if (hideLoading != null) {
                    hideLoading.run();
                }
                if (onError != null) {
                    onError.accept(error);
                }
            }
        );
    }
    
    /**
     * Execute multiple operations in parallel and combine results
     */
    public <T> Future<T[]> executeParallel(Supplier<T>[] operations, 
                                          Consumer<T[]> onSuccess, 
                                          Consumer<Exception> onError) {
        
        return executorService.submit(() -> {
            try {
                logger.fine("Starting " + operations.length + " parallel operations");
                long startTime = System.currentTimeMillis();
                
                @SuppressWarnings("unchecked")
                CompletableFuture<T>[] futures = new CompletableFuture[operations.length];
                
                // Start all operations
                for (int i = 0; i < operations.length; i++) {
                    final int index = i;
                    futures[i] = CompletableFuture.supplyAsync(operations[index], executorService);
                }
                
                // Wait for all to complete
                CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
                allOf.get(30, TimeUnit.SECONDS); // 30 second timeout
                
                // Collect results
                @SuppressWarnings("unchecked")
                T[] results = (T[]) new Object[operations.length];
                for (int i = 0; i < futures.length; i++) {
                    results[i] = futures[i].get();
                }
                
                long endTime = System.currentTimeMillis();
                logger.fine("Parallel operations completed in " + (endTime - startTime) + "ms");
                
                // Execute success callback on UI thread
                if (onSuccess != null) {
                    SwingUtilities.invokeLater(() -> onSuccess.accept(results));
                }
                
                return results;
                
            } catch (Exception e) {
                logger.log(Level.WARNING, "Parallel operations failed", e);
                
                // Execute error callback on UI thread
                if (onError != null) {
                    SwingUtilities.invokeLater(() -> onError.accept(e));
                }
                
                throw new RuntimeException(e);
            }
        });
    }
    
    /**
     * Execute operation with timeout
     */
    public <T> Future<T> executeAsyncWithTimeout(Supplier<T> operation,
                                                Consumer<T> onSuccess,
                                                Consumer<Exception> onError,
                                                long timeoutSeconds) {
        
        Future<T> future = executeAsync(operation, onSuccess, onError);
        
        // Schedule timeout task using separate scheduler
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            if (!future.isDone()) {
                future.cancel(true);
                SwingUtilities.invokeLater(() -> {
                    if (onError != null) {
                        onError.accept(new TimeoutException("Operation timed out after " + timeoutSeconds + " seconds"));
                    }
                });
            }
            scheduler.shutdown();
        }, timeoutSeconds, TimeUnit.SECONDS);
        
        return future;
    }
    
    /**
     * Default error handler that logs the error
     */
    private void defaultErrorHandler(Exception e) {
        logger.log(Level.WARNING, "Async operation error (using default handler)", e);
        // Could show a generic error dialog here if needed
    }
    
    /**
     * Get thread pool statistics for monitoring
     */
    public String getThreadPoolStats() {
        if (executorService instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor tpe = (ThreadPoolExecutor) executorService;
            return String.format("ThreadPool Stats - Active: %d, Pool: %d, Queue: %d, Completed: %d",
                    tpe.getActiveCount(),
                    tpe.getPoolSize(),
                    tpe.getQueue().size(),
                    tpe.getCompletedTaskCount());
        }
        return "ThreadPool Stats - Not available";
    }
    
    /**
     * Shutdown the async operations service
     */
    public void shutdown() {
        logger.info("Shutting down AsyncOperations...");
        
        executorService.shutdown();
        
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                logger.warning("Executor did not terminate gracefully, forcing shutdown");
                executorService.shutdownNow();
                
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    logger.severe("Executor did not terminate after forced shutdown");
                }
            }
        } catch (InterruptedException e) {
            logger.warning("Shutdown interrupted, forcing immediate shutdown");
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        
        logger.info("AsyncOperations shutdown complete");
    }
    
    /**
     * Check if service is shutdown
     */
    public boolean isShutdown() {
        return executorService.isShutdown();
    }
    
    /**
     * Helper class for common async operation patterns
     */
    public static class AsyncPatterns {
        
        /**
         * Load data asynchronously and populate UI table
         */
        public static <T> Future<T> loadDataForTable(Supplier<T> dataLoader,
                                                    Consumer<T> tablePopulator,
                                                    Runnable showLoading,
                                                    Runnable hideLoading) {
            
            return getInstance().executeAsyncWithLoading(
                dataLoader,
                tablePopulator,
                (error) -> {
                    logger.log(Level.SEVERE, "Failed to load table data", error);
                    // Could show error message to user here
                },
                showLoading,
                hideLoading
            );
        }
        
        /**
         * Save data asynchronously with user feedback
         */
        public static Future<Integer> saveDataAsync(Supplier<Integer> saveOperation,
                                                   Runnable onSuccess,
                                                   Consumer<Exception> onError) {
            
            return getInstance().executeAsync(
                saveOperation,
                (rowsAffected) -> {
                    if (rowsAffected > 0) {
                        onSuccess.run();
                    } else {
                        logger.warning("Save operation affected 0 rows");
                    }
                },
                onError
            );
        }
    }
}