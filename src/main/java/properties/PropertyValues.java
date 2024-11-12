package properties;

import java.io.*;
import java.util.Properties;

/**
 * @author paolo
 */
public class PropertyValues {

    /**
     * all the stored values
     */
    public static String cache_path;
    public static String dump_path = "dump/";
    public static String sprite_path;
    /*
     * random
     */
    static InputStream input;


    public static void loadValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            input = new FileInputStream("config.properties");

            if (input != null) {
                prop.load(input);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            System.out.println(prop.getProperty("cache_path"));
            cache_path = prop.getProperty("cache_path");
            dump_path = prop.getProperty("dump_path");
            sprite_path = prop.getProperty("sprite_path");

            if (cache_path == null || cache_path.isEmpty()) {
                cache_path = "data\\export";
                prop.setProperty("cache_path", cache_path);
                saveProperties(prop);
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            input.close();
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
        if (!path.endsWith(File.separator))
            path += File.separator;

        cache_path = path;
        dump_path = cache_path + "dumps" + File.separator + "cache" + File.separator;
        sprite_path = cache_path + "dumps" + File.separator + "sprites" + File.separator;
    }
}
