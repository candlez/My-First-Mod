package com.example.tutorialmod.block.custom;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class JumpyBlock extends Block{
    public JumpyBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPosition, Player player,
            InteractionHand hand, BlockHitResult result) {
        player.sendSystemMessage(Component.literal("You Right Clicked This Block!"));
        return super.use(state, level, blockPosition, player, hand, result);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> components,
            TooltipFlag flag) {

        if (Screen.hasShiftDown()) {
            components.add(Component.literal("Right click to get a random number").withStyle(ChatFormatting.AQUA));
        } else {
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(stack, blockGetter, components, flag);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPosition, BlockState blockState, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200));
        }
        super.stepOn(level, blockPosition, blockState, entity);
    }

}
