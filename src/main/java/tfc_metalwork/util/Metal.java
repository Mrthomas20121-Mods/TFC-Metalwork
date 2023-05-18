package tfc_metalwork.util;

import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import tfc_metalwork.common.blocks.MetalBlock;

import java.util.function.Supplier;

public enum Metal {
    BISMUTH,
    BISMUTh_BRONZE,
    BLACK_BRONZE,
    BRASS,
    BRONZE,
    COPPER,
    GOLD,
    NICKEL,
    ROSE_GOLD,
    SILVER,
    TIN,
    ZINC,
    STERLING_SILVER,
    WROUGHT_IRON,
    CAST_IRON,
    STEEL,
    BLACK_STEEL,
    BLUE_STEEL,
    RED_STEEL,
    ANDESITE_ALLOY,
    ANTIMONY,
    ALNICO,
    ALUMINUM,
    BORON,
    BERYLLIUM,
    BERYLLIUM_COPPER,
    BLUTONIUM,
    CONSTANTAN,
    COBALT,
    COMPRESSED_IRON,
    ELECTRUM,
    PLATINUM,
    ENDERIUM,
    FERROBORON,
    FLORENTINE_BRONZE,
    GRAPHITE,
    INVAR,
    IRIDIUM,
    LEAD,
    LUMIUM,
    MITHRIL,
    NICKEL_SILVER,
    OSMIUM,
    OSMIRIDIUM,
    PEWTER,
    PINK_SLIME,
    REFINED_GLOWSTONE,
    REFINED_OBSIDIAN,
    SIGNALUM,
    SOLDER,
    THORIUM,
    TITANIUM,
    TUNGSTEN,
    TUNGSTEN_STEEL,
    URANIUM,
    CHROMIUM,
    STAINLESS_STEEL;

    public enum BlockType {
        BLOCK(true),
        CUT(true),
        OXIDIZED(true, () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0.4f))),
        OXIDIZED_CUT(true, () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0.4f))),
        LADDER(false, () -> new LadderBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.4F).sound(SoundType.LADDER).noOcclusion()));

        private final Supplier<Block> blockSupplier;
        private final boolean full;

        BlockType(boolean full, Supplier<Block> supplier) {
            this.blockSupplier = supplier;
            this.full = full;
        }

        BlockType(boolean full) {
            this(full, null);
        }

        public boolean isFull() {
            return this.full;
        }

        public Supplier<Block> create() {
            return this.blockSupplier;
        }

        public Supplier<Block> createBlock(Metal metal, BlockType type) {
            return () -> new MetalBlock(ExtendedProperties.of(Material.METAL).randomTicks().strength(0.4f).blockEntity(TFCBlockEntities.TICK_COUNTER), metal, type);
        }
    }

    public enum ItemType {
        DUST,
        PLATE,
        LARGE_PLATE,
        SMALL_GEAR,
        LARGE_GEAR,
        LARGE_ROD
    }
}
