package com.foozey.gems.items.rings;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.UUID;

public class EmeraldRing extends Item {

    public EmeraldRing(Properties properties) {
        super(properties.stacksTo(1).durability(250));
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repairWith) {
        return repairWith.getItem() == Items.EMERALD.getItem() || super.isValidRepairItem(toRepair, repairWith);
    }

    public static final UUID RING_MOVEMENT_SPEED_UUID = UUID.fromString("7de255b2-dbcf-11ea-87d0-0242ac130003");

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> lazyCurio = LazyOptional.of(()-> new ICurio() {

                // Right Click Equip
                @Override
                public boolean canRightClickEquip() {
                    return true;
                }

                // Right Click Equip Sound
                @Override
                public void playRightClickEquipSound(LivingEntity livingEntity) {
                    livingEntity.level.playSound(null, new BlockPos(livingEntity.position()), SoundEvents.ARMOR_EQUIP_GOLD, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                }

                // Wearing Event
                @Override
                public Multimap<Attribute, AttributeModifier> getAttributeModifiers(String identifier) {
                    Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
                    modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(RING_MOVEMENT_SPEED_UUID, "Movement Speed", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
                    return modifiers;
                }

            });

            @Override
            public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                return CuriosCapability.ITEM.orEmpty(capability, lazyCurio);
            }

        };
    }

}