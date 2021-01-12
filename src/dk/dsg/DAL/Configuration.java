package dk.dsg.DAL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Configuration {

    protected HashMap<String, String> configValues;

    /***
     * Load all the information from a configuration file
     * and insert all the information into a HashMap. Will be
     * used to store database-information safer than a plain-text file
     * @see HashMap
     * @see java.io.File
     * @see Paths
     */
    public Configuration() {

        configValues = new HashMap<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/dk/dsg/config.csv"), StandardCharsets.UTF_8);
            for (String line : lines) {
                String[] values = line.split(",");
                configValues.put(values[0], values[1]);
            }
        } catch (IOException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }
    }

}
