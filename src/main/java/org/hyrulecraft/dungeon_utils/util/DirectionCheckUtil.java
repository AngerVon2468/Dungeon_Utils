package org.hyrulecraft.dungeon_utils.util;

public class DirectionCheckUtil {

    public static boolean facingNorth(double pX, double cX, double pZ, double cZ) {
        return (pX == cX && pZ >= cZ);
    }

    public static boolean facingSouth(double pX, double cX, double pZ, double cZ) {
        return (pX == cX && pZ <= cZ);
    }

    public static boolean facingEast(double pX, double cX, double pZ, double cZ) {
        return (pZ == cZ && pX <= cX);
    }

    public static boolean facingWest(double pX, double cX, double pZ, double cZ) {
        return (pZ == cZ && pX >= cX);
    }
}