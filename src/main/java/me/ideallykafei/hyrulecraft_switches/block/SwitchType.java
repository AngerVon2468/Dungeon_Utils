package me.ideallykafei.hyrulecraft_switches.block;

import net.minecraft.util.StringIdentifiable;

public enum SwitchType implements StringIdentifiable {
    YELLOW("YELLOW"),
    RUSTY("RUSTY"),
    BLUE("BLUE"),
    // Add more switch types here as needed
    ;

    private final String name;

    SwitchType(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}