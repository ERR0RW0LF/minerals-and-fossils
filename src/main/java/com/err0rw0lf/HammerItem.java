package com.err0rw0lf;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
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
        return !miner.isCreative();
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        // Only on server side to avoid double drops
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
            if (user.isSneaking() && mainHand.getItem() instanceof HammerItem && offHand.getItem() instanceof FindItem && offHand.getCount() >= 10) {
                FindItem findItem = (FindItem) offHand.getItem();
                for (int i = 0; i < 10; i++) {
                    findItem.openFind((ServerWorld) world, user);
                    offHand.decrementUnlessCreative(1, user);
                }
            } else if (mainHand.getItem() instanceof HammerItem && offHand.getItem() instanceof FindItem) {
                FindItem findItem = (FindItem) offHand.getItem();
                findItem.openFind((ServerWorld) world, user);
                offHand.decrementUnlessCreative(1, user);
            }
        }
        return super.use(world, user, hand);
    }
}
