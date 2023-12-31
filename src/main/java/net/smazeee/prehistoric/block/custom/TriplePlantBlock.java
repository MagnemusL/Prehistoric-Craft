package net.smazeee.prehistoric.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.level.block.state.properties.WallSide;
import net.smazeee.prehistoric.util.TripleBlockHalf;

import javax.annotation.Nullable;

public class TriplePlantBlock extends BushBlock {
    public static final EnumProperty<TripleBlockHalf> TRIPLE_BLOCK_HALF = EnumProperty.create("stage", TripleBlockHalf.class);
        public static final EnumProperty<TripleBlockHalf> HALF = TRIPLE_BLOCK_HALF;

        public TriplePlantBlock(BlockBehaviour.Properties properties) {
            super(properties);
            this.registerDefaultState(this.stateDefinition.any().setValue(HALF, TripleBlockHalf.LOWER));
        }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor accessor, BlockPos pos, BlockPos blockPos) {
        TripleBlockHalf tripleblockhalf = state.getValue(HALF);
        if (direction.getAxis() != Direction.Axis.Y || tripleblockhalf == TripleBlockHalf.LOWER != (direction == Direction.UP) || blockState.is(this) && blockState.getValue(HALF) != tripleblockhalf) {
            return tripleblockhalf == TripleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(accessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, blockState, accessor, pos, blockPos);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

        @Nullable
        public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
            BlockPos blockpos = placeContext.getClickedPos();
            Level level = placeContext.getLevel();
            Player player = placeContext.getPlayer();
            return blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(placeContext) ? super.getStateForPlacement(placeContext) : null;
        }

        public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
            BlockPos blockpos = pos.above();
            BlockPos blockPosAbove = blockpos.above();
            level.setBlock(blockpos, copyWaterloggedFrom(level, blockpos, this.defaultBlockState().setValue(HALF, TripleBlockHalf.MIDDLE)), 3);
        }

        public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
            if (state.getValue(HALF) != TripleBlockHalf.UPPER) {
                return super.canSurvive(state, levelReader, pos);
            } else {
                BlockState blockstate = levelReader.getBlockState(pos.below());
                if (state.getBlock() != this)
                    return super.canSurvive(state, levelReader, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
                return blockstate.is(this) && blockstate.getValue(HALF) == TripleBlockHalf.LOWER;
            }
        }

        public static void placeAt(LevelAccessor levelAccessor, BlockState state, BlockPos pos, int i) {
            BlockPos blockpos = pos.above();
            levelAccessor.setBlock(pos, copyWaterloggedFrom(levelAccessor, pos, state.setValue(HALF, TripleBlockHalf.LOWER)), i);
            levelAccessor.setBlock(blockpos, copyWaterloggedFrom(levelAccessor, blockpos, state.setValue(HALF, TripleBlockHalf.MIDDLE)), i);
            levelAccessor.setBlock(blockpos, copyWaterloggedFrom(levelAccessor, blockpos, state.setValue(HALF, TripleBlockHalf.UPPER)), i);
        }

        public static BlockState copyWaterloggedFrom(LevelReader p_182454_, BlockPos p_182455_, BlockState p_182456_) {
            return p_182456_.hasProperty(BlockStateProperties.WATERLOGGED) ? p_182456_.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(p_182454_.isWaterAt(p_182455_))) : p_182456_;
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

        public void playerDestroy(Level p_52865_, Player p_52866_, BlockPos p_52867_, BlockState p_52868_, @Nullable BlockEntity p_52869_, ItemStack p_52870_) {
            super.playerDestroy(p_52865_, p_52866_, p_52867_, Blocks.AIR.defaultBlockState(), p_52869_, p_52870_);
        }

        protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
            TripleBlockHalf tripleblockhalf = state.getValue(HALF);
            if (tripleblockhalf == TripleBlockHalf.UPPER) {
                BlockPos blockpos = pos.below();
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == TripleBlockHalf.LOWER) {
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

        public long getSeed(BlockState p_52891_, BlockPos p_52892_) {
            return Mth.getSeed(p_52892_.getX(), p_52892_.below(p_52891_.getValue(HALF) == TripleBlockHalf.LOWER ? 0 : 1).getY(), p_52892_.getZ());
        }
}