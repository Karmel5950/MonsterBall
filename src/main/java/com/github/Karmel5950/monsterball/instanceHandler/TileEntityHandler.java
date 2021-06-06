package com.github.Karmel5950.monsterball.instanceHandler;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.block.BlockMonsterBallProduceMachine;
import com.github.Karmel5950.monsterball.tileentity.TileEntityMonsterBallProduceMachine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class TileEntityHandler {
    public TileEntityHandler(){
        GameRegistry.registerTileEntity(TileEntityMonsterBallProduceMachine.class, new ResourceLocation(MonsterBall.MODID, BlockMonsterBallProduceMachine.name));
    }
}
