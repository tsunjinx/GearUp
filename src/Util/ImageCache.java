package Util;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public final class ImageCache {

    private static final Map<String, BufferedImage> ORIGINAL_CACHE = new ConcurrentHashMap<>();
    private static final Map<String, BufferedImage> SCALED_CACHE = new ConcurrentHashMap<>();

    private static BufferedImage loadOriginal(String path) {
        return ORIGINAL_CACHE.computeIfAbsent(path, p -> {
            try (InputStream is = ImageCache.class.getResourceAsStream(p)) {
                if (is == null) {
                    throw new IllegalArgumentException("Resource not found: " + p);
                }
                return ImageIO.read(is);
            } catch (IOException ex) {
                throw new RuntimeException("Failed to load image: " + p, ex);
            }
        });
    }

    private static String key(String path, int w, int h) {
        return path + "#" + w + "x" + h;
    }

    /**
     * Returns a cached ImageIcon scaled to the requested dimensions. Subsequent
     * calls with the same parameters return the already scaled image, greatly
     * reducing GUI construction time.
     *
     * @param resourcePath class-path resource (e.g. "/images/logo.png")
     * @param width        target width (pixels); if &lt;=0 original width is used
     * @param height       target height (pixels); if &lt;=0 original height is used
     * @return scaled {@link ImageIcon}
     */
    public static ImageIcon getScaledIcon(String resourcePath, int width, int height) {
        String cacheKey = key(resourcePath, width, height);
        BufferedImage scaled = SCALED_CACHE.get(cacheKey);
        if (scaled != null) {
            return new ImageIcon(scaled);
        }

        BufferedImage original = loadOriginal(resourcePath);
        if (width <= 0) width = original.getWidth();
        if (height <= 0) height = original.getHeight();

        scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaled.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();

        SCALED_CACHE.put(cacheKey, scaled);
        return new ImageIcon(scaled);
    }

    private ImageCache() {
    }
}