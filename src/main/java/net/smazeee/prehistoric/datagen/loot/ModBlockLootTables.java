package net.smazeee.prehistoric.datagen.loot;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.block.ModBlocks;
import net.smazeee.prehistoric.item.ModItems;

public class ModBlockLootTables extends BlockLoot {

    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

    @Override
    protected void addTables() {
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ARCHAEOPTERIS_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ARCHAEOPTERIS_LOG.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_STAIRS.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_SAPLING.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_FENCE.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_FENCE_GATE.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_TRAPDOOR.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_LEAVES.get());
        this.dropSelf(ModBlocks.ANOMOZAMITES_SHOOT.get());
        this.dropSelf(ModBlocks.BRASILODENDRON.get());
        this.dropSelf(ModBlocks.BELEMNOPTERIS.get());
        this.dropSelf(ModBlocks.ZINGIBEROPSIS.get());
        this.dropSelf(ModBlocks.BISONIA_NIEMII.get());
        this.dropSelf(ModBlocks.BAIERA.get());
        this.add(ModBlocks.ARCHAEOPTERIS_DOOR.get(), (block ->
                createDoorTable(ModBlocks.ARCHAEOPTERIS_DOOR.get())));
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_PLANKS.get());
        this.dropSelf(ModBlocks.ACID_SHOWER.get());
        this.dropSelf(ModBlocks.LIMESTONE.get());
        this.dropSelf(ModBlocks.CONIFER_DIRT.get());
        this.dropSelf(ModBlocks.CYCADEOIDEA_TRUNK.get());
        this.dropSelf(ModBlocks.CYCADEOIDEA_SHOOT.get());
        this.dropSelf(ModBlocks.CYCADEOIDEA_SAPLING.get());
        this.dropSelf(ModBlocks.BJUVIA_TRUNK.get());
        this.add(ModBlocks.CONIFER_BEDDING.get(), (block) -> createSingleItemTableWithSilkTouch(block, ModBlocks.CONIFER_DIRT.get()));

        this.dropSelf(ModBlocks.LIMESTONE_BRICKS.get());
        this.add(ModBlocks.ARCHAEOPTERIS_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.STRIPPED_ARCHAEOPTERIS_LOG.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.ARCHAEOPTERIS_SLAB.get(), BlockLoot::createSlabItemTable);

        this.add(ModBlocks.CRETACEOUS_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.CRETACEOUS_FOSSIL_BLOCK.get(), ModItems.CRETACEOUS_FOSSIL.get())));

        this.add(ModBlocks.JURASSIC_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.JURASSIC_FOSSIL_BLOCK.get(), ModItems.JURASSIC_FOSSIL.get())));

        this.add(ModBlocks.TRIASSIC_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.JURASSIC_FOSSIL_BLOCK.get(), ModItems.JURASSIC_FOSSIL.get())));

        this.add(ModBlocks.PERMIAN_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.PERMIAN_FOSSIL_BLOCK.get(), ModItems.JURASSIC_FOSSIL.get())));

        this.add(ModBlocks.CARBONIFEROUS_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.CARBONIFEROUS_FOSSIL_BLOCK.get(), ModItems.JURASSIC_FOSSIL.get())));

        this.add(ModBlocks.DEVONIAN_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.DEVONIAN_FOSSIL_BLOCK.get(), ModItems.JURASSIC_FOSSIL.get())));

        this.add(ModBlocks.SILURIAN_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.SILURIAN_FOSSIL_BLOCK.get(), ModItems.JURASSIC_FOSSIL.get())));

        this.add(ModBlocks.ORDOVICIAN_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.ORDOVICIAN_FOSSIL_BLOCK.get(), ModItems.JURASSIC_FOSSIL.get())));

        this.add(ModBlocks.CAMBRIAN_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.CAMBRIAN_FOSSIL_BLOCK.get(), ModItems.JURASSIC_FOSSIL.get())));

        this.add(ModBlocks.PRECAMBRIAN_FOSSIL_BLOCK.get(),
                (block -> createOreDrop(ModBlocks.PRECAMBRIAN_FOSSIL_BLOCK.get(), ModItems.JURASSIC_FOSSIL.get())));

        this.dropSelf(ModBlocks.ARCHAEOPTERIS_LOG.get());


        this.dropSelf(ModBlocks.LIRIODENDRITES_LOG.get());
        this.dropSelf(ModBlocks.LIRIODENDRITES_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_LIRIODENDRITES_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_LIRIODENDRITES_LOG.get());
        this.dropSelf(ModBlocks.LIRIODENDRITES_STAIRS.get());
        //this.dropSelf(ModBlocks.LIRIODENDRITES_SAPLING.get());
        this.dropSelf(ModBlocks.LIRIODENDRITES_FENCE.get());
        this.dropSelf(ModBlocks.LIRIODENDRITES_FENCE_GATE.get());
        this.dropSelf(ModBlocks.LIRIODENDRITES_TRAPDOOR.get());
        this.dropSelf(ModBlocks.LIRIODENDRITES_LEAVES.get());
        this.dropSelf(ModBlocks.LIRIODENDRITES_PLANKS.get());
        this.add(ModBlocks.LIRIODENDRITES_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.STRIPPED_LIRIODENDRITES_LOG.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.LIRIODENDRITES_SLAB.get(), BlockLoot::createSlabItemTable);
        this.add(ModBlocks.LIRIODENDRITES_DOOR.get(), (block ->
                createDoorTable(ModBlocks.LIRIODENDRITES_DOOR.get())));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
