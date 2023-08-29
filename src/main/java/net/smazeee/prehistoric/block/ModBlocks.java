package net.smazeee.prehistoric.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.block.custom.ModFlammableRotatedPillarBlock;
import net.smazeee.prehistoric.block.custom.BrasilodendronPlant;
import net.smazeee.prehistoric.item.ModItems;
import net.smazeee.prehistoric.world.feature.tree.ArchaeopterisTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PrehistoricCraft.MOD_ID);

    public static final RegistryObject<Block> ARCHAEOPTERIS_LOG = registerBlock("archaeopteris_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> ARCHAEOPTERIS_WOOD = registerBlock("archaeopteris_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_ARCHAEOPTERIS_WOOD = registerBlock("stripped_archaeopteris_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_ARCHAEOPTERIS_LOG = registerBlock("stripped_archaeopteris_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> ARCHAEOPTERIS_LEAVES = registerBlock("archaeopteris_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> ARCHAEOPTERIS_SAPLING = registerBlock("archaeopteris_sapling",
            () -> new SaplingBlock(new ArchaeopterisTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> BRASILODENDRON_BOTTOM = registerBlock("brasilodendron_bottom",
            () -> new BrasilodendronPlant(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).instabreak()));

    public static final RegistryObject<Block> BRASILODENDRON_MIDDLE = registerBlock("brasilodendron_middle",
            () -> new BrasilodendronPlant(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).instabreak()));

    public static final RegistryObject<Block> BRASILODENDRON_TOP = registerBlock("brasilodendron_top",
            () -> new BrasilodendronPlant(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).instabreak()));

    public static final RegistryObject<Block> BELEMNOPTERIS = registerBlock("belemnopteris",
            () -> new BrasilodendronPlant(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).instabreak()));

    public static final RegistryObject<Block> ARCHAEOPTERIS_DOOR = BLOCKS.register("archaeopteris_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));

    public static final RegistryObject<Block> ARCHAEOPTERIS_PLANKS = registerBlock("archaeopteris_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            } );

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
