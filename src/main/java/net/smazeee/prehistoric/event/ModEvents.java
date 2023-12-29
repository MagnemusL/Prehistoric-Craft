package net.smazeee.prehistoric.event;

import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.entity.Cryolophosaurus;
import net.smazeee.prehistoric.entity.ModEntityTypes;
import net.smazeee.prehistoric.recipe.AcidShowerRecipe;

public class ModEvents {

    @Mod.EventBusSubscriber(modid = PrehistoricCraft.MOD_ID)
    public static class ForgeEvents {

    }

    @Mod.EventBusSubscriber(modid = PrehistoricCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
            Registry.register(Registry.RECIPE_TYPE, AcidShowerRecipe.Type.ID, AcidShowerRecipe.Type.INSTANCE);
        }

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            //event.put(ModEntityTypes.CRYOLOPHOSAURUS.get(), Cryolophosaurus.createAttributes().build());
        }
    }
}
