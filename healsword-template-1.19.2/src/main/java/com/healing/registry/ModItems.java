package com.healing.registry;

import com.healing.HealSword;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModItems {
        public static final SwordItem VAMPIRIC_SWORD = register("vampiric_sword",
                        new SwordItem(ToolMaterials.IRON, 3, -2.4F, new FabricItemSettings().maxCount(1)));

        private ModItems() {
        }

        public static void registerModItems() {
                ItemGroupEvents.modifyEntriesEvent(ItemGroup.COMBAT)
                                .register(entries -> entries.add(VAMPIRIC_SWORD));
                HealSword.LOGGER.info("Registered mod items");
        }

        private static <T extends Item> T register(String name, T item) {
                return Registry.register(Registries.ITEM, new Identifier(HealSword.MOD_ID, name), item);
        }
}
