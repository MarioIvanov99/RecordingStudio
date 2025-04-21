package com.studio.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// The purpose of this class is to simulate loading data from an external source
public class ConfigLoader {
    private static final String CONFIG_FILE = "config.txt";
    private static final Map<String, String> configValues = new HashMap<>();

    static {
        loadConfig();
    }

    private static void loadConfig() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(CONFIG_FILE));
            for (String line : lines) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    configValues.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration file: " + e.getMessage());
        }
    }

    public static BigDecimal getBigDecimal(String key) {
        String value = configValues.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing config value for: " + key);
        }
        return new BigDecimal(value);
    }
}
