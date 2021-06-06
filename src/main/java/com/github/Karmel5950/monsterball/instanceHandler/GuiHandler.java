package com.github.Karmel5950.monsterball.instanceHandler;


import com.github.Karmel5950.monsterball.GUI.ContainerBlockMonsterBallProduceMachine;
import com.github.Karmel5950.monsterball.GUI.GUIContainerBlockMonsterBallProduceMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int GUI_PRODUCE_MACHINE = 1;


    public GuiHandler(){
        NetworkRegistry.INSTANCE.registerGuiHandler(com.github.Karmel5950.monsterball.MonsterBall.getInstance(), this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
            case GUI_PRODUCE_MACHINE:
                return new ContainerBlockMonsterBallProduceMachine(player,world.getTileEntity(new BlockPos(x,y,z)));
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
            case GUI_PRODUCE_MACHINE:
                return new GUIContainerBlockMonsterBallProduceMachine(new ContainerBlockMonsterBallProduceMachine(player,world.getTileEntity(new BlockPos(x,y,z))));
            default:
                return null;
        }
    }

}
