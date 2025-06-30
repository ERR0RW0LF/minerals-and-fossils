package com.err0rw0lf;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemstoneWorkstationBlock extends Block {
    public static final MapCodec<GemstoneWorkstationBlock> CODEC = createCodec(GemstoneWorkstationBlock::new);
    private static final Text TITLE = Text.translatable("container.working");

    public MapCodec<? extends GemstoneWorkstationBlock> getCodec() {
        return CODEC;
    }

    public GemstoneWorkstationBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack mainHand = player.getMainHandStack();
        if (!world.isClient) {
            if (mainHand.getItem() instanceof GemstoneItem gemstoneItem) {
                if (!gemstoneItem.isCut) {
                    MineralsAndFossils.LOGGER.info(gemstoneItem.result.getName().getString());
                    if (gemstoneItem.result != null) {
                        mainHand.decrement(1);
                        ItemEntity drop = new ItemEntity(
                                world,
                                pos.getX() + 0.5,
                                pos.getY() + 1.5,
                                pos.getZ() + 0.5,
                                new ItemStack(gemstoneItem.result)
                        );
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD,
                                pos.getX() + 0.5,
                                pos.getY() + 1.5,
                                pos.getZ() + 0.5,
                                5, 0, 0, 0, 0.05
                        );
                        ((ServerWorld) world).spawnParticles(ParticleTypes.ELECTRIC_SPARK,
                                pos.getX() + 0.5,
                                pos.getY() + 1.5,
                                pos.getZ() + 0.5,
                                5, 0, 0, 0, 0.05
                        );
                        world.spawnEntity(drop);
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }
}
