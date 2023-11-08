package tfc_metalwork.common.blocks;

import net.dries007.tfc.common.blocks.DecorationBlockRegistryObject;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tfc_metalwork.TFCMetalwork;
import tfc_metalwork.common.MetalworkItemGroup;
import tfc_metalwork.common.blocks.entities.TFCMetalworkBlockEntities;
import tfc_metalwork.util.Metal;
import tfc_metalwork.common.items.MetallumItems;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class MetalworkBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TFCMetalwork.mod_id);

    public static final Map<Metal, Map<Metal.BlockType, RegistryObject<Block>>> METALS = Helpers.mapOfKeys(Metal.class, metal ->
            Helpers.mapOfKeys(Metal.BlockType.class, type ->
                    register("metal/" + type.name() + "/" + metal.name(), type.create() == null ? type.createBlock(metal, type): type.create(), MetalworkItemGroup.METAL))
    );

    public static final Map<Metal, Map<Metal.BlockType, DecorationBlockRegistryObj>> BLOCK_DECORATIONS = Helpers.mapOfKeys(Metal.class, metal -> Helpers.mapOfKeys(Metal.BlockType.class, Metal.BlockType::isFull, blockType -> {
        // normal block if oxidized
        if(blockType.name().contains("oxidized")) {
            return new DecorationBlockRegistryObj(
                    register(String.format("metal/%s/%s_slab", blockType.name().toLowerCase(), metal.name()), () -> new SlabBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2f)), MetalworkItemGroup.METAL),
                    register(String.format("metal/%s/%s_stairs", blockType.name().toLowerCase(), metal.name()), () -> new StairBlock(() -> METALS.get(metal).get(blockType).get().defaultBlockState(), BlockBehaviour.Properties.of(Material.METAL).strength(2f)), MetalworkItemGroup.METAL),
                    register(String.format("metal/%s/%s_wall", blockType.name().toLowerCase(), metal.name()), () -> new WallBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2f)), MetalworkItemGroup.METAL)
            );
        }
        else {
            return new DecorationBlockRegistryObj(
                    register(String.format("metal/%s/%s_slab", blockType.name().toLowerCase(), metal.name()), () -> new MetalSlab(ExtendedProperties.of(Material.METAL).blockEntity(TFCMetalworkBlockEntities.TICK_COUNTER).strength(2f), metal, blockType), MetalworkItemGroup.METAL),
                    register(String.format("metal/%s/%s_stairs", blockType.name().toLowerCase(), metal.name()), () -> new MetalStairs(() -> METALS.get(metal).get(blockType).get().defaultBlockState(), ExtendedProperties.of(Material.METAL).blockEntity(TFCMetalworkBlockEntities.TICK_COUNTER).strength(2f), metal, blockType), MetalworkItemGroup.METAL),
                    register(String.format("metal/%s/%s_wall", blockType.name().toLowerCase(), metal.name()), () -> new MetalWalls(ExtendedProperties.of(Material.METAL).strength(2f).blockEntity(TFCMetalworkBlockEntities.TICK_COUNTER), metal, blockType), MetalworkItemGroup.METAL)
            );
        }
    }));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, CreativeModeTab group)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(group)));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return RegistrationHelpers.registerBlock(BLOCKS, MetallumItems.ITEMS, name, blockSupplier, blockItemFactory);
    }
}
