package net.smazeee.prehistoric.item;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.fluids.ModFluids;
import net.smazeee.prehistoric.item.custom.JarItem;
import net.smazeee.prehistoric.item.custom.MachineCore;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PrehistoricCraft.MOD_ID);

    public static final RegistryObject<Item> MUD_WATER_BUCKET = ITEMS.register("mud_water_bucket",
            () -> new BucketItem(ModFluids.MUD_WATER_FLUID,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

    public static final RegistryObject<Item> ACID_BUCKET = ITEMS.register("acid_bucket",
            () -> new BucketItem(ModFluids.ACID_FLUID,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

    public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur",
            () -> new JarItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> PC_PICTURE = ITEMS.register("pc_picture",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> EMPTY_JAR = ITEMS.register("empty_jar",
            () -> new JarItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> BELEMNOPTERIS_SPORE_JAR = ITEMS.register("belemnopteris_spore_jar",
            () -> new JarItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> CYCADEOIDA_SPORE_JAR = ITEMS.register("cycadeoida_spore_jar",
            () -> new JarItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> UNCHARGED_MACHINE_CORE = ITEMS.register("uncharged_machine_core",
            () -> new MachineCore(new Item.Properties().tab(CreativeModeTab.TAB_MISC).defaultDurability(0)));

    public static final RegistryObject<Item> CHARGED_MACHINE_CORE = ITEMS.register("charged_machine_core",
            () -> new MachineCore(new Item.Properties().tab(CreativeModeTab.TAB_MISC).defaultDurability(1000)));

    public static final RegistryObject<Item> CRETACEOUS_FOSSIL = ITEMS.register("cretaceous_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> JURASSIC_FOSSIL = ITEMS.register("jurassic_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> TRIASSIC_FOSSIL = ITEMS.register("triassic_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> PERMIAN_FOSSIL = ITEMS.register("permian_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> CARBONIFEROUS_FOSSIL = ITEMS.register("carboniferous_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> DEVONIAN_FOSSIL = ITEMS.register("devonian_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SILURIAN_FOSSIL = ITEMS.register("silurian_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ORDOVICIAN_FOSSIL = ITEMS.register("ordovician_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> CAMBRIAN_FOSSIL = ITEMS.register("cambrian_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> PRECAMBRIAN_FOSSIL = ITEMS.register("precambrian_fossil",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
