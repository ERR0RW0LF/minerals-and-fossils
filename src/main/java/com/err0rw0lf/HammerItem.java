package com.err0rw0lf;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HammerItem extends MiningToolItem {
    public HammerItem(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        super(material, BlockTags.PICKAXE_MINEABLE, attackDamage, attackSpeed, settings);
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !state.isOf(Blocks.BUDDING_AMETHYST) && !miner.isCreative();
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        // Only on the server side to avoid double drops
        if (!world.isClient) {
            // Check if the mined block is stone (or your specific stone block)
            if (state.isOf(Blocks.STONE)) {
                // 25% chance to drop (change as desired)
                if (world.random.nextFloat() < 0.25f) {
                    // Drop your "stone find" item at the block's position
                    ItemEntity drop = new ItemEntity(
                            world,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            new ItemStack(ModItems.STONE_FIND)
                    );
                    world.spawnEntity(drop);
                }
            } else if (state.isOf(Blocks.SANDSTONE)) {
                // 15 % chance to drop
                if (world.random.nextFloat() < 0.15f) {
                    // Drop sand find item at the block's position
                    ItemEntity drop = new ItemEntity(
                            world,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            new ItemStack(ModItems.SAND_FIND)
                    );
                    world.spawnEntity(drop);
                }
            } else if (state.isOf(Blocks.AMETHYST_CLUSTER)) {
                // 15 % chance to drop an extra item
                if (world.random.nextFloat() < 0.15f) {
                    // Get the drop from Amethyst Find Loot table
                    LootTable lootTable = ModItems.AMETHYST_FIND_LOOT;
                    float totalWeight = 0f;
                    for (LootEntry entry : ModItems.AMETHYST_FIND_LOOT.lootEntries) {
                        totalWeight += entry.chance;
                    }

                    float roll = world.random.nextFloat() * totalWeight;
                    float cumulative = 0f;

                    for (LootEntry entry : lootTable.lootEntries) {
                        cumulative += entry.chance;
                        if (roll < cumulative) {
                            ItemEntity drop = new ItemEntity(
                                    world,
                                    pos.getX() + 0.5,
                                    pos.getY() + 0.5,
                                    pos.getZ() + 0.5,
                                    new ItemStack(entry.item)
                            );
                            world.spawnEntity(drop);
                            break;
                        }
                    }
                }
            } else if (state.isOf(Blocks.IRON_ORE) || state.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
                // 10% chance to drop Almandine
                if (world.random.nextFloat() < 0.1f) {
                    // Drop Almandine item at the block's position
                    ItemEntity drop = new ItemEntity(
                            world,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            new ItemStack(ModItems.UNCUT_ALMANDINE)
                    );
                    world.spawnEntity(drop);
                }
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        //MineralsAndFossils.LOGGER.info("using Hammer");
        ItemStack mainHand = user.getMainHandStack();
        ItemStack offHand = user.getOffHandStack();

        // check if player is sneaking
        if (!world.isClient) {
            if (user.isSneaking() && mainHand.getItem() instanceof HammerItem && offHand.getItem() instanceof FindItem findItem && offHand.getCount() >= 10) {
                for (int i = 0; i < 10; i++) {
                    findItem.openFind((ServerWorld) world, user);
                    offHand.decrementUnlessCreative(1, user);
                    mainHand.damage(1, user, EquipmentSlot.MAINHAND);
                }
                //  VisualEffects(world, user);
            } else if (mainHand.getItem() instanceof HammerItem && offHand.getItem() instanceof FindItem findItem) {
                findItem.openFind((ServerWorld) world, user);
                offHand.decrementUnlessCreative(1, user);
                mainHand.damage(1, user, EquipmentSlot.MAINHAND);
                //VisualEffects(world, user);
            }
            String userPitch = "Pitch: " + user.getPitch();
            String userYaw = "Yaw: " + user.getYaw();

            // MineralsAndFossils.LOGGER.info(userPitch);
            // MineralsAndFossils.LOGGER.info(userYaw);
        }
        return super.use(world, user, hand);
    }

    private void VisualEffects(World world, PlayerEntity user) {
        ((ServerWorld) world).spawnParticles(ParticleTypes.CLOUD,
                user.getX() + Math.cos(((user.getYaw()+90)/180)*Math.PI)*Math.sin(((user.getPitch()+90)/180)*Math.PI)*1,
                user.getY() + 1.5 + Math.cos(((user.getPitch()+90)/180)*Math.PI)*1,
                user.getZ() + Math.sin(((user.getYaw()+90)/180)*Math.PI)*Math.sin(((user.getPitch()+90)/180)*Math.PI)*1,
                10, 0, 0, 0, 0.2
        );
    }
}
