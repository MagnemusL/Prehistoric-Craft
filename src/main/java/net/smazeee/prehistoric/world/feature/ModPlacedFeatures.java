package net.smazeee.prehistoric.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> ARCHAEOPTERIS_PLACED = PlacementUtils.register("archaeopteris_placed",
            PCConfiguredFeatures.ARCHAEOPTERIS_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3,0.1f,2)));
}
