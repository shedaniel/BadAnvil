package me.shedaniel.badanvil.mixin;

import net.minecraft.container.AnvilContainer;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Property;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AnvilContainer.class)
public interface AnvilContainerHooks {
    @Accessor("result")
    Inventory getResult();
    
    @Accessor("levelCost")
    Property getLevelCost();
    
    @Accessor("field_7776")
    int field_7776();
    
    @Accessor("context")
    BlockContext getContext();
    
    @Accessor("inventory")
    Inventory getInventory();
}
