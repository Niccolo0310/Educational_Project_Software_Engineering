package ch.supsi.mavendemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe dedicata al caricamento delle proprietà di configurazione.
 */
public class ConfigurationLoader {

    private ConfigurationLoader() {
        // Evita istanziazione
    }

    public static Properties loadConfig(String filePath) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        }
        return properties;
    }
}