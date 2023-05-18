package tfc_metalwork.common.blocks;

import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import tfc_metalwork.common.MetalworkTickCounterBlockEntity;
import tfc_metalwork.common.blocks.entities.TFCMetalworkBlockEntities;
import tfc_metalwork.util.Metal;

import java.util.Random;
import java.util.function.Supplier;

public class MetalStairs extends StairBlock implements OxidableBlock {

    private final ExtendedProperties extended;

    private final Metal metal;

    private final Metal.BlockType blockType;

    public MetalStairs(Supplier<BlockState> metalBlock, ExtendedProperties extended, Metal metal, Metal.BlockType type) {
        super(metalBlock, extended.properties());
        this.extended = extended;
        this.blockType = type;
        this.metal = metal;
    }

    @Override
    public Metal getMetal() {
        return metal;
    }

    @Override
    public Metal.BlockType getBlockType() {
        return this.blockType;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
        this.oxidizeOther(state, level, pos, rand);
    }

    @Override
    public ExtendedProperties getExtendedProperties() {
        return this.extended;
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.getBlockEntity(pos, TFCMetalworkBlockEntities.TICK_COUNTER.get()).ifPresent(MetalworkTickCounterBlockEntity::resetCounter);
        super.setPlacedBy(level, pos, state, placer, stack);
    }
}
