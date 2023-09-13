package net.smazeee.prehistoric;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.smazeee.prehistoric.block.ModBlocks;
import net.smazeee.prehistoric.block.entity.ModBlockEntities;
import net.smazeee.prehistoric.fluids.ModFluids;
import net.smazeee.prehistoric.item.ModItems;
import net.smazeee.prehistoric.painting.ModPaintings;
import net.smazeee.prehistoric.screen.AcidShowerScreen;
import net.smazeee.prehistoric.screen.ModMenuTypes;
import net.smazeee.prehistoric.world.structures.ModStructures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("prehistoric")
public class PrehistoricCraft {

    public static final String MOD_ID = "prehistoric";
    public static final Logger LOGGER = LogManager.getLogger();

    public PrehistoricCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModStructures.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFluids.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModPaintings.register(modEventBus);

        //ModBlockStateProperties.register(modEventBus);

        modEventBus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ARCHAEOPTERIS_LEAVES.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.ARCHAEOPTERIS_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ARCHAEOPTERIS_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ARCHAEOPTERIS_TRAPDOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BELEMNOPTERIS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BRASILODENDRON_BOTTOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BRASILODENDRON_MIDDLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BRASILODENDRON_TOP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BAIERA.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModFluids.MUD_WATER_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.MUD_WATER_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.MUD_WATER_FLOWING.get(), RenderType.translucent());

        MenuScreens.register(ModMenuTypes.ACID_SHOWER_MENU.get(), AcidShowerScreen::new);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        PrehistoricCraft.LOGGER.info("Remember to update the Acid Shower BE to use tags!");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        }
    }
}
