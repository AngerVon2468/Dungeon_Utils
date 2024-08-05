package org.hyrulecraft.dungeon_utils.util;

import net.minecraft.fluid.Fluid;

import org.jetbrains.annotations.*;

public class FluidRendererCollector {

    private final Fluid stillFluid;

    private final Fluid flowingFluid;

    private final Integer colour;

    @Contract(value = "_, _, _ -> new", pure = true)
    public static @NotNull FluidRendererCollector of(Fluid stillFluid, Fluid flowingFluid, Integer colour) {
        return new FluidRendererCollector(stillFluid, flowingFluid, colour);
    }

    private FluidRendererCollector(Fluid stillFluid, Fluid flowingFluid, Integer colour) {
        this.stillFluid = stillFluid;
        this.flowingFluid = flowingFluid;
        this.colour = colour;
    }

    public Fluid getStillFluid() {
        return this.stillFluid;
    }

    public Fluid getFlowingFluid() {
        return this.flowingFluid;
    }

    public Integer getColour() {
        return this.colour;
    }
}