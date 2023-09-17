package net.smazeee.prehistoric.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PrehistoricCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ACID_BUCKET.get());
        simpleItem(ModItems.CHARGED_MACHINE_CORE.get());
        simpleItem(ModItems.UNCHARGED_MACHINE_CORE.get());
        simpleItem(ModItems.SULFUR.get());
        simpleItem(ModItems.EMPTY_JAR.get());
        simpleItem(ModItems.BELEMOPTERIS_SPORE_JAR.get());
        simpleItem(ModItems.MUD_WATER_BUCKET.get());
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PrehistoricCraft.MOD_ID, "item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(PrehistoricCraft.MOD_ID, "item/" + item.getRegistryName().getPath()));
    }
}
