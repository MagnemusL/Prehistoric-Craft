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
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.block.custom.*;
import net.smazeee.prehistoric.block.custom.machines.AcidShowerBlock;
import net.smazeee.prehistoric.block.custom.plants.*;
import net.smazeee.prehistoric.item.ModItems;
import net.smazeee.prehistoric.world.feature.tree.archaeopteris.ArchaeopterisTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PrehistoricCraft.MOD_ID);

    public static final RegistryObject<Block> ARCHAEOPTERIS_LOG = registerBlock("archaeopteris_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> ARCHAEOPTERIS_WOOD = registerBlock("archaeopteris_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> STRIPPED_ARCHAEOPTERIS_WOOD = registerBlock("stripped_archaeopteris_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_ARCHAEOPTERIS_LOG = registerBlock("stripped_archaeopteris_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> ARCHAEOPTERIS_STAIRS = registerBlock("archaeopteris_stairs",
            () -> new StairBlock(() -> ModBlocks.ARCHAEOPTERIS_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));

    public static final RegistryObject<Block> ARCHAEOPTERIS_SLAB = registerBlock("archaeopteris_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));

    public static final RegistryObject<Block> ARCHAEOPTERIS_FENCE = registerBlock("archaeopteris_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));

    public static final RegistryObject<Block> ARCHAEOPTERIS_FENCE_GATE = registerBlock("archaeopteris_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)));

    public static final RegistryObject<Block> ARCHAEOPTERIS_TRAPDOOR = registerBlock("archaeopteris_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));

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

    public static final RegistryObject<Block> ARCHAEOPTERIS_DOOR = registerBlock("archaeopteris_door",
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

    public static final RegistryObject<Block> LIRIODENDRITES_LOG = registerBlock("liriodendrites_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> LIRIODENDRITES_WOOD = registerBlock("liriodendrites_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> STRIPPED_LIRIODENDRITES_WOOD = registerBlock("stripped_liriodendrites_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_LIRIODENDRITES_LOG = registerBlock("stripped_liriodendrites_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> LIRIODENDRITES_STAIRS = registerBlock("liriodendrites_stairs",
            () -> new StairBlock(() -> ModBlocks.LIRIODENDRITES_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));

    public static final RegistryObject<Block> LIRIODENDRITES_SLAB = registerBlock("liriodendrites_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));

    public static final RegistryObject<Block> LIRIODENDRITES_FENCE = registerBlock("liriodendrites_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));

    public static final RegistryObject<Block> LIRIODENDRITES_FENCE_GATE = registerBlock("liriodendrites_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)));

    public static final RegistryObject<Block> LIRIODENDRITES_TRAPDOOR = registerBlock("liriodendrites_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));

    public static final RegistryObject<Block> LIRIODENDRITES_LEAVES = registerBlock("liriodendrites_leaves",
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

    //public static final RegistryObject<Block> LIRIODENDRITES_SAPLING = registerBlock("liriodendrites_sapling",
      //      () -> new SaplingBlock(new LiriodendritesTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> LIRIODENDRITES_DOOR = registerBlock("liriodendrites_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));

    public static final RegistryObject<Block> LIRIODENDRITES_PLANKS = registerBlock("liriodendrites_planks",
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

    public static final RegistryObject<Block> ANOMOZAMITES_SHOOT = registerBlock("anomozamites_shoot",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).instabreak()) {
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

    public static final RegistryObject<Block> BRASILODENDRON = registerBlock("brasilodendron",
            () -> new TriplePlantBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).instabreak()));

    public static final RegistryObject<Block> RED_LAVA_ROCK = registerBlock("red_lava_rock",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BELEMNOPTERIS = registerBlock("belemnopteris",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).instabreak()));

    public static final RegistryObject<Block> BAIERA = registerBlock("baiera",
            () -> new BaieraBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).instabreak()));

    public static final RegistryObject<Block> ZINGIBEROPSIS = registerBlock("zingiberopsis",
            () -> new ZingiberopsisBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).instabreak()));

    public static final RegistryObject<Block> BISONIA_NIEMII = registerBlock("bisonia_niemii",
            () -> new ModDoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).instabreak()));

    public static final RegistryObject<Block> ACID_SHOWER = registerBlock("acid_shower",
            () -> new AcidShowerBlock(BlockBehaviour.Properties.of(Material.METAL)));

    public static final RegistryObject<Block> LIMESTONE = registerBlock("limestone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    public static final RegistryObject<Block> CONIFER_DIRT = registerBlock("conifer_dirt",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT).strength(0.1F).sound(SoundType.GRAVEL)));

    public static final RegistryObject<Block> CONIFER_BEDDING = registerBlock("conifer_bedding",
            () -> new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.1F).sound(SoundType.GRASS)));

    public static final RegistryObject<Block> LIMESTONE_BRICKS = registerBlock("limestone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BJUVIA_TRUNK = registerBlock("bjuvia_trunk",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion()));

    public static final RegistryObject<Block> CYCADEOIDEA_TRUNK = registerBlock("cycadeoidea_trunk",
            () -> new CycadeoideaBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> CYCADEOIDEA_SHOOT = registerBlock("cycadeoidea_shoot",
            () -> new CycadeoideaShoot(BlockBehaviour.Properties.of(Material.LEAVES).noOcclusion().sound(SoundType.GRASS).instabreak()));

    public static final RegistryObject<Block> CYCADEOIDEA_SAPLING = registerBlock("cycadeoida_sapling",
            () -> new CycadeoidaGrower(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).noOcclusion().instabreak()));

    public static final RegistryObject<Block> CRETACEOUS_FOSSIL_BLOCK = registerBlock("cretaceous_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> JURASSIC_FOSSIL_BLOCK = registerBlock("jurassic_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> TRIASSIC_FOSSIL_BLOCK = registerBlock("triassic_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> PERMIAN_FOSSIL_BLOCK = registerBlock("permian_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> CARBONIFEROUS_FOSSIL_BLOCK = registerBlock("carboniferous_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final RegistryObject<Block> DEVONIAN_FOSSIL_BLOCK = registerBlock("devonian_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final RegistryObject<Block> SILURIAN_FOSSIL_BLOCK = registerBlock("silurian_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final RegistryObject<Block> ORDOVICIAN_FOSSIL_BLOCK = registerBlock("ordovician_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final RegistryObject<Block> CAMBRIAN_FOSSIL_BLOCK = registerBlock("cambrian_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final RegistryObject<Block> PRECAMBRIAN_FOSSIL_BLOCK = registerBlock("precambrian_fossil_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));


    private static <T extends Block> RegistryObject<T> registerOre(String name, Supplier<T> block) {
            registerBlock(name,
                    () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE)
                            .strength(2f)
                            .explosionResistance(1f)
                            .requiresCorrectToolForDrops()));
        return null;
    }

    private static <T extends Block> RegistryObject<T> registerOreDeepslate(String name, Supplier<T> block) {
        registerBlock(name,
                () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE)
                        .strength(2f)
                        .explosionResistance(1f)
                        .requiresCorrectToolForDrops()));
        registerBlock("deepslate_" + name,
                () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE)
                        .strength(2f)
                        .explosionResistance(1f)
                        .requiresCorrectToolForDrops()));
        return null;
    }

    private static <T extends Block> RegistryObject<T> registerDeepslate(String name, Supplier<T> block) {
        registerBlock("deepslate_" + name,
                () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE)
                        .strength(2f)
                        .explosionResistance(1f)
                        .requiresCorrectToolForDrops()));
        return null;
    }

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
