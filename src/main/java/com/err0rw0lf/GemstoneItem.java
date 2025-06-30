package com.err0rw0lf;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GemstoneItem extends Item {
    public final boolean isCut;
    public final Item result;

    public GemstoneItem(boolean isCut, Item result,Item.Settings settings) {
        super(settings);
        this.result = result;
        this.isCut = isCut;
    }
}
