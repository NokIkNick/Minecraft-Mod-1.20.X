package net.nokiknick.myoriginalmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.nokiknick.myoriginalmod.MyOriginalMod;
import net.nokiknick.myoriginalmod.block.ModBlocks;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isValuableBlock(state)){
                    outPutValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock){
                assert player != null;
                player.sendSystemMessage(Component.literal("No valuable blocks found"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), (player) -> {
            player.broadcastBreakEvent(pContext.getPlayer().getUsedItemHand());
        });

        return InteractionResult.SUCCESS;
    }

    private void outPutValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.IRON_ORE) || state.is(Blocks.DIAMOND_ORE) || state.is(Blocks.GOLD_ORE) || state.is(Blocks.EMERALD_ORE) || state.is(Blocks.LAPIS_ORE) || state.is(Blocks.REDSTONE_ORE) || state.is(Blocks.COPPER_ORE) || state.is(ModBlocks.GARNET_ORE.get().defaultBlockState().getBlock());
    }
}