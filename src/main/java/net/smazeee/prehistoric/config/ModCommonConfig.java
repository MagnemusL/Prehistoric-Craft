package net.smazeee.prehistoric.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> CAMBRIAN_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> CAMBRIAN_ORE_VEIN_SIZE;

    static {
        BUILDER.push("Configs for Prehistoric Craft");

        CAMBRIAN_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Cambrian Fossil Ore Veins spawn per chunk!")
                .define("Veins Per Chunk", 3);
        CAMBRIAN_ORE_VEIN_SIZE = BUILDER.comment("How many Cambrian Fossil Ore Blocks spawn in one Vein!")
                .defineInRange("Vein Size",1 , 1, 4);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
