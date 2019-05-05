package me.shedaniel.badanvil;

import me.shedaniel.badanvil.blocks.container.StoneAnvilContainer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.client.gui.container.AnvilScreen;
import net.minecraft.text.TranslatableTextComponent;

public class BadAnvilClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(BadAnvil.STONE_ANVIL_CONTAINER, (syncId, identifier, playerEntity, packetByteBuf) -> {
            return new AnvilScreen(new StoneAnvilContainer(syncId, playerEntity.inventory), playerEntity.inventory, new TranslatableTextComponent("container.repair"));
        });
    }
}
