package net.smazeee.prehistoric.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.recipe.AcidShowerRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class AcidShowerRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final Ingredient core;
    private final Ingredient acid;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public AcidShowerRecipeBuilder(ItemLike ingredient, Item result, ItemLike core, ItemLike acid, int count) {
        this.result = result.asItem();
        this.ingredient = Ingredient.of(ingredient);
        this.core = Ingredient.of(core);
        this.acid = Ingredient.of(acid);
        this.count = count;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, CriterionTriggerInstance instance) {
        this.advancement.addCriterion(name, instance);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> recipeConsumer, ResourceLocation resourceLocation) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(resourceLocation))
                .rewards(AdvancementRewards.Builder.recipe(resourceLocation)).requirements(RequirementsStrategy.OR);

        recipeConsumer.accept(new AcidShowerRecipeBuilder.Result(resourceLocation, this.result, this.ingredient, this.core, this.acid, this.count,
                this.advancement, new ResourceLocation(resourceLocation.getNamespace(), "recipes/" +
                this.result.getItemCategory().getRecipeFolderName() + "/" + resourceLocation.getPath())));
    }
    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final Ingredient ingredient;
        private final Ingredient core;
        private final Ingredient acid;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Item result, Ingredient ingredient, Ingredient core, Ingredient acid, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.result = result;
            this.ingredient = ingredient;
            this.core = core;
            this.acid = acid;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(ingredient.toJson());
            jsonArray.add(core.toJson());
            jsonArray.add(acid.toJson());

            json.add("ingredients", jsonArray);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", this.result.getRegistryName().toString());
            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }

            json.add("output", jsonObject);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(PrehistoricCraft.MOD_ID,
                    this.result.getRegistryName().getPath() + "_from_acid_shower");
        }

        @Override
        public RecipeSerializer<?> getType() {
            return AcidShowerRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
