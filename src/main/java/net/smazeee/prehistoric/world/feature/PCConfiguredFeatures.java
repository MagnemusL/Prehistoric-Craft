package net.smazeee.prehistoric.world.feature;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.block.ModBlocks;

import java.util.List;

public class PCConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, PrehistoricCraft.MOD_ID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, PrehistoricCraft.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> ARCHAEOPTERIS_TREE =
            CONFIGURED_FEATURES.register("archaeopteris", () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.ARCHAEOPTERIS_LOG.get()),
                    new StraightTrunkPlacer(15, 10, 19),
                    BlockStateProvider.simple(ModBlocks.ARCHAEOPTERIS_LEAVES.get()),
                    new SpruceFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), ConstantInt.of(15)),
                    new TwoLayersFeatureSize(1, 0, 2)).build()));

    public static final RegistryObject<PlacedFeature> ARCHAEOPTERIS_CHECKED = PLACED_FEATURES.register("archaeopteris_checked", () -> new PlacedFeature(ARCHAEOPTERIS_TREE.getHolder().get(),
            ImmutableList.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.ARCHAEOPTERIS_SAPLING.get()))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ARCHAEOPTERIS_SPAWN =
            CONFIGURED_FEATURES.register("archaeopteris_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ARCHAEOPTERIS_CHECKED.getHolder().get(),
                            0.5F)), ARCHAEOPTERIS_CHECKED.getHolder().get())));
}