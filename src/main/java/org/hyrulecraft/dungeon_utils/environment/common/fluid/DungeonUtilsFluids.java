package org.hyrulecraft.dungeon_utils.environment.common.fluid;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.*;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

public class DungeonUtilsFluids {

    public static void registerDungeonUtilsFluids() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its fluids.");
    }

    public static final FlowableFluid STILL_SPRING_WATER = Registry.register(RegistryTypes.FLUID,
            new Identifier(DungeonUtils.MOD_ID, "spring_water"), new SpringWaterFluid.Still());

    public static final FlowableFluid FLOWING_SPRING_WATER = Registry.register(RegistryTypes.FLUID,
            new Identifier(DungeonUtils.MOD_ID, "flowing_spring_water"), new SpringWaterFluid.Flowing());

    public static final Block SPRING_WATER_BLOCK = Registry.register(RegistryTypes.BLOCK, new Identifier(DungeonUtils.MOD_ID,
            "spring_water_block"), new FluidBlock(DungeonUtilsFluids.STILL_SPRING_WATER, FabricBlockSettings.copyOf(Blocks.WATER)
            .replaceable().liquid()));

    public static final Item SPRING_WATER_BUCKET = Registry.register(RegistryTypes.ITEM, new Identifier(DungeonUtils.MOD_ID,
            "spring_water_bucket"), new BucketItem(DungeonUtilsFluids.STILL_SPRING_WATER,
            new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));
}