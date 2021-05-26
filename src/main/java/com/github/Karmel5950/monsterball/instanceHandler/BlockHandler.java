package com.github.Karmel5950.monsterball.instanceHandler;

import com.github.Karmel5950.monsterball.block.*;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockHandler {
    public static final List<Block> blocks = new ArrayList<Block>();

    BlockMonsterBallExchangeMachine blockMonsterBallExchangeMachine = new BlockMonsterBallExchangeMachine();
    BlockMonsterBallProduceMachine blockMonsterBallProduceMachine = new BlockMonsterBallProduceMachine();
    BlockMonsterHealer blockMonsterHealer = new BlockMonsterHealer();
}
