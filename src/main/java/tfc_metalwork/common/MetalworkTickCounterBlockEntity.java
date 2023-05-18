package tfc_metalwork.common;

import net.dries007.tfc.common.blockentities.TFCBlockEntity;
import net.dries007.tfc.util.calendar.Calendars;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import tfc_metalwork.common.blocks.entities.TFCMetalworkBlockEntities;

public class MetalworkTickCounterBlockEntity extends TFCBlockEntity {

    protected long lastUpdateTick;

    public static void reset(Level level, BlockPos pos)
    {
        level.getBlockEntity(pos, TFCMetalworkBlockEntities.TICK_COUNTER.get()).ifPresent(MetalworkTickCounterBlockEntity::resetCounter);
    }

    public MetalworkTickCounterBlockEntity(BlockPos pos, BlockState state) {
        this(TFCMetalworkBlockEntities.TICK_COUNTER.get(), pos, state);
    }

    protected MetalworkTickCounterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.lastUpdateTick = -2147483648L;
    }

    public long getTicksSinceUpdate() {
        assert this.level != null;

        return Calendars.get(this.level).getTicks() - this.lastUpdateTick;
    }

    public void setLastUpdateTick(long tick) {
        this.lastUpdateTick = tick;
        this.setChanged();
    }

    public long getLastUpdateTick() {
        return this.lastUpdateTick;
    }

    public void resetCounter() {
        this.lastUpdateTick = Calendars.SERVER.getTicks();
        this.setChanged();
    }

    public void reduceCounter(long amount) {
        this.lastUpdateTick += amount;
        this.setChanged();
    }

    public void loadAdditional(CompoundTag nbt) {
        this.lastUpdateTick = nbt.getLong("tick");
        super.loadAdditional(nbt);
    }

    public void saveAdditional(CompoundTag nbt) {
        nbt.putLong("tick", this.lastUpdateTick);
        super.saveAdditional(nbt);
    }
}
