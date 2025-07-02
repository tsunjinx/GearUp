/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBCon;
import java.sql.*;
/**
 *
 * @author PonYanki
 */
public class DbConnection {
    public static final String HOSTNAME = "localhost";
    public static final String PORT = "1433";
    public static final String DBNAME = "DA_final";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "1";

    // Save a single shared connection to avoid expensive connection creation on every call
    private static Connection sharedConnection;

    static {
        try {
            // Load driver once during class initialization
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            // If the driver cannot be loaded the application cannot function – re-throw as unchecked
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Lazily create (if necessary) and return a shared {@link Connection} instance.
     * <p>
     * Creating a JDBC connection is an expensive operation. Reusing the same
     * connection for the lifetime of the application can dramatically reduce
     * application start-up times and the latency of every repository method
     * that needs DB access. In low-concurrency desktop applications this simple
     * singleton approach is usually sufficient and avoids introducing an
     * external connection-pool dependency.
     * </p>
     */
    public static synchronized Connection getConnection() {
        try {
            if (sharedConnection == null || sharedConnection.isClosed()) {
                String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                        + "databaseName=" + DBNAME + ";encrypt=true;trustServerCertificate=true;";
                // Create the physical connection only once
                sharedConnection = DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
            }
            return sharedConnection;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    /**
     * Gracefully close the shared connection when the application terminates.
     */
    public static synchronized void close() {
        if (sharedConnection != null) {
            try {
                sharedConnection.close();
            } catch (SQLException ignore) {
            } finally {
                sharedConnection = null;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
