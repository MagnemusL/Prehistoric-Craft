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
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_FENCE.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_FENCE_GATE.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_WALLS.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_TRAPDOOR.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_LEAVES.get());
        this.dropSelf(ModBlocks.ANOMOZAMITES_SHOOT.get());
        this.dropSelf(ModBlocks.BRASILODENDRON_BOTTOM.get());
        this.dropSelf(ModBlocks.BRASILODENDRON_MIDDLE.get());
        this.dropSelf(ModBlocks.BRASILODENDRON_TOP.get());
        this.dropSelf(ModBlocks.BELEMNOPTERIS.get());
        this.dropSelf(ModBlocks.BAIERA.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_DOOR.get());
        this.dropSelf(ModBlocks.ARCHAEOPTERIS_PLANKS.get());
        this.dropSelf(ModBlocks.ACID_SHOWER.get());
        this.dropSelf(ModBlocks.LIMESTONE.get());
        this.dropSelf(ModBlocks.CONIFER_DIRT.get());
        this.add(ModBlocks.CONIFER_BEDDING.get(), (block) -> createSingleItemTableWithSilkTouch(block, ModBlocks.CONIFER_DIRT.get()));

        this.dropSelf(ModBlocks.LIMESTONE_BRICKS.get());

        this.add(ModBlocks.ARCHAEOPTERIS_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.STRIPPED_ARCHAEOPTERIS_LOG.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.ARCHAEOPTERIS_SLAB.get(), BlockLoot::createSlabItemTable);

        /*this.add(ModBlocks.ARCHAEOPTERIS_DOOR.get(),
                (block) -> createOreDrop(ModBlocks.ARCHAEOPTERIS_DOOR.get(), ModItems.ACID_BUCKET.get()));
        */

        this.dropSelf(ModBlocks.ARCHAEOPTERIS_LOG.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
