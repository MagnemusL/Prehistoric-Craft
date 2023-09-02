package net.smazeee.prehistoric.painting;

import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;

public class ModPaintings {
    public static final DeferredRegister<Motive> PAINTING_MOTIVES =
            DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, PrehistoricCraft.MOD_ID);


    public static final RegistryObject<Motive> ARCHAEOPTERIS_PAINTING =
            PAINTING_MOTIVES.register("archaeopteris_painting", () -> new Motive(16,32));

    public static final RegistryObject<Motive> CROCODILE_PAINTING =
            PAINTING_MOTIVES.register("crocodile_painting", () -> new Motive(32,16));


    public static void register(IEventBus eventBus) {
        PAINTING_MOTIVES.register(eventBus);
    }
}