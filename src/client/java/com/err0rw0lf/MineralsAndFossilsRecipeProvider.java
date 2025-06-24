package com.err0rw0lf;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class MineralsAndFossilsRecipeProvider extends FabricRecipeProvider {
    public MineralsAndFossilsRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                // Hammer head recipe
                createShaped(RecipeCategory.MISC, ModItems.HAMMER_HEAD, 1)
                        .pattern("ibi")
                        .pattern("ibi")
                        .input('i', Items.IRON_INGOT)
                        .input('b', Items.IRON_BLOCK)
                        .group("multi_bench")
                        .criterion(hasItem(ModItems.HAMMER_HEAD),conditionsFromItem(ModItems.HAMMER_HEAD))
                        .offerTo(exporter);

                // Hammer recipe
                createShaped(RecipeCategory.MISC, ModItems.HAMMER, 1)
                        .pattern("h")
                        .pattern("s")
                        .pattern("s")
                        .input('h', ModItems.HAMMER_HEAD)
                        .input('s', Items.STICK)
                        .group("multi_bench")
                        .criterion(hasItem(ModItems.HAMMER),conditionsFromItem(ModItems.HAMMER))
                        .offerTo(exporter);

            }
        };
    }

    @Override
    public String getName() {
        return "MineralAndFossilsRecipeProvider";
    }
}
