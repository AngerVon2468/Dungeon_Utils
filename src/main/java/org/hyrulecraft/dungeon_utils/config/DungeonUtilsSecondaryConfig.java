package org.hyrulecraft.dungeon_utils.config;

import com.google.gson.*;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

import java.io.*;

public class DungeonUtilsSecondaryConfig {

    public static void main(String[] args) {
        genConfig();
        DungeonUtils.LOGGER.info(getWiiUIsBased() + "");
    }

    public static String dungeonUtilsPath = System.getProperty("user.home") + System.getProperty("file.separator") + "." + DungeonUtils.NAME;

    public static File dungeonUtilsConfig = new File(dungeonUtilsPath, "config.json");

    public static File dungeonUtilsFolder = new File(dungeonUtilsPath);

    public static void genConfig() {

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
                if (getWiiUIsBased()) {

                    dungeonUtilsConfigWriter.write("    \"isWiiUBased\": true" + System.getProperty("line.separator"));
                    DungeonUtils.LOGGER.info("Case 1");

                } else if (!getWiiUIsBased()) {

                    dungeonUtilsConfigWriter.write("    \"isWiiUBased\": false" + System.getProperty("line.separator"));
                    DungeonUtils.LOGGER.info("Case 2");

                } else {

                    dungeonUtilsConfigWriter.write("    \"isWiiUBased\": true" + System.getProperty("line.separator"));
                    DungeonUtils.LOGGER.info("Case 3");

                }
                dungeonUtilsConfigWriter.write("}");
                dungeonUtilsConfigWriter.close();
            } catch (IOException ioException) {
                DungeonUtils.LOGGER.info(ioException.toString());
            }

        } catch (IOException ioException) {
            DungeonUtils.LOGGER.info(ioException.toString());
        }
        DungeonUtils.LOGGER.info(getWiiUIsBased() + "");

    }

    public static void setIsWiiUBased(boolean value) {

        try {

            FileWriter dungeonUtilsConfigWriter = new FileWriter(dungeonUtilsConfig);
            dungeonUtilsConfigWriter.write("{" + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("    \"isWiiUBased\": " + value + System.getProperty("line.separator"));
            dungeonUtilsConfigWriter.write("}");
            dungeonUtilsConfigWriter.close();

        } catch (IOException ioException) {

            DungeonUtils.LOGGER.error(ioException.toString());

        }

    }

    public static BufferedReader bufferedReader;

    public static Gson gson = new Gson();

    static {
        try {
            bufferedReader = new BufferedReader(new FileReader(dungeonUtilsConfig));
        } catch (FileNotFoundException fileNotFoundException) {
            DungeonUtils.LOGGER.error(fileNotFoundException.toString());
        }

    }

    public static Object json;
    public static JsonElement jsonTree;

    static {

        if (dungeonUtilsConfig.exists()) {

            json = gson.fromJson(bufferedReader, Object.class);
            if (json != null) {

                jsonTree = JsonParser.parseString(json.toString());

            }

        }

    }

    public static JsonObject jsonObject;

    static {

        if (jsonTree != null && jsonTree.isJsonObject()) {
            jsonObject = jsonTree.getAsJsonObject();
        }

    }

    public static boolean getWiiUIsBased() {

        return jsonTree == null ? true : jsonObject.get("isWiiUBased").getAsBoolean();
    }
}