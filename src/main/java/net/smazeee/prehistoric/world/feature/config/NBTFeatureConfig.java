package net.smazeee.prehistoric.world.feature.config;

import com.ibm.icu.impl.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

/*public class NBTFeatureConfig implements FeatureConfiguration {
    public static final Codec<NBTFeatureConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            ResourceLocation.CODEC.fieldOf("processors").forGetter(NBTFeatureConfig -> NBTFeatureConfig.processor),
            ResourceLocation.CODEC.fieldOf("post_processors").orElse(new ResourceLocation("minecraft:empty")).forGetter(NBTFeatureConfig -> NBTFeatureConfig.postProcessor),
            Codec.mapPair(ResourceLocation.CODEC.fieldOf("resourcelocation"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("dungeon_nbt_entries").forGetter(NBTFeatureConfig -> NBTFeatureConfig.nbtResourcelocationsAndWeights),
            Codec.INT.fieldOf("structure_y_offset").orElse(0).forGetter(NBTFeatureConfig -> NBTFeatureConfig.structureYOffset)
    ).apply(configInstance, NBTFeatureConfig::new));

    public final List<Pair<ResourceLocation, Integer>> nbtResourcelocationsAndWeights;
    public final ResourceLocation processor;
    public final ResourceLocation postProcessor;
    public final int structureYOffset;

    public NBTFeatureConfig(ResourceLocation processor,
                            ResourceLocation postProcessor,
                            List<Pair<ResourceLocation, Integer>> nbtIdentifiersAndWeights,
                            int structureYOffset)
    {
        this.nbtResourcelocationsAndWeights = nbtIdentifiersAndWeights;
        this.processor = processor;
        this.postProcessor = postProcessor;
        this.structureYOffset = structureYOffset;
    }
}

 */