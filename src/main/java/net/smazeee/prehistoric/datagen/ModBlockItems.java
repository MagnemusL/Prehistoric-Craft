package net.smazeee.prehistoric.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.block.ModBlocks;
import net.smazeee.prehistoric.item.ModItems;

public class ModBlockItems extends BlockStateProvider {
    public ModBlockItems(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, PrehistoricCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
       // simpleBlockItem(ModBlocks.ARCHAEOPTERIS_LOG.get(), );
    }
}
