package com.cryptnation.cryptsadditions.world.gen;

import com.cryptnation.cryptsadditions.block.ModBlocks;
import net.minecraft.block.Block;

public enum OreType
{
    CRYPTIC_ORE(ModBlocks.CRYPTIC_ORE.get(),6,8,38),
    VOID_ORE(ModBlocks.VOID_ORE.get(),4,4,20);

    private final Block block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;

OreType(Block block, int maxVeinSize, int minHeight, int maxHeight){
    this.block = block;
    this.maxHeight = maxHeight;
    this.maxVeinSize = maxVeinSize;
    this.minHeight = minHeight;
}

    public Block getBlock() {
        return block;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public static OreType get(Block block)
    {
        for(OreType ore : values())
        {
             if(block == ore.block){
                 return ore;
             }
        }
        return null;
    }

}
