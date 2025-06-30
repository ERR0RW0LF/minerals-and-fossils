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
                        .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                // Hammer recipe
                createShaped(RecipeCategory.MISC, ModItems.HAMMER, 1)
                        .pattern("h")
                        .pattern("s")
                        .pattern("s")
                        .input('h', ModItems.HAMMER_HEAD)
                        .input('s', Items.STICK)
                        .criterion(hasItem(ModItems.HAMMER_HEAD),conditionsFromItem(ModItems.HAMMER_HEAD))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, ModBlocks.SMALL_CUT_AMETHYST_BLOCK.asItem(), 1)
                        .input(ModItems.SMALL_CUT_AMETHYST, 9)
                        .criterion(hasItem(ModItems.SMALL_CUT_AMETHYST), conditionsFromItem(ModItems.SMALL_CUT_AMETHYST))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, ModBlocks.MEDIUM_CUT_AMETHYST_BLOCK.asItem(), 1)
                        .input(ModItems.MEDIUM_CUT_AMETHYST, 9)
                        .criterion(hasItem(ModItems.MEDIUM_CUT_AMETHYST), conditionsFromItem(ModItems.MEDIUM_CUT_AMETHYST))
                        .offerTo(exporter);
                createShapeless(RecipeCategory.MISC, ModBlocks.BIG_CUT_AMETHYST_BLOCK.asItem(), 1)
                        .input(ModItems.BIG_CUT_AMETHYST, 9)
                        .criterion(hasItem(ModItems.BIG_CUT_AMETHYST), conditionsFromItem(ModItems.BIG_CUT_AMETHYST))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.GEMSTONE_WORKSTATION_BLOCK.asItem(), 1)
                        .pattern("hs")
                        .pattern("ii")
                        .pattern("ii")
                        .input('h', ModItems.HAMMER)
                        .input('s', Items.SAND)
                        .input('i', Items.IRON_BLOCK)
                        .criterion(hasItem(ModItems.HAMMER), conditionsFromItem(ModItems.HAMMER))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "MineralAndFossilsRecipeProvider";
    }
}
