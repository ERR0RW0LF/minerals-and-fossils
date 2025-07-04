package com.err0rw0lf;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class MineralsAndFossilsItemTagProvider extends FabricTagProvider<Item> {
    public static final TagKey<Item> SMELLY_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(MineralsAndFossils.MOD_ID, "smelly_items"));
    public MineralsAndFossilsItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(SMELLY_ITEMS)
                .add(Items.SLIME_BALL)
                .add(Items.ROTTEN_FLESH)
                .addOptionalTag(ItemTags.DIRT)
                .add(Identifier.ofVanilla("oak_planks"))
                .forceAddTag(ItemTags.BANNERS)
                .setReplace(true);
    }
}
