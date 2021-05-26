package com.github.Karmel5950.monsterball.block;

import com.github.Karmel5950.monsterball.instanceHandler.BlockHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockMonsterBallExchangeMachine extends Block {
    public BlockMonsterBallExchangeMachine() {
        super(Material.IRON);
        BlockHandler.blocks.add(this);
    }
}
