package net.smazeee.prehistoric.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.block.ModBlocks;
import net.smazeee.prehistoric.fluids.ModFluids;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PrehistoricCraft.MOD_ID);

    public static final RegistryObject<Item> MUD_WATER_BUCKET = ITEMS.register("mud_water_bucket",
            () -> new BucketItem(ModFluids.MUD_WATER_FLUID,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

    public static final RegistryObject<Item> ARCHAEOPTERIS_DOOR = ITEMS.register("archaeopteris_door",
            () -> new BlockItem(ModBlocks.ARCHAEOPTERIS_DOOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
