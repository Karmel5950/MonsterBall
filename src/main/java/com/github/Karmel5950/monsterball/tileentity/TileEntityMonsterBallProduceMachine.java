package com.github.Karmel5950.monsterball.tileentity;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.TileEntityHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityMonsterBallProduceMachine extends TileEntityFurnace {
    private final static String name = "te_monsterball_produce_machine";

    NonNullList<ItemStack> furnaceItemStacks = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);
    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);


    public TileEntityMonsterBallProduceMachine () {
        GameRegistry.registerTileEntity(this.getClass(), new ResourceLocation(MonsterBall.MODID, name));
        TileEntityHandler.tileEntities.add(this);
    }

}
