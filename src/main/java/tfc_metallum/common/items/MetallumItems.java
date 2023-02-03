package tfc_metallum.common.items;

import net.dries007.tfc.util.Helpers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tfc_metallum.TFCMetalwork;
import tfc_metallum.common.MetalworkItemGroup;
import tfc_metallum.util.Metal;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class MetallumItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TFCMetalwork.mod_id);

    public static final Map<Metal, Map<Metal.ItemType, RegistryObject<Item>>> METAL_ITEMS = Helpers.mapOfKeys(Metal.class, metal ->
            Helpers.mapOfKeys(Metal.ItemType.class, type ->
                    register("metal/" + type.name().toLowerCase() + "/" + metal.name().toLowerCase(), MetalworkItemGroup.METAL)
            )
    );

    private static RegistryObject<Item> register(String name, CreativeModeTab group)
    {
        return register(name, () -> new Item(new Item.Properties().tab(group)));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
