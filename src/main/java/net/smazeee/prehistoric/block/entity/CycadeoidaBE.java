package net.smazeee.prehistoric.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.smazeee.prehistoric.block.ModBlocks;

import java.util.Random;

public class CycadeoidaBE extends BlockEntity {
    public CycadeoidaBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
