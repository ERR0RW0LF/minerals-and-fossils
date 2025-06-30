package com.err0rw0lf;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class MineralsAndFossilsBlockTagProvider extends FabricTagProvider<Block> {
    public static final TagKey<Block> PICKAXE = TagKey.of(RegistryKeys.BLOCK, Identifier.of("pickaxe"));
    public MineralsAndFossilsBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(PICKAXE)
                .add(ModBlocks.GEMSTONE_WORKSTATION_BLOCK)
                .add(ModBlocks.BIG_CUT_AMETHYST_BLOCK)
                .add(ModBlocks.MEDIUM_CUT_AMETHYST_BLOCK)
                .add(ModBlocks.SMALL_CUT_AMETHYST_BLOCK)
                .setReplace(true);
    }
}
