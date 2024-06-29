package org.hyrulecraft.lockon_port;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import org.hyrulecraft.dungeon_utils.environment.common.DungeonUtils;

public class RenderTypeThingo extends RenderPhase {

    public RenderTypeThingo(String string, Runnable runnable, Runnable runnable2) {
        super(string, runnable, runnable2);
    }

    public static final RenderLayer RENDER_TYPE = getRenderType();

    private static RenderLayer getRenderType() {
        RenderLayer.MultiPhaseParameters renderTypeState = RenderLayer.MultiPhaseParameters.builder()
                .program(COLOR_PROGRAM)
                .transparency(TRANSLUCENT_TRANSPARENCY)
                .build(false);
        return RenderLayer.of(DungeonUtils.MOD_ID, VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.TRIANGLES, 256, true, true, renderTypeState);
    }
}