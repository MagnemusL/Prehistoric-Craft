package net.smazeee.prehistoric.world.feature.tree.archaeopteris;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;

import java.util.Random;
import java.util.function.BiConsumer;

public class ArchaeopterisFoliagePlacer extends BlobFoliagePlacer {
    public ArchaeopterisFoliagePlacer(IntProvider p_161356_, IntProvider p_161357_, int p_161358_) {
        super(p_161356_, p_161357_, p_161358_);
    }

    @Override
    protected void createFoliage(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer,
                                 Random random, TreeConfiguration treeConfiguration,
                                 int ii, FoliageAttachment foliageAttachment, int p_161366_, int p_161367_, int p_161368_) {

    }

}