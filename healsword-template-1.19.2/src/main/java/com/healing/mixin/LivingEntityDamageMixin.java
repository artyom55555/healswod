package com.healing.mixin;

import com.healing.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDamageMixin {

        @Inject(method = "damage", at = @At("RETURN"))
        private void healsword$onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
                if (!cir.getReturnValue() || amount <= 0.0F) {
                        return;
                }

                Entity attacker = source.getAttacker();
                if (!(attacker instanceof PlayerEntity player)) {
                        return;
                }

                if (player.getWorld().isClient()) {
                        return;
                }

                ItemStack heldItem = player.getStackInHand(Hand.MAIN_HAND);
                if (!heldItem.isOf(ModItems.VAMPIRIC_SWORD)) {
                        return;
                }

                float healAmount = amount * 0.1F;
                if (healAmount > 0.0F) {
                        player.heal(healAmount);
                }
        }
}
