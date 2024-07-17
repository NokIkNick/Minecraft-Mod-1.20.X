package net.nokiknick.myoriginalmod.block.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoundBlock extends Block {
    public SoundBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        Player nearestPlayer = pLevel.getNearestPlayer(pPos.getX(), pPos.getY(), pPos.getZ(), 10, false);
        BlockState blockState = pLevel.getBlockState(pPos.below());
        assert nearestPlayer != null;
        nearestPlayer.sendSystemMessage(Component.literal("The sound block has registered the sound of: ").append(Component.literal(I18n.get(blockState.getBlock().getDescriptionId()))));

        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
    }



    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        Block blockBelow = pLevel.getBlockState(pPos.below()).getBlock();

        if(blockBelow.getDescriptionId().equals("block.myoriginalmod.sound_block")){
            blockBelow.use(pState, pLevel, pPos.below(), pPlayer, pHand, pHit);
            return InteractionResult.SUCCESS;
        }

        SoundEvent soundEvent = pLevel.getBlockState(pPos.below()).getBlock().getSoundType(pLevel.getBlockState(pPos.below())).getPlaceSound();


        pLevel.playSound(pPlayer, pPos, soundEvent, SoundSource.BLOCKS, 1f, 1f);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("block.myoriginalmod.sound_block.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
