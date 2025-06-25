package com.err0rw0lf;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.*;

import java.util.Arrays;
import java.util.function.Function;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MineralsAndFossils.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static final ToolMaterial GUIDITE_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            455,
            5.0F,
            1.5F,
            22,
            ToolMaterial.IRON.repairItems()
    );

    public static final Item HAMMER = register("hammer", settings -> new HammerItem(GUIDITE_TOOL_MATERIAL, 1f, 1f, settings), new Item.Settings());

    public static final Item HAMMER_HEAD = register("hammer_head", Item::new, new Item.Settings());

    public static final LootTable STONE_FIND_LOOT = new LootTable(Arrays.asList(
            new LootEntry(Items.DIAMOND, 0.01f),
            new LootEntry(Items.COAL, 0.5f),
            new LootEntry(Items.EMERALD, 0.005f)
    ));

    public static final Item STONE_FIND = register(
            "stone_find",
            settings -> new FindItem(STONE_FIND_LOOT, settings),
            new Item.Settings()
    );

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((itemGroup) -> itemGroup.add(ModItems.HAMMER));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> itemGroup.add(ModItems.HAMMER_HEAD));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> itemGroup.add(ModItems.STONE_FIND));
    }

}
