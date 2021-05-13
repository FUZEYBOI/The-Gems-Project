package com.foozey.gems.items.shields;

import com.foozey.gems.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class PlatedOnyxShield extends ShieldItem {

    public PlatedOnyxShield(Properties properties) {
        super(properties.stacksTo(1).durability(4000));
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repairWith) {
        return repairWith.getItem() == ModItems.PLATED_ONYX_INGOT.get() || super.isValidRepairItem(toRepair, repairWith);
    }

    @Override
    public boolean isShield(ItemStack stack, LivingEntity entity) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

}