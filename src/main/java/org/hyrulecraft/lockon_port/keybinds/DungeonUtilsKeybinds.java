package org.hyrulecraft.lockon_port.keybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.*;

import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

import org.jetbrains.annotations.*;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DungeonUtilsKeybinds {

    public static List<LivingEntity> list = new ArrayList<>();
    public static boolean lockedOn;
    private static @Nullable LivingEntity targeted;
    private static final Predicate<LivingEntity> ENTITY_PREDICATE = entity -> entity.isAlive() && entity.isAttackable();
    private static int cycle = -1;

    // Categories (Translation keys)
    public static final String CATEGORY_DUNGEON_UTILS = "key.category.dungeon_utils.dungeon_utils";

    // Keybinds (Translation keys)
    public static final String LOCK_ON_KEY = "key.dungeon_utils.lock_on";
    public static final String SWITCH_TARGET_KEY = "key.dungeon_utils.switch_target";

    // Keybinds
    public static KeyBinding LOCK_ON = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            LOCK_ON_KEY,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_Z,
            CATEGORY_DUNGEON_UTILS
    ));
    public static KeyBinding SWITCH_TARGET = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            SWITCH_TARGET_KEY,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_TAB,
            CATEGORY_DUNGEON_UTILS
    ));

    public static void registerDungeonUtilsKeybinds() {
        registerKeyInputs();
        DungeonUtils.LOGGER.info(DungeonUtils.NAME + " has registered its keybinds.");
    }

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            ClientPlayerEntity clientPlayer = client.clientPlayer;
            if (LOCK_ON.wasPressed()) {

                if (lockedOn) {

                    leaveLockOn();

                } else {

                    attemptEnterLockOn(clientPlayer);

                }

            }
            if (SWITCH_TARGET.wasPressed() && lockedOn) {

                tabToNextEnemy(clientPlayer);

            }
            tickLockedOn();
        });
    }

    public static boolean handleKeyPress(PlayerEntity player, double d2, double d3) {
        if (player != null && !MinecraftClient.getInstance().isPaused()) {
            if (targeted != null) {
                Vec3d targetPos = targeted.getPos().add(0,targeted.getEyeHeight(targeted.getPose()),0);
                Vec3d targetVec = targetPos.subtract(player.getPos().add(0, player.getEyeHeight(player.getPose()),0)).normalize();
                double targetAngleX = MathHelper.wrapDegrees(Math.atan2(-targetVec.x, targetVec.z) * 180 / Math.PI);
                double targetAngleY = Math.atan2(targetVec.y , targetVec.horizontalLength()) * 180 / Math.PI;
                double xRot = MathHelper.wrapDegrees(player.getPitch());
                double yRot = MathHelper.wrapDegrees(player.getYaw());
                double toTurnX = MathHelper.wrapDegrees(yRot - targetAngleX);
                double toTurnY = MathHelper.wrapDegrees(xRot + targetAngleY);

                player.changeLookDirection(-toTurnX,-toTurnY);
                return true;
            }
        }
        return false;
    }

    private static void tickLockedOn() {
        list.removeIf(livingEntity -> !livingEntity.isAlive());
        if (targeted != null && !targeted.isAlive()) {
            targeted = null;
            lockedOn = false;
        }
    }

    public static @Nullable LivingEntity findNearby(@NotNull PlayerEntity player) {

        int r = 16;

        final TargetPredicate ENEMY_CONDITION = TargetPredicate.createAttackable().setBaseMaxDistance(r).setPredicate(ENTITY_PREDICATE);

        List<LivingEntity> entities = player.getWorld()
                .getTargets(LivingEntity.class, ENEMY_CONDITION, player, player.getBoundingBox().expand(r)).stream().filter(player::canSee).toList();
        if (lockedOn) {
            cycle++;
            for (LivingEntity entity : entities) {
                if (!list.contains(entity)) {
                    list.add(entity);
                    return entity;
                }
            }

            //cycle existing entity
            if (cycle >= list.size()) {
                cycle = 0;
            }
            return list.get(cycle);
        } else {
            if (!entities.isEmpty()) {
                LivingEntity first = entities.get(0);
                list.add(first);
                return entities.get(0);
            } else {
                return null;
            }
        }
    }

    private static void leaveLockOn() {
        targeted = null;
        lockedOn = false;
        list.clear();
    }

    private static void attemptEnterLockOn(PlayerEntity player) {
        tabToNextEnemy(player);
        if (targeted != null) {
            lockedOn = true;
        }
    }

    private static void tabToNextEnemy(PlayerEntity player) {
        targeted = findNearby(player);
    }
}