package tfc_metallum;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import tfc_metallum.common.blocks.MetalworkBlocks;
import tfc_metallum.common.fluids.MetallumFluids;
import tfc_metallum.common.items.MetallumItems;

@Mod(TFCMetalwork.mod_id)
public class TFCMetalwork {

	public static final String mod_id = "tfc_metalwork";

	public static final Logger LOGGER = LogUtils.getLogger();

	public TFCMetalwork() {
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MetalworkBlocks.BLOCKS.register(bus);
		MetallumItems.ITEMS.register(bus);
		MetallumFluids.FLUIDS.register(bus);
	}
}
