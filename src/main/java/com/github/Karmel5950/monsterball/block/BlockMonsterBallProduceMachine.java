package com.github.Karmel5950.monsterball.block;

import com.github.Karmel5950.monsterball.instanceHandler.BlockHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMonsterBallProduceMachine extends Block {
    public BlockMonsterBallProduceMachine() {
        super(Material.IRON);
        BlockHandler.blocks.add(this);
    }
}
