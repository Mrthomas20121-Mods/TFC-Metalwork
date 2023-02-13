package tfc_metalwork.common;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tfc_metalwork.common.blocks.MetalworkBlocks;

public class ClientEvents {

    public static void init() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(ClientEvents::clientSetup);
    }

    public static void clientSetup(FMLClientSetupEvent event) {

        final RenderType cutout = RenderType.cutout();

        MetalworkBlocks.METALS.values().forEach(inner -> inner.values().forEach(b -> ItemBlockRenderTypes.setRenderLayer(b.get(), cutout)));
    }
}
