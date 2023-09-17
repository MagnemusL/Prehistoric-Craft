package net.smazeee.prehistoric.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.smazeee.prehistoric.block.ModBlocks;
import net.smazeee.prehistoric.datagen.custom.AcidShowerRecipeBuilder;
import net.smazeee.prehistoric.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    private final Item core = ModItems.CHARGED_MACHINE_CORE.get();
    private final Item acid = ModItems.ACID_BUCKET.get();

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {

        ShapedRecipeBuilder.shaped(ModBlocks.ACID_SHOWER.get())
                .define('G', Items.GOLD_INGOT)
                .define('I', Items.IRON_BLOCK)
                .define('#', Items.IRON_INGOT)
                .define('R', Items.REDSTONE_BLOCK)
                .define('T', Items.IRON_TRAPDOOR)
                .pattern("#T#")
                .pattern("#G#")
                .pattern("IRI")
                .unlockedBy("has_iron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_INGOT).build()))
                .save(recipeConsumer);


        ShapedRecipeBuilder.shaped(ModBlocks.ARCHAEOPTERIS_DOOR.get())
                .define('W', ModBlocks.ARCHAEOPTERIS_PLANKS.get())
                .pattern("WW")
                .pattern("WW")
                .pattern("WW")
                .unlockedBy("has_archaeopteris_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ARCHAEOPTERIS_PLANKS.get()).build()))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(ModBlocks.ARCHAEOPTERIS_PLANKS.get())
                .requires(ModBlocks.ARCHAEOPTERIS_LOG.get())
                .unlockedBy("has_archaeopteris_logs", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ARCHAEOPTERIS_LOG.get()).build()))

                .save(recipeConsumer);

        new AcidShowerRecipeBuilder(ModItems.SULFUR.get(), ModItems.PC_PICTURE.get(), core, acid, 1)
                .unlockedBy("has_sulfur", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.SULFUR.get())
                        .build()))
                .save(recipeConsumer);
    }
}
