package tfc_metalwork.common.blocks;

import net.dries007.tfc.common.blocks.DecorationBlockRegistryObject;
import net.dries007.tfc.common.blocks.EntityBlockExtension;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import tfc_metalwork.common.blocks.entities.TFCMetalworkBlockEntities;
import tfc_metalwork.common.config.TFCMetalworkConfig;
import tfc_metalwork.util.Metal;

import java.util.Random;

public interface OxidableBlock extends IForgeBlockExtension, EntityBlockExtension {

    Metal getMetal();

    Metal.BlockType getBlockType();

    default void onRandomTick(ServerLevel level, BlockPos pos, BlockState placeState) {
        level.getBlockEntity(pos, TFCMetalworkBlockEntities.TICK_COUNTER.get()).ifPresent((block) -> {
            int blockTicks = TFCMetalworkConfig.SERVER.blockTicks.get();
            if (block.getTicksSinceUpdate() > (long)blockTicks && blockTicks > 0) {
                level.setBlockAndUpdate(pos, placeState);
            }
        });
    }

    default void oxidizeBlock(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random rand) {
        onRandomTick(level, pos, Helpers.copyProperties(state, MetalworkBlocks.METALS.get(this.getMetal()).get(this.getBlockType()).get().defaultBlockState()));
    }

    default void oxidizeOther(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random rand) {
        String name = ForgeRegistries.BLOCKS.getKey(state.getBlock()).getPath();
        DecorationBlockRegistryObj object = MetalworkBlocks.BLOCK_DECORATIONS.get(this.getMetal()).get(this.getBlockType());

        if(name.contains("stair")) {
            onRandomTick(level, pos, Helpers.copyProperties(state, object.stair().get().defaultBlockState()));
        }
        else if(name.contains("slab")) {
            onRandomTick(level, pos, Helpers.copyProperties(state, object.slab().get().defaultBlockState()));
        }
        else if(name.contains("wall")) {
            onRandomTick(level, pos, Helpers.copyProperties(state, object.wall().get().defaultBlockState()));
        }
    }
}
