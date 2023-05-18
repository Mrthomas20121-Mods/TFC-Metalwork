package tfc_metalwork;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import tfc_metalwork.common.blocks.MetalworkBlocks;
import tfc_metalwork.common.blocks.entities.TFCMetalworkBlockEntities;
import tfc_metalwork.common.config.TFCMetalworkConfig;
import tfc_metalwork.common.items.MetallumItems;
import tfc_metalwork.common.ClientEvents;

@Mod(TFCMetalwork.mod_id)
public class TFCMetalwork {

	public static final String mod_id = "tfc_metalwork";

	public static final Logger LOGGER = LogUtils.getLogger();

	public TFCMetalwork() {
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MetalworkBlocks.BLOCKS.register(bus);
		MetallumItems.ITEMS.register(bus);
		TFCMetalworkBlockEntities.BLOCK_ENTITIES.register(bus);

		TFCMetalworkConfig.init();

		if (FMLEnvironment.dist == Dist.CLIENT) {
			ClientEvents.init();
		}
	}

}
