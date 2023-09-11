package net.smazeee.prehistoric.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class StructureTree extends BushBlock implements BonemealableBlock {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected static final float AABB_OFFSET = 6.0F;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    private final AbstractTreeGrower treeGrower;

    public StructureTree(AbstractTreeGrower treeGrower, BlockBehaviour.Properties properties) {
        super(properties);
        this.treeGrower = treeGrower;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collisionContext) {
        return SHAPE;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if (level.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
            if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
            this.advanceTree(level, pos, state, random);
        }

    }

    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, Random random) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            this.treeGrower.growTree(level, level.getChunkSource().getGenerator(), pos, state, random);
        }
    }

    public boolean isValidBonemealTarget(BlockGetter p_55991_, BlockPos p_55992_, BlockState p_55993_, boolean p_55994_) {
        return true;
    }

    public boolean isBonemealSuccess(Level p_55996_, Random p_55997_, BlockPos p_55998_, BlockState p_55999_) {
        return (double)p_55996_.random.nextFloat() < 0.45D;
    }

    public void performBonemeal(ServerLevel p_55986_, Random p_55987_, BlockPos p_55988_, BlockState p_55989_) {
        this.advanceTree(p_55986_, p_55988_, p_55989_, p_55987_);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56001_) {
        p_56001_.add(STAGE);
    }
}

