package com.err0rw0lf;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class MineralsAndFossilsBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected MineralsAndFossilsBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.GEMSTONE_WORKSTATION_BLOCK);
        addDrop(ModBlocks.SMALL_CUT_AMETHYST_BLOCK);
        addDrop(ModBlocks.MEDIUM_CUT_AMETHYST_BLOCK);
        addDrop(ModBlocks.BIG_CUT_AMETHYST_BLOCK);
    }
}
