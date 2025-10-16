package com.healing;

import com.healing.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealSword implements ModInitializer {
	public static final String MOD_ID = "healsword";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        @Override
        public void onInitialize() {
                ModItems.registerModItems();

                ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, originalDamage, actualDamage, blocked) -> {
                        if (blocked || actualDamage <= 0) {
                                return;
                        }

                        Entity attacker = source.getAttacker();
                        if (!(attacker instanceof PlayerEntity player)) {
                                return;
                        }

                        ItemStack mainHandStack = player.getStackInHand(Hand.MAIN_HAND);
                        if (!mainHandStack.isOf(ModItems.VAMPIRIC_SWORD)) {
                                return;
                        }

                        float healAmount = actualDamage * 0.1f;
                        if (healAmount > 0) {
                                player.heal(healAmount);
                        }
                });

                LOGGER.info("HealSword initialized: vampiric sword registered");
        }
}
