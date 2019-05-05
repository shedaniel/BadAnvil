package me.shedaniel.badanvil;

import me.shedaniel.badanvil.blocks.StoneAnvilBlock;
import me.shedaniel.badanvil.blocks.container.StoneAnvilContainer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.tag.FabricItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.container.BlockContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BadAnvil implements ModInitializer {
    
    public static final Block STONE_ANVIL = new StoneAnvilBlock(FabricBlockSettings.of(Material.ANVIL, MaterialColor.STONE).strength(5.0F, 1200.0F).drops(new Identifier("badanvil", "blocks/stone_anvil")).breakByTool(FabricItemTags.PICKAXES, 1).sounds(BlockSoundGroup.ANVIL).build());
    public static final Identifier STONE_ANVIL_CONTAINER = new Identifier("badanvil", "stone_anvil");
    
    @Override
    public void onInitialize() {
        registerBlock(STONE_ANVIL, "stone_anvil", ItemGroup.DECORATIONS);
        ContainerProviderRegistry.INSTANCE.registerFactory(STONE_ANVIL_CONTAINER, (syncId, identifier, playerEntity, packetByteBuf) -> {
            return new StoneAnvilContainer(syncId, playerEntity.inventory, BlockContext.create(playerEntity.world, packetByteBuf.readBlockPos()));
        });
    }
    
    private void registerBlock(Block block, String name, ItemGroup itemGroup) {
        Registry.register(Registry.BLOCK, new Identifier("badanvil", name), block);
        Registry.register(Registry.ITEM, new Identifier("badanvil", name), new BlockItem(block, new Item.Settings().itemGroup(itemGroup)));
    }
    
}
