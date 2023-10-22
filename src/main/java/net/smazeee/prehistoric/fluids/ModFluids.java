package net.smazeee.prehistoric.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.block.ModBlocks;
import net.smazeee.prehistoric.item.ModItems;

public class ModFluids {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, PrehistoricCraft.MOD_ID);


    public static final RegistryObject<FlowingFluid> MUD_WATER_FLUID
            = FLUIDS.register("mud_water_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.MUD_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> MUD_WATER_FLOWING
            = FLUIDS.register("mud_water_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.MUD_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> ACID_FLUID
            = FLUIDS.register("acid_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.ACID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> ACID_FLOWING
            = FLUIDS.register("acid_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.ACID_PROPERTIES));



    public static final ForgeFlowingFluid.Properties MUD_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MUD_WATER_FLUID.get(), () -> MUD_WATER_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY_RL)
            .color(0xff6b7356)).slopeFindDistance(4).levelDecreasePerBlock(2)
            .block(() -> ModFluids.MUD_WATER_BLOCK.get()).bucket(() -> ModItems.MUD_WATER_BUCKET.get());

    public static final ForgeFlowingFluid.Properties ACID_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> ACID_FLUID.get(), () -> ACID_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY_RL).temperature(3)
            .color(0xEBFF7E)).slopeFindDistance(4).levelDecreasePerBlock(2)
            .block(() -> ModFluids.ACID_BLOCK.get()).bucket(() -> ModItems.ACID_BUCKET.get());


    public static final RegistryObject<LiquidBlock> MUD_WATER_BLOCK = ModBlocks.BLOCKS.register("mud_water",
            () -> new LiquidBlock(() -> ModFluids.MUD_WATER_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static final RegistryObject<LiquidBlock> ACID_BLOCK = ModBlocks.BLOCKS.register("acid",
            () -> new LiquidBlock(() -> ModFluids.ACID_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
