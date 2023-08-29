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
import net.smazeee.prehistoric.blockstates.ModBlockStateProperties;
import net.smazeee.prehistoric.util.TripleBlockHalf;

import javax.annotation.Nullable;

public class TriplePlantBlock extends BushBlock {
        public static final EnumProperty<TripleBlockHalf> HALF = ModBlockStateProperties.TRIPLE_BLOCK_HALF;

        public TriplePlantBlock(BlockBehaviour.Properties properties) {
            super(properties);
            this.registerDefaultState(this.stateDefinition.any().setValue(HALF, TripleBlockHalf.LOWER));
        }

        public BlockState updateShape(BlockState state, Direction axis, BlockState blockState, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos1) {
            TripleBlockHalf tripleBlockHalf = state.getValue(HALF);
            if (axis.getAxis() != Direction.Axis.Y || tripleBlockHalf == TripleBlockHalf.LOWER != (axis == Direction.UP) || blockState.is(this) && blockState.getValue(HALF) != tripleBlockHalf) {
                return tripleBlockHalf == TripleBlockHalf.LOWER && axis == Direction.DOWN && !state.canSurvive(levelAccessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, axis, blockState, levelAccessor, pos, pos1);
            } else {
                return Blocks.AIR.defaultBlockState();
            }
        }

        @Nullable
        public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
            BlockPos blockpos = placeContext.getClickedPos();
            Level level = placeContext.getLevel();
            return blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(placeContext) ? super.getStateForPlacement(placeContext) : null;
        }

        public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
            BlockPos blockpos = pos.above();
            level.setBlock(blockpos, copyWaterloggedFrom(level, blockpos, this.defaultBlockState().setValue(HALF, TripleBlockHalf.UPPER)), 3);
        }

        public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
            if (state.getValue(HALF) != TripleBlockHalf.UPPER) {
                return super.canSurvive(state, levelReader, pos);
            } else {
                BlockState blockstate = levelReader.getBlockState(pos.below());
                if (state.getBlock() != this) return super.canSurvive(state, levelReader, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
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

        public void playerWillDestroy(Level p_52878_, BlockPos p_52879_, BlockState p_52880_, Player p_52881_) {
            if (!p_52878_.isClientSide) {
                if (p_52881_.isCreative()) {
                    preventCreativeDropFromBottomPart(p_52878_, p_52879_, p_52880_, p_52881_);
                } else {
                    dropResources(p_52880_, p_52878_, p_52879_, (BlockEntity)null, p_52881_, p_52881_.getMainHandItem());
                }
            }

            super.playerWillDestroy(p_52878_, p_52879_, p_52880_, p_52881_);
        }

        public void playerDestroy(Level p_52865_, Player p_52866_, BlockPos p_52867_, BlockState p_52868_, @Nullable BlockEntity p_52869_, ItemStack p_52870_) {
            super.playerDestroy(p_52865_, p_52866_, p_52867_, Blocks.AIR.defaultBlockState(), p_52869_, p_52870_);
        }

        protected static void preventCreativeDropFromBottomPart(Level p_52904_, BlockPos p_52905_, BlockState p_52906_, Player p_52907_) {
            TripleBlockHalf doubleblockhalf = p_52906_.getValue(HALF);
            if (doubleblockhalf == TripleBlockHalf.UPPER) {
                BlockPos blockpos = p_52905_.below();
                BlockState blockstate = p_52904_.getBlockState(blockpos);
                if (blockstate.is(p_52906_.getBlock()) && blockstate.getValue(HALF) == TripleBlockHalf.LOWER) {
                    BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                    p_52904_.setBlock(blockpos, blockstate1, 35);
                    p_52904_.levelEvent(p_52907_, 2001, blockpos, Block.getId(blockstate));
                }
            }

        }

        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_52901_) {
            p_52901_.add(HALF);
        }

        public BlockBehaviour.OffsetType getOffsetType() {
            return BlockBehaviour.OffsetType.XZ;
        }

        public long getSeed(BlockState p_52891_, BlockPos p_52892_) {
            return Mth.getSeed(p_52892_.getX(), p_52892_.below(p_52891_.getValue(HALF) == TripleBlockHalf.LOWER ? 0 : 1).getY(), p_52892_.getZ());
        }
}
