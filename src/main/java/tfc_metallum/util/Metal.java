package tfc_metallum.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

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
    PIG_IRON,
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
        BLOCK(() -> new Block(BlockBehaviour.Properties.of(Material.METAL))),
        CUT(() -> new Block(BlockBehaviour.Properties.of(Material.METAL))),
        LADDER(() -> new LadderBlock(BlockBehaviour.Properties.of(Material.METAL)));

        private final Supplier<Block> blockSupplier;

        BlockType(Supplier<Block> supplier) {
            this.blockSupplier = supplier;
        }

        public Supplier<Block> create() {
            return this.blockSupplier;
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
