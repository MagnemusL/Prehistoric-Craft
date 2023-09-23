package net.smazeee.prehistoric.world.feature.tree.archaeopteris;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.world.feature.tree.ModTrunkPlacer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class ArchaeopterisTreeTrunk extends ModTrunkPlacer {

    public ArchaeopterisTreeTrunk(int p_70268_, int p_70269_, int p_70270_) {
        super(p_70268_, p_70269_, p_70270_);
    }

    //public static void growTree(ServerLevel world, BlockPos pos, Random rand, boolean vines, boolean vines2, boolean SaplingSpawn) {

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader levelSimulatedReader, @NotNull BiConsumer<BlockPos, BlockState> biConsumer, @NotNull Random random, int p_161871_, @NotNull BlockPos pos, @NotNull TreeConfiguration treeConfiguration) {
        ResourceLocation resourceLocation = null;
        int i = random.nextInt(2);

        switch (i) {
            case 0 -> resourceLocation = new ResourceLocation(PrehistoricCraft.MOD_ID, "archaeopteris_tree_variant_1");
            case 1 -> resourceLocation = new ResourceLocation(PrehistoricCraft.MOD_ID, "archaeopteris_tree_variant_2");
        }

        if (levelSimulatedReader instanceof ServerLevel serverLevel) {
            StructureTemplate template = serverLevel.getStructureManager().getOrCreate(new ResourceLocation(PrehistoricCraft.MOD_ID, "archaeopteris_tree_variant_1"));
            template.placeInWorld(serverLevel, new BlockPos(pos.getX() - 7, pos.getY(), pos.getZ() - 7), new BlockPos(pos.getX() - 7, pos.getY(), pos.getZ() - 7), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), serverLevel.random, 3);
        }
        return new ArrayList<>();
    }

}
