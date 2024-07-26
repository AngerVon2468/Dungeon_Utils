package org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.hyrulecraft.dungeon_utils.environment.common.block.DungeonUtilsBlockEntities;

import org.jetbrains.annotations.*;

public class PedestalBlockEntity extends BlockEntity {

    @Nullable
    public String pedestal_item_id;

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(DungeonUtilsBlockEntities.PEDESTAL_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(@NotNull NbtCompound nbt) {
        super.writeNbt(nbt);
        if (this.pedestal_item_id != null) {
            nbt.putString("pedestal_item_id", this.pedestal_item_id);
        }
    }

    @Override
    public void readNbt(@NotNull NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("pedestal_item_id")) {
            this.pedestal_item_id = nbt.getString("pedestal_item_id");
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, PedestalBlockEntity blockEntity) {
    }
}