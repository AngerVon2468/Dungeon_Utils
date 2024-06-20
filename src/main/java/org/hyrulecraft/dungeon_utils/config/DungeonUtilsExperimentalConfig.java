package org.hyrulecraft.dungeon_utils.config;

import com.google.gson.*;

import org.hyrulecraft.dungeon_utils.DungeonUtils;

import java.io.*;

import static org.hyrulecraft.dungeon_utils.config.DungeonUtilsExperimentalConfig.ConfigOutput.isWiiUBased;

public class DungeonUtilsExperimentalConfig {

    public static final String dungeonUtilsPath = System.getProperty("user.home") + System.getProperty("file.separator") + "." + DungeonUtils.NAME;

    public static final File dungeonUtilsConfig = new File(dungeonUtilsPath, "config.json");

    public static final File dungeonUtilsFolder = new File(dungeonUtilsPath);

    public static Object dungeonUtilsObject;

    public static final JsonObject dungeonUtilsAsJsonObject = (JsonObject) dungeonUtilsObject;

    static {
        try {
            dungeonUtilsObject = JsonParser.parseReader(new FileReader(dungeonUtilsConfig));
        } catch (FileNotFoundException fileNotFoundException) {
            DungeonUtils.LOGGER.info(fileNotFoundException.toString());
        }
    }

    public static class ConfigOutput {

        /**
         * Use {@link ConfigValues#isWiiuBased} to get the boolean value of this option. <br>
         * <strong>DO NOT CALL ANYTHING FROM THIS SUBCLASS</strong>
         */
        public static String isWiiUBased() {

            return dungeonUtilsAsJsonObject == null ? "" : dungeonUtilsAsJsonObject.get("isWiiUBased").toString();
        }
    }

    public static void experimentalConfigTesting() {


        if (!dungeonUtilsFolder.exists()){
            dungeonUtilsFolder.mkdirs();
        }
        try {
            if (dungeonUtilsConfig.createNewFile()) {
                DungeonUtils.LOGGER.info("File created: " + dungeonUtilsConfig.getName());
            } else {
                DungeonUtils.LOGGER.info("File already exists.");
            }
            try {
                FileWriter dungeonUtilsConfigWriter = new FileWriter(dungeonUtilsConfig);

                dungeonUtilsConfigWriter.write("{" + System.getProperty("line.separator"));
                if (isWiiUBased().contains("true")) {

                    dungeonUtilsConfigWriter.write("    \"isWiiUBased\": true" + System.getProperty("line.separator"));

                } else if (isWiiUBased().contains("false")) {

                    dungeonUtilsConfigWriter.write("    \"isWiiUBased\": false" + System.getProperty("line.separator"));

                } else {

                    dungeonUtilsConfigWriter.write("    \"isWiiUBased\": true" + System.getProperty("line.separator"));

                }
                dungeonUtilsConfigWriter.write("}");
                dungeonUtilsConfigWriter.close();
            } catch (IOException ioException) {
                DungeonUtils.LOGGER.info(ioException.toString());
            }

        } catch (IOException ioException) {
            DungeonUtils.LOGGER.info(ioException.toString());
        }
    }

    /**
     * The actual return values of the config's options. <br>
     * Only to be called from outside of this class. <br>
     * Only use values from here, nothing else from this class should be called, <strong>EVER.</strong>
     */
    public static class ConfigValues {

        public static boolean isWiiuBased = isWiiUBased().contains("true");
    }
}