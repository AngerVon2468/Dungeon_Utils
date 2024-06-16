package org.hyrulecraft.dungeon_utils.util.event;

import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;

public class ClientSendMessageCallbacks {

    public static void onSendClientMessage() {
        ClientSendMessageEvents.ALLOW_CHAT.register(message -> !message.contains("1984"));
    }
}