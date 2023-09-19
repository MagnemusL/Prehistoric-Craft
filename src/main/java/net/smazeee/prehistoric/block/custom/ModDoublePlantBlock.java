package net.smazeee.prehistoric.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.smazeee.prehistoric.block.ModBlocks;

import javax.annotation.Nullable;

public class ModDoublePlantBlock extends BushBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public ModDoublePlantBlock(BlockBehaviour.Properties p_52861_) {
        super(p_52861_);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor accessor, BlockPos pos, BlockPos blockPos) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (direction.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || blockState.is(this) && blockState.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, blockState, accessor, pos, blockPos);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND) || state.is(ModBlocks.CONIFER_BEDDING.get()) || state.is(ModBlocks.CONIFER_DIRT.get());
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        return blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
        BlockPos blockpos = pos.above();
        level.setBlock(blockpos, copyWaterloggedFrom(level, blockpos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)), 3);
    }

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return super.canSurvive(state, reader, pos);
        } else {
            BlockState blockstate = reader.getBlockState(pos.below());
            if (state.getBlock() != this) return super.canSurvive(state, reader, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    public static void placeAt(LevelAccessor accessor, BlockState state, BlockPos pos, int stuff) {
        BlockPos blockpos = pos.above();
        accessor.setBlock(pos, copyWaterloggedFrom(accessor, pos, state.setValue(HALF, DoubleBlockHalf.LOWER)), stuff);
        accessor.setBlock(blockpos, copyWaterloggedFrom(accessor, blockpos, state.setValue(HALF, DoubleBlockHalf.UPPER)), stuff);
    }

    public static BlockState copyWaterloggedFrom(LevelReader reader, BlockPos pos, BlockState state) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(reader.isWaterAt(pos))) : state;
    }

    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                preventCreativeDropFromBottomPart(level, pos, state, player);
            } else {
                dropResources(state, level, pos, (BlockEntity)null, player, player.getMainHandItem());
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }

    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity entity, ItemStack stack) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), entity, stack);
    }

    protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }

    public BlockBehaviour.OffsetType getOffsetType() {
        return BlockBehaviour.OffsetType.XZ;
    }

    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }
}

