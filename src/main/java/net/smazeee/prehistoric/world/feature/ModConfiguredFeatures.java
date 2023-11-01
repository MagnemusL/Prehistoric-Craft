package net.smazeee.prehistoric.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.smazeee.prehistoric.block.ModBlocks;
import net.smazeee.prehistoric.config.ModCommonConfig;
import net.smazeee.prehistoric.world.feature.tree.archaeopteris.ArchaeopterisFoliagePlacer;
import net.smazeee.prehistoric.world.feature.tree.archaeopteris.ArchaeopterisTreeTrunk;

import java.util.List;

public class ModConfiguredFeatures {
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ARCHAEOPTERIS =
        FeatureUtils.register("archaeopteris", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(ModBlocks.ARCHAEOPTERIS_LOG.get()),
            new ArchaeopterisTreeTrunk(0,0,0),
            BlockStateProvider.simple(ModBlocks.ARCHAEOPTERIS_LEAVES.get()),
            new ArchaeopterisFoliagePlacer(ConstantInt.of(0),ConstantInt.of(0),0),
            new TwoLayersFeatureSize(0,0,0)
        ).build());

    public static final Holder<PlacedFeature> ARCHAEOPTERIS_TREE =
            PlacementUtils.register("archaeopteris_tree_placement", ModConfiguredFeatures.ARCHAEOPTERIS,
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ARCHAEOPTERIS_SPAWN =
            FeatureUtils.register("archaeopteris_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ARCHAEOPTERIS_TREE,
                            0.5F)), ARCHAEOPTERIS_TREE));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_FOSSIL_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PRECAMBRIAN.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.CAMBRIAN.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CAMBRIAN_ORE = FeatureUtils.register("cambrian_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_FOSSIL_ORES, ModCommonConfig.CAMBRIAN_ORE_VEIN_SIZE.get()));
}