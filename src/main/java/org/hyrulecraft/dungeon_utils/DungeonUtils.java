package org.hyrulecraft.dungeon_utils;

import com.google.gson.*;

import eu.midnightdust.lib.config.MidnightConfig;

import net.fabricmc.api.ModInitializer;

import org.hyrulecraft.dungeon_utils.config.DungeonUtilsConfig;
import org.hyrulecraft.dungeon_utils.entity.DungeonUtilsEntities;
import org.hyrulecraft.dungeon_utils.item.DungeonUtilsItems;
import org.hyrulecraft.dungeon_utils.block.DungeonUtilsBlocks;
import org.hyrulecraft.dungeon_utils.itemgroup.DungeonUtilsItemGroups;
import org.hyrulecraft.dungeon_utils.sound.DungeonUtilsSounds;
import org.hyrulecraft.dungeon_utils.tags.DungeonUtilsTags;
import org.hyrulecraft.dungeon_utils.util.UtilCollector;

import org.slf4j.*;

import java.io.*;

// TODO: Fix all wide skin textures.
public class DungeonUtils implements ModInitializer {

    public static final String MOD_ID = "dungeon_utils";

    public static final String NAME = "Dungeon Utils";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {

        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its main class.");

        // Initializing classes.
        DungeonUtilsItemGroups.registerDungeonUtilsItemGroups();
        DungeonUtilsItems.registerModItems();
        DungeonUtilsBlocks.registerModBlocks();
        DungeonUtilsSounds.registerDungeonUtilsSounds();
        DungeonUtilsTags.registerDungeonUtilsTags();
        UtilCollector.registerAllUtilClasses();
        DungeonUtilsEntities.registerDungeonUtilsEntities();

        // Config.
        MidnightConfig.init(DungeonUtils.MOD_ID, DungeonUtilsConfig.class);

        String path = System.getProperty("user.home") + System.getProperty("file.separator") + "." + NAME;
        File dungeonUtils = new File(path);
        if (!dungeonUtils.exists()){
            dungeonUtils.mkdirs();
        }

        try {
            File dungeonUtilsConfig = new File(path, "config.json");
            if (dungeonUtilsConfig.createNewFile()) {
                System.out.println("File created: " + dungeonUtilsConfig.getName());
            } else {
                System.out.println("File already exists.");
            }
            try {
                FileWriter dungeonUtilsConfigWriter = new FileWriter(dungeonUtilsConfig);
                dungeonUtilsConfigWriter.write("{" + System.getProperty("line.separator"));
                dungeonUtilsConfigWriter.write("    \"WiiU\": \"good\"" + System.getProperty("line.separator"));
                dungeonUtilsConfigWriter.write("}");
                dungeonUtilsConfigWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Object dungeonUtilsObject = JsonParser.parseReader(new FileReader(dungeonUtilsConfig));

            JsonObject dungeonUtilsAsJsonObject = (JsonObject) dungeonUtilsObject;

            String WiiU = dungeonUtilsAsJsonObject.get("WiiU").toString();

            LOGGER.info("WiiU: " + WiiU);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}