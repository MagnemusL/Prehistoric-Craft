package net.smazeee.prehistoric.world.feature.tree.archaeopteris;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.smazeee.prehistoric.world.feature.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ArchaeopterisTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random p_204307_, boolean p_204308_) {
        return ModConfiguredFeatures.ARCHAEOPTERIS;
    }
}