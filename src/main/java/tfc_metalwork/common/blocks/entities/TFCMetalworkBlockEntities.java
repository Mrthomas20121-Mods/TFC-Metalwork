package tfc_metalwork.common.blocks.entities;

import net.dries007.tfc.common.blockentities.TickCounterBlockEntity;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import tfc_metalwork.TFCMetalwork;
import tfc_metalwork.common.MetalworkTickCounterBlockEntity;
import tfc_metalwork.common.blocks.MetalworkBlocks;
import tfc_metalwork.util.Metal;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TFCMetalworkBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TFCMetalwork.mod_id);

    public static final RegistryObject<BlockEntityType<MetalworkTickCounterBlockEntity>> TICK_COUNTER = register("tick_counter", MetalworkTickCounterBlockEntity::new, Stream.concat(Stream.concat(
            Arrays.stream(Metal.values()).map(metal -> MetalworkBlocks.METALS.get(metal).get(Metal.BlockType.BLOCK)),
            Arrays.stream(Metal.values()).map(metal -> MetalworkBlocks.BLOCK_DECORATIONS.get(metal).get(Metal.BlockType.BLOCK).slab())
    ),
            Stream.concat(Arrays.stream(Metal.values()).map(metal -> MetalworkBlocks.BLOCK_DECORATIONS.get(metal).get(Metal.BlockType.BLOCK).wall()),
                    Arrays.stream(Metal.values()).map(metal -> MetalworkBlocks.BLOCK_DECORATIONS.get(metal).get(Metal.BlockType.BLOCK).stair()))));

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> block)
    {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block);
    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Stream<? extends Supplier<? extends Block>> blocks)
    {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, blocks);
    }
}
