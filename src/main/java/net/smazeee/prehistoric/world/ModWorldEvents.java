package net.smazeee.prehistoric.world;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.smazeee.prehistoric.PrehistoricCraft;

import net.smazeee.prehistoric.world.gen.ModTreeGeneration;

@Mod.EventBusSubscriber(modid = PrehistoricCraft.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModTreeGeneration.generateTrees(event);
    }
}
