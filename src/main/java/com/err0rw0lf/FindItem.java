package com.err0rw0lf;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;


public class FindItem extends Item {
    private final LootTable lootTable;

    public FindItem(LootTable lootTable, Item.Settings settings) {
        super(settings);
        this.lootTable = lootTable;
    }

    public void openFind(ServerWorld world, PlayerEntity player) {
        float totalWeight = 0f;
        for (LootEntry entry : lootTable.lootEntries) {
            totalWeight += entry.chance;
        }

        float roll = world.random.nextFloat() * totalWeight;
        float cumulative = 0f;

        for (LootEntry entry : lootTable.lootEntries) {
            cumulative += entry.chance;
            if (roll < cumulative) {
                ItemStack loot = new ItemStack(entry.item);
                if (!player.getInventory().insertStack(loot.copy())) {
                    player.dropItem(loot,false);
                }
                if (entry.item == Items.AIR) {
                    VisualEffects(world, player);
                }
                break;
            }
        }
    }

    private void VisualEffects(ServerWorld world, PlayerEntity user) {
        world.spawnParticles(ParticleTypes.CLOUD,
                user.getX() + Math.cos(((user.getYaw()+90)/180)*Math.PI)*Math.sin(((user.getPitch()+90)/180)*Math.PI)*1,
                user.getY() + 1.5 + Math.cos(((user.getPitch()+90)/180)*Math.PI)*1,
                user.getZ() + Math.sin(((user.getYaw()+90)/180)*Math.PI)*Math.sin(((user.getPitch()+90)/180)*Math.PI)*1,
                5, 0, 0, 0, 0.2
        );
    }
}
