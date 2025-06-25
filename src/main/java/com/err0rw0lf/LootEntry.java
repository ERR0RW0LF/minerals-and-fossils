package com.err0rw0lf;

import net.minecraft.item.Item;

public class LootEntry {
    public final Item item;
    public final float chance; // 0.0 - 1.0

    public LootEntry(Item item, float chance) {
        this.item = item;
        this.chance = chance;
    }
}
