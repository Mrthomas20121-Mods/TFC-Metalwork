package tfc_metallum.common;

import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.Lazy;
import tfc_metallum.common.blocks.rock.MetallumOre;
import tfc_metallum.common.items.MetallumItems;
import tfc_metallum.util.Metal;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class MetalworkItemGroup extends CreativeModeTab {

    public static final CreativeModeTab METAL = new MetalworkItemGroup("metals", () -> new ItemStack((MetallumItems.METAL_ITEMS.get(Metal.BISMUTH)).get(Metal.ItemType.DUST).get()));
    private final Lazy<ItemStack> iconStack;

    private MetalworkItemGroup(String label, Supplier<ItemStack> iconSupplier) {
        super("tfc_metalwork." + label);
        this.iconStack = Lazy.of(() -> FoodCapability.setStackNonDecaying(iconSupplier.get()));
    }

    @Nonnull
    public ItemStack makeIcon() {
        return this.iconStack.get();
    }
}
