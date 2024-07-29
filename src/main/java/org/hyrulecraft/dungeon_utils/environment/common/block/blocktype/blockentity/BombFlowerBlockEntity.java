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
import org.hyrulecraft.dungeon_utils.environment.common.block.blocktype.BombFlowerBlock;

import org.jetbrains.annotations.*;

public class BombFlowerBlockEntity extends BlockEntity {

    public int growth;

    public boolean antiLag;

    public BombFlowerBlockEntity(BlockPos pos, BlockState state) {
        super(DungeonUtilsBlockEntities.BOMB_FLOWER_BLOCK_ENTITY, pos, state);
        this.growth = 0;
        this.antiLag = false;
    }

    @Override
    protected void writeNbt(@NotNull NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("growth", this.growth);
        nbt.putBoolean("anti_lag", this.antiLag);
    }

    @Override
    public void readNbt(@NotNull NbtCompound nbt) {
        super.readNbt(nbt);
        this.growth = nbt.getInt("growth");
        this.antiLag = nbt.getBoolean("anti_lag");
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

    public void serverTick(World world, BlockPos pos, BlockState state, BombFlowerBlockEntity blockEntity) {
        if (this.growth < (20 * 7)) {
            this.growth++;
        } else if (!this.antiLag) {
            world.setBlockState(pos, state.with(BombFlowerBlock.IS_GROWN, true));
            this.antiLag = true;
        }
    }
}