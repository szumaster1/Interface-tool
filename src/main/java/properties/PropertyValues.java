package properties;

import java.io.*;
import java.util.Properties;

/**
 * @author paolo
 */
public class PropertyValues {

    /**
     * All the stored values
     */
    public static String cache_path;
    public static String dump_path = "data\\export\\dumps\\cache\\";
    public static String sprite_path = "data\\export\\dumps\\sprites\\";

    static InputStream input;

    public static void loadValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            input = new FileInputStream(propFileName);

            if (input != null) {
                prop.load(input);
            } else {
                throw new FileNotFoundException("Config file '" + propFileName + "' not found in the classpath.");
            }

            cache_path = prop.getProperty("cache_path");
            dump_path = prop.getProperty("dump_path");
            sprite_path = prop.getProperty("sprite_path");

            if (cache_path == null || cache_path.isEmpty()) {
                cache_path = "data\\export\\cache\\";
                prop.setProperty("cache_path", cache_path);
            }

            if (dump_path == null || dump_path.isEmpty()) {
                dump_path = "data\\export\\dumps\\cache\\";
                prop.setProperty("dump_path", dump_path);
            }

            if (sprite_path == null || sprite_path.isEmpty()) {
                sprite_path = "data\\export\\dumps\\sprites\\";
                prop.setProperty("sprite_path", sprite_path);
            }
            saveProperties(prop);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    private static void saveProperties(Properties prop) {
        try (OutputStream output = new FileOutputStream("config.properties")) {
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void setCachePath(String path) {
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }

        cache_path = path;
        dump_path = cache_path + "dumps" + File.separator + "cache" + File.separator;
        sprite_path = cache_path + "dumps" + File.separator + "sprites" + File.separator;
    }
}
