package org.hyrulecraft.dungeon_utils.util;

public class DirectionCheckUtil {

    public static boolean caseNorth(double pX, double cX, double pZ, double cZ) {
        return (pX == cX && pZ >= cZ);
    }

    public static boolean caseSouth(double pX, double cX, double pZ, double cZ) {
        return (pX == cX && pZ <= cZ);
    }

    public static boolean caseEast(double pX, double cX, double pZ, double cZ) {
        return (pZ == cZ && pX <= cX);
    }

    public static boolean caseWest(double pX, double cX, double pZ, double cZ) {
        return (pZ == cZ && pX >= cX);
    }
}