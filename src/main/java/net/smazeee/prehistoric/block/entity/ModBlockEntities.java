package net.smazeee.prehistoric.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, PrehistoricCraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<AcidShowerBE>> ACID_SHOWER_BE =
            BLOCK_ENTITIES.register("acid_shower_be", () ->
                    BlockEntityType.Builder.of(AcidShowerBE::new,
                            ModBlocks.ACID_SHOWER.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
