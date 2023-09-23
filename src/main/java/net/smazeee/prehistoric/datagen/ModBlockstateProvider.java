package net.smazeee.prehistoric.datagen;


import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.block.ModBlocks;
import net.smazeee.prehistoric.block.custom.ModFlammableRotatedPillarBlock;

import java.util.function.Function;

public class ModBlockstateProvider extends BlockStateProvider {

    public ModBlockstateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, PrehistoricCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.ARCHAEOPTERIS_PLANKS.get());
        simpleBlock(ModBlocks.ARCHAEOPTERIS_LEAVES.get());
        simpleBlock(ModBlocks.LIMESTONE.get());
        simpleBlock(ModBlocks.LIMESTONE_BRICKS.get());
        simpleBlock(ModBlocks.CONIFER_DIRT.get());
        simpleBlock(ModBlocks.CRETACEOUS.get());
        simpleBlock(ModBlocks.JURASSIC.get());
        simpleBlock(ModBlocks.TRIASSIC.get());
        simpleBlock(ModBlocks.PERMIAN.get());
        simpleBlock(ModBlocks.CARBONIFEROUS.get());
        simpleBlock(ModBlocks.DEVONIAN.get());
        simpleBlock(ModBlocks.SILURIAN.get());
        simpleBlock(ModBlocks.ORDOVICIAN.get());
        simpleBlock(ModBlocks.CAMBRIAN.get());
        simpleBlock(ModBlocks.PRECAMBRIAN.get());

        //buttonBlock((ButtonBlock) ModBlocks.hhs.get(), blockTexture(ModBlocks.dfgf.get()));
        //pressurePlateBlock((PressurePlateBlock) ModBlocks.hhs.get(), blockTexture(ModBlocks.dfgf.get()));
        fenceBlock((FenceBlock) ModBlocks.ARCHAEOPTERIS_FENCE.get(), blockTexture(ModBlocks.ARCHAEOPTERIS_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.ARCHAEOPTERIS_FENCE_GATE.get(), blockTexture(ModBlocks.ARCHAEOPTERIS_PLANKS.get()));

        logBlock((RotatedPillarBlock) ModBlocks.ARCHAEOPTERIS_LOG.get());
        doorBlock((DoorBlock) ModBlocks.ARCHAEOPTERIS_DOOR.get(), new ResourceLocation(PrehistoricCraft.MOD_ID, "block/archaeopteris_door_bottom"),
                new ResourceLocation(PrehistoricCraft.MOD_ID, "block/archaeopteris_door_top"));

        axisBlock((RotatedPillarBlock) ModBlocks.ARCHAEOPTERIS_WOOD.get(), blockTexture(ModBlocks.ARCHAEOPTERIS_LOG.get()), blockTexture(ModBlocks.ARCHAEOPTERIS_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_ARCHAEOPTERIS_LOG.get(), new ResourceLocation(PrehistoricCraft.MOD_ID, "block/stripped_archaeopteris_log"),
                new ResourceLocation(PrehistoricCraft.MOD_ID, "block/stripped_archaeopteris_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_ARCHAEOPTERIS_WOOD.get(), new ResourceLocation(PrehistoricCraft.MOD_ID, "block/stripped_archaeopteris_log"),
                new ResourceLocation(PrehistoricCraft.MOD_ID, "block/stripped_archaeopteris_log"));

        stairsBlock((StairBlock) ModBlocks.ARCHAEOPTERIS_STAIRS.get(), blockTexture(ModBlocks.ARCHAEOPTERIS_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.ARCHAEOPTERIS_SLAB.get(), blockTexture(ModBlocks.ARCHAEOPTERIS_PLANKS.get()),
                blockTexture(ModBlocks.ARCHAEOPTERIS_PLANKS.get()));
        trapdoorBlock((TrapDoorBlock) ModBlocks.ARCHAEOPTERIS_TRAPDOOR.get(), new ResourceLocation(PrehistoricCraft.MOD_ID, "block/archaeopteris_trapdoor"), true);
    }

    public ModelFile flowerPotCross(String name) {
        return models().withExistingParent(name, "flower_pot_cross");
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(block.getAgeProperty()),
                new ResourceLocation(PrehistoricCraft.MOD_ID, "block/" + textureName + state.getValue(block.getAgeProperty()))));

        return models;
    }
}
