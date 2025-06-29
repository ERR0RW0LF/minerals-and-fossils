package com.err0rw0lf;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MineralsAndFossils.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MineralsAndFossils.MOD_ID, name));
    }

    public static final Block BIG_CUT_AMETHYST_BLOCK = register(
            "big_cut_amethyst_block",
            TransparentBlock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.AMETHYST_BLOCK).nonOpaque().allowsSpawning(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never).solidBlock(Blocks::never),
            true
    );

    public static final Block MEDIUM_CUT_AMETHYST_BLOCK = register(
            "medium_cut_amethyst_block",
            TransparentBlock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.AMETHYST_BLOCK).nonOpaque().allowsSpawning(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never).solidBlock(Blocks::never),
            true
    );

    public static final Block SMALL_CUT_AMETHYST_BLOCK = register(
            "small_cut_amethyst_block",
            TransparentBlock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.AMETHYST_BLOCK).nonOpaque().allowsSpawning(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never).solidBlock(Blocks::never),
            true
    );

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.CUSTOM_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ModBlocks.SMALL_CUT_AMETHYST_BLOCK.asItem());
            itemGroup.add(ModBlocks.MEDIUM_CUT_AMETHYST_BLOCK.asItem());
            itemGroup.add(ModBlocks.BIG_CUT_AMETHYST_BLOCK.asItem());
        });
    }
}
