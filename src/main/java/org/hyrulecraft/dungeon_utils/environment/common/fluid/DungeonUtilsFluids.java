package org.hyrulecraft.dungeon_utils.environment.common.fluid;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

import org.jetbrains.annotations.NotNull;

public class DungeonUtilsFluids {

    public static boolean isStateOfSpringWater(@NotNull World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        FluidState fluidState = world.getFluidState(pos);
        return (fluidState.isOf(DungeonUtilsFluids.STILL_SPRING_WATER) || fluidState.isOf(DungeonUtilsFluids.FLOWING_SPRING_WATER)) || blockState.isOf(DungeonUtilsFluids.SPRING_WATER_BLOCK);
    }

    public static boolean isStateOfHotSpringWater(@NotNull World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        FluidState fluidState = world.getFluidState(pos);
        return (fluidState.isOf(DungeonUtilsFluids.STILL_HOT_SPRING_WATER) || fluidState.isOf(DungeonUtilsFluids.FLOWING_HOT_SPRING_WATER)) || blockState.isOf(DungeonUtilsFluids.HOT_SPRING_WATER_BLOCK);
    }

    public static void registerDungeonUtilsFluids() {
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its fluids.");
    }

    public static <T extends Fluid> T registerFluid(String name, T fluid) {
        return Registry.register(RegistryTypes.FLUID, new Identifier(DungeonUtils.MOD_ID, name), fluid);
    }

    public static <T extends Block> T registerBlock(String name, T block) {
        return Registry.register(RegistryTypes.BLOCK, new Identifier(DungeonUtils.MOD_ID, name), block);
    }

    public static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(RegistryTypes.ITEM, new Identifier(DungeonUtils.MOD_ID, name), item);
    }

    public static final FlowableFluid STILL_SPRING_WATER = registerFluid("spring_water", new SpringWaterFluid.Still());

    public static final FlowableFluid STILL_HOT_SPRING_WATER = registerFluid("hot_spring_water", new HotSpringWaterFluid.Still());

    public static final FlowableFluid FLOWING_SPRING_WATER = registerFluid("flowing_spring_water", new SpringWaterFluid.Flowing());

    public static final FlowableFluid FLOWING_HOT_SPRING_WATER = registerFluid("flowing_hot_spring_water", new HotSpringWaterFluid.Flowing());

    public static final Block SPRING_WATER_BLOCK = registerBlock("spring_water_block",
            new FluidBlock(DungeonUtilsFluids.STILL_SPRING_WATER, FabricBlockSettings.copyOf(Blocks.WATER).replaceable().liquid())
    );

    public static final Block HOT_SPRING_WATER_BLOCK = registerBlock("hot_spring_water_block",
            new FluidBlock(DungeonUtilsFluids.STILL_HOT_SPRING_WATER, FabricBlockSettings.copyOf(Blocks.WATER).replaceable().liquid())
    );

    public static final Item SPRING_WATER_BOTTLE = registerItem("spring_water_bottle",
            new BucketItem(DungeonUtilsFluids.STILL_SPRING_WATER, new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).maxCount(1))
    );

    public static final Item HOT_SPRING_WATER_BOTTLE = registerItem("hot_spring_water_bottle",
            new BucketItem(DungeonUtilsFluids.STILL_HOT_SPRING_WATER, new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).maxCount(1))
    );
}