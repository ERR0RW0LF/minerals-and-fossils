package com.err0rw0lf;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

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

    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(MineralsAndFossils.MOD_ID, "item_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.AMETHYST_FIND))
            .displayName(Text.translatable("itemGroup.minerals_and_fossils"))
            .build();

    public static final ToolMaterial GUIDITE_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            128,
            5.0F,
            1.5F,
            22,
            ToolMaterial.IRON.repairItems()
    );

    public static final Item HAMMER = register("hammer", settings -> new HammerItem(GUIDITE_TOOL_MATERIAL, 1f, 1f, settings), new Item.Settings());

    public static final Item HAMMER_HEAD = register("hammer_head", Item::new, new Item.Settings());


    // Fossils

    public static final LootTable FOSSIL_FIND_LOOT = new LootTable(Arrays.asList(
            new LootEntry(Items.IRON_BLOCK, 1.0f) // TODO: Replace later with fossils
    ));

    public static final Item FOSSIL_FIND = register(
            "fossil_find",
            settings -> new FindItem(FOSSIL_FIND_LOOT, settings),
            new Item.Settings()
    );






    // Amethyst Items
    public static final Item SMALL_CUT_AMETHYST = register("small_cut_amethyst", settings -> new GemstoneItem(true, Items.AIR, settings), new Item.Settings());
    public static final Item MEDIUM_CUT_AMETHYST = register("medium_cut_amethyst", settings -> new GemstoneItem(true, Items.AIR, settings), new Item.Settings());
    public static final Item BIG_CUT_AMETHYST = register("big_cut_amethyst", settings -> new GemstoneItem(true, Items.AIR, settings), new Item.Settings());

    public static final Item SMALL_UNCUT_AMETHYST = register("small_uncut_amethyst", settings -> new GemstoneItem(false, ModItems.SMALL_CUT_AMETHYST, settings), new Item.Settings());
    public static final Item MEDIUM_UNCUT_AMETHYST = register("medium_uncut_amethyst", settings -> new GemstoneItem(false, ModItems.MEDIUM_CUT_AMETHYST, settings), new Item.Settings());
    public static final Item BIG_UNCUT_AMETHYST = register("big_uncut_amethyst", settings -> new GemstoneItem(false, ModItems.BIG_CUT_AMETHYST, settings), new Item.Settings());
    public static final Item AMETHYST_DUST = register("amethyst_dust", Item::new, new Item.Settings());



    public static final LootTable AMETHYST_FIND_LOOT = new LootTable(Arrays.asList(
            new LootEntry(Items.AMETHYST_SHARD, 0.5f),
            new LootEntry(ModItems.SMALL_UNCUT_AMETHYST, 0.3f),
            new LootEntry(ModItems.MEDIUM_UNCUT_AMETHYST, 0.1f),
            new LootEntry(ModItems.BIG_UNCUT_AMETHYST, 0.05f),
            new LootEntry(ModItems.AMETHYST_DUST, 0.05f)
    ));

    public static final Item AMETHYST_FIND = register(
            "amethyst_find",
            settings -> new FindItem(AMETHYST_FIND_LOOT, settings),
            new Item.Settings()
    );

    // Almandine:
    public static final Item CUT_ALMANDINE = register("cut_almandine", settings -> new GemstoneItem(true, Items.AIR, settings), new Item.Settings());

    public static final Item UNCUT_ALMANDINE = register("uncut_almandine", settings -> new GemstoneItem(false, ModItems.CUT_ALMANDINE, settings), new Item.Settings());


    public static final LootTable STONE_FIND_LOOT = new LootTable(Arrays.asList(
            new LootEntry(Items.DIAMOND, 0.01f),
            new LootEntry(ModItems.AMETHYST_FIND, 0.01f),
            new LootEntry(ModItems.UNCUT_ALMANDINE, 0.01f),
            new LootEntry(ModItems.FOSSIL_FIND, 0.01f),
            new LootEntry(Items.COAL, 0.5f),
            new LootEntry(Items.EMERALD, 0.005f),
            new LootEntry(Items.AIR, 0.41f)
    ));

    public static final Item STONE_FIND = register(
            "stone_find",
            settings -> new FindItem(STONE_FIND_LOOT, settings),
            new Item.Settings()
    );


    // Garnet:
    // - Almandine
    // - Pyrope
    // - Spessartine
    // - Grossular
    // - Demantoid



    public static final LootTable SAND_FIND_LOOT = new LootTable(Arrays.asList(
            new LootEntry(Items.GOLD_NUGGET, 0.2f),
            new LootEntry(Items.IRON_NUGGET, 0.3f),
            new LootEntry(Items.AIR, 0.5f)
    ));

    public static final Item SAND_FIND = register(
            "sand_find",
            settings -> new FindItem(SAND_FIND_LOOT, settings),
            new Item.Settings()
    );

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.HAMMER_HEAD);
            itemGroup.add(ModItems.HAMMER);
            itemGroup.add(ModItems.STONE_FIND);
            itemGroup.add(ModItems.SAND_FIND);
            itemGroup.add(ModItems.FOSSIL_FIND);
            itemGroup.add(ModItems.AMETHYST_FIND);
            itemGroup.add(ModItems.SMALL_UNCUT_AMETHYST);
            itemGroup.add(ModItems.MEDIUM_UNCUT_AMETHYST);
            itemGroup.add(ModItems.BIG_UNCUT_AMETHYST);
            itemGroup.add(ModItems.AMETHYST_DUST);
            itemGroup.add(ModItems.SMALL_CUT_AMETHYST);
            itemGroup.add(ModItems.MEDIUM_CUT_AMETHYST);
            itemGroup.add(ModItems.BIG_CUT_AMETHYST);
            itemGroup.add(ModItems.UNCUT_ALMANDINE);
            itemGroup.add(ModItems.CUT_ALMANDINE);
        });
    }

}
