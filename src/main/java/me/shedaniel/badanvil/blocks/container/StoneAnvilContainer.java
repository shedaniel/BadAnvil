package me.shedaniel.badanvil.blocks.container;

import me.shedaniel.badanvil.BadAnvil;
import me.shedaniel.badanvil.mixin.AnvilContainerHooks;
import net.minecraft.block.BlockState;
import net.minecraft.container.AnvilContainer;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class StoneAnvilContainer extends AnvilContainer {
    
    public StoneAnvilContainer(int int_1, PlayerInventory playerInventory_1) {
        this(int_1, playerInventory_1, BlockContext.EMPTY);
    }
    
    public StoneAnvilContainer(int int_1, PlayerInventory inventory, BlockContext blockContext) {
        super(int_1, inventory, blockContext);
        Slot slot;
        slotList.set(2, slot = new Slot(((AnvilContainerHooks) this).getResult(), 2, 134, 47) {
            public boolean canInsert(ItemStack itemStack_1) {
                return false;
            }
            
            public boolean canTakeItems(PlayerEntity playerEntity_1) {
                return (playerEntity_1.abilities.creativeMode || playerEntity_1.experience >= ((AnvilContainerHooks) StoneAnvilContainer.this).getLevelCost().get()) && ((AnvilContainerHooks) StoneAnvilContainer.this).getLevelCost().get() > 0 && this.hasStack();
            }
            
            public ItemStack onTakeItem(PlayerEntity playerEntity_1, ItemStack itemStack_1) {
                if (!playerEntity_1.abilities.creativeMode) {
                    playerEntity_1.method_7316(-((AnvilContainerHooks) StoneAnvilContainer.this).getLevelCost().get());
                }
                
                ((AnvilContainerHooks) StoneAnvilContainer.this).getInventory().setInvStack(0, ItemStack.EMPTY);
                if (((AnvilContainerHooks) StoneAnvilContainer.this).field_7776() > 0) {
                    ItemStack itemStack_2 = ((AnvilContainerHooks) StoneAnvilContainer.this).getInventory().getInvStack(1);
                    if (!itemStack_2.isEmpty() && itemStack_2.getAmount() > ((AnvilContainerHooks) StoneAnvilContainer.this).field_7776()) {
                        itemStack_2.subtractAmount(((AnvilContainerHooks) StoneAnvilContainer.this).field_7776());
                        ((AnvilContainerHooks) StoneAnvilContainer.this).getInventory().setInvStack(1, itemStack_2);
                    } else {
                        ((AnvilContainerHooks) StoneAnvilContainer.this).getInventory().setInvStack(1, ItemStack.EMPTY);
                    }
                } else {
                    ((AnvilContainerHooks) StoneAnvilContainer.this).getInventory().setInvStack(1, ItemStack.EMPTY);
                }
                
                ((AnvilContainerHooks) StoneAnvilContainer.this).getLevelCost().set(0);
                ((AnvilContainerHooks) StoneAnvilContainer.this).getContext().run((world_1, blockPos_1) -> {
                    BlockState blockState_1 = world_1.getBlockState(blockPos_1);
                    if (blockState_1.getBlock() == BadAnvil.STONE_ANVIL) {
                        world_1.clearBlockState(blockPos_1, false);
                        world_1.playLevelEvent(1029, blockPos_1, 0);
                    } else {
                        world_1.playLevelEvent(1030, blockPos_1, 0);
                    }
                });
                return itemStack_1;
            }
        });
        slot.id = 2;
    }
    
    @Override
    public boolean canUse(PlayerEntity playerEntity_1) {
        return (Boolean) ((AnvilContainerHooks) this).getContext().run((world_1, blockPos_1) -> {
            return world_1.getBlockState(blockPos_1).getBlock() != BadAnvil.STONE_ANVIL ? false : playerEntity_1.squaredDistanceTo((double) blockPos_1.getX() + 0.5D, (double) blockPos_1.getY() + 0.5D, (double) blockPos_1.getZ() + 0.5D) <= 64.0D;
        }, true);
    }
    
}
