package net.smazeee.prehistoric.world.feature.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.smazeee.prehistoric.PrehistoricCraft;

import java.util.Random;
import java.util.function.BiConsumer;

public abstract class ModTrunkPlacer extends TrunkPlacer {

    public ModTrunkPlacer(int p_70268_, int p_70269_, int p_70270_) {
        super(p_70268_, p_70269_, p_70270_);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return null;
    }

    protected void setFoliage(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer,
                              Random random, TreeConfiguration treeConfiguration, int ii,
                              FoliagePlacer.FoliageAttachment foliageAttachment, int p_161366_, int p_161367_, int p_161368_) {
        PrehistoricCraft.LOGGER.info("Stuff");

    }
}
