package net.smazeee.prehistoric.blockstates;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.util.TripleBlockHalf;

public class ModBlockStateProperties {
    public static final DeferredRegister<BlockStateProviderType<?>> BLOCKSTATES =
            DeferredRegister.create(ForgeRegistries.BLOCK_STATE_PROVIDER_TYPES, PrehistoricCraft.MOD_ID);

    public static final EnumProperty<TripleBlockHalf> TRIPLE_BLOCK_HALF = EnumProperty.create("half", TripleBlockHalf.class);

    public static void register(IEventBus eventBus) {
        BLOCKSTATES.register(eventBus);
    }
}
