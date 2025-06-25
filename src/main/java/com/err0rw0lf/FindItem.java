package com.err0rw0lf;

import net.minecraft.item.Item;


public class FindItem extends Item {
    public FindItem(Item.Settings settings) {
        super(settings);
    }

    public void openFind() {
        MineralsAndFossils.LOGGER.info("Opened Find Item");
    }
}
