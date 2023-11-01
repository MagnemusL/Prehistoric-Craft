package net.smazeee.prehistoric.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.entity.Cryolophosaurus;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CryolophosaurusModel  extends AnimatedGeoModel<Cryolophosaurus> {
    @Override
    public ResourceLocation getModelLocation(Cryolophosaurus object) {
        return new ResourceLocation(PrehistoricCraft.MOD_ID, "geo/cryolophosaurus.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Cryolophosaurus object) {
        return new ResourceLocation(PrehistoricCraft.MOD_ID, "textures/entity/cryolophosaurus_adult_female.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Cryolophosaurus animatable) {
        return new ResourceLocation(PrehistoricCraft.MOD_ID, "animations/cryolophosaurus.animation.json");
    }
}
