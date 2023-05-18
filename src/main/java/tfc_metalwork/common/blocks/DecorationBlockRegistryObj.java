package tfc_metalwork.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.registries.RegistryObject;

/**
 * Dumbed down version of {@link net.dries007.tfc.common.blocks.DecorationBlockRegistryObject}
 * Because I do not need to know if the block is a slabBlock/WallBlock/StairBlock.
 * @param slab slab block
 * @param stair stairs block
 * @param wall wall block
 */
public record DecorationBlockRegistryObj(RegistryObject<Block> slab, RegistryObject<Block> stair, RegistryObject<Block> wall) {
    public DecorationBlockRegistryObj(RegistryObject<Block> slab, RegistryObject<Block> stair, RegistryObject<Block> wall) {
        this.slab = slab;
        this.stair = stair;
        this.wall = wall;
    }

    public RegistryObject<Block> slab() {
        return this.slab;
    }

    public RegistryObject<Block> stair() {
        return this.stair;
    }

    public RegistryObject<Block> wall() {
        return this.wall;
    }
}

