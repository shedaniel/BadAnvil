package me.shedaniel.badanvil.blocks;

import me.shedaniel.badanvil.BadAnvil;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class StoneAnvilBlock extends AnvilBlock {
    
    public StoneAnvilBlock(Settings settings) {
        super(settings);
    }
    
    @Override
    public boolean activate(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (world.isClient)
            return true;
        ContainerProviderRegistry.INSTANCE.openContainer(BadAnvil.STONE_ANVIL_CONTAINER, player, buf -> buf.writeBlockPos(pos));
        return true;
    }
    
    @Override
    public void buildTooltip(ItemStack itemStack_1, BlockView blockView_1, List<TextComponent> list_1, TooltipContext tooltipContext_1) {
        super.buildTooltip(itemStack_1, blockView_1, list_1, tooltipContext_1);
        list_1.add(new TranslatableTextComponent("text.badanvil.one_use_only").applyFormat(TextFormat.GRAY));
    }
    
}
