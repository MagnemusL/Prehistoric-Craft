package net.smazeee.prehistoric.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier placementModifier) {
        return List.of(modifier, InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int i, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(i), modifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int i, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(i), modifier);
    }
}
