package net.smazeee.prehistoric.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.config.ModCommonConfig;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> CAMBRIAN_ORE_PLACED = PlacementUtils.register("cambrian_ore_placed",
            ModConfiguredFeatures.CAMBRIAN_ORE, ModOrePlacement.commonOrePlacement(ModCommonConfig.CAMBRIAN_ORE_VEINS_PER_CHUNK.get(), // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(-36))));
}
