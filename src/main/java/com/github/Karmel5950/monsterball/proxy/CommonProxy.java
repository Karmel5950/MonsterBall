package com.github.Karmel5950.monsterball.proxy;


import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.creativeTab.CreativeTabsMonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.BlockHandler;
import com.github.Karmel5950.monsterball.instanceHandler.GuiHandler;
import com.github.Karmel5950.monsterball.instanceHandler.ItemHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        MonsterBall.creativeTabsMonsterBall = new CreativeTabsMonsterBall();

    }

    public void init(FMLInitializationEvent event) {
        new GuiHandler();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public void registerItemRenderer(Item item, int meta, String id) { }
}
