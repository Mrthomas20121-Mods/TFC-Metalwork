package tfc_metalwork.common.blocks;

import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tfc_metalwork.common.MetalworkTickCounterBlockEntity;
import tfc_metalwork.common.blocks.entities.TFCMetalworkBlockEntities;
import tfc_metalwork.util.Metal;

import java.util.Random;

public class MetalBlock extends Block implements OxidableBlock {

    private final ExtendedProperties properties;

    private final Metal metal;

    private final Metal.BlockType blockType;

    public MetalBlock(ExtendedProperties properties, Metal metal, Metal.BlockType type) {
        super(properties.properties());
        this.properties = properties;
        this.blockType = this.getBlockType(type);
        this.metal = metal;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
        this.oxidizeBlock(state, level, pos, rand);
    }

    private Metal.BlockType getBlockType(Metal.BlockType baseType) {
        Metal.BlockType type = baseType;
        if(baseType.equals(Metal.BlockType.BLOCK)) {
            type = Metal.BlockType.OXIDIZED;
        }
        else if(baseType.equals(Metal.BlockType.CUT)) {
            type = Metal.BlockType.OXIDIZED_CUT;
        }

        return type;
    }

    @Override
    public @NotNull ExtendedProperties getExtendedProperties() {
        return properties;
    }

    @Override
    public Metal getMetal() {
        return this.metal;
    }

    @Override
    public Metal.BlockType getBlockType() {
        return this.blockType;
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.getBlockEntity(pos, TFCMetalworkBlockEntities.TICK_COUNTER.get()).ifPresent(MetalworkTickCounterBlockEntity::resetCounter);
        super.setPlacedBy(level, pos, state, placer, stack);
    }
}
