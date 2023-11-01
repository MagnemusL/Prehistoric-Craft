package net.smazeee.prehistoric.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.entity.Cryolophosaurus;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CryolophosaurusRenderer extends GeoEntityRenderer<Cryolophosaurus> {
    public CryolophosaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CryolophosaurusModel());
        this.shadowRadius = 2f;
    }

    @Override
    public ResourceLocation getTextureLocation(Cryolophosaurus instance) {
        return new ResourceLocation(PrehistoricCraft.MOD_ID, "textures/entity/cryolophosaurus_adult_female.png");
    }

    @Override
    public RenderType getRenderType(Cryolophosaurus animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
