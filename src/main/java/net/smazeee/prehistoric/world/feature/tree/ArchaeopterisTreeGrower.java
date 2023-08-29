package net.smazeee.prehistoric.world.feature.tree;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.smazeee.prehistoric.world.structures.ModStructures;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ArchaeopterisTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean b) {
        return ModStructures.ARCHAEOPTERIS_TREES.getHolder().get();
    }
}