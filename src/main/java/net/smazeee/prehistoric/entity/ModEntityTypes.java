package net.smazeee.prehistoric.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, PrehistoricCraft.MOD_ID);

    public static final RegistryObject<EntityType<Cryolophosaurus>> CRYOLOPHOSAURUS =
            ENTITY_TYPES.register("cryolophosaurus",
                    () -> EntityType.Builder.of(Cryolophosaurus::new, MobCategory.MONSTER)
                            .sized(0.4F, 1.5F)
                            .build(new ResourceLocation(PrehistoricCraft.MOD_ID, "cryolophosaurus").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
