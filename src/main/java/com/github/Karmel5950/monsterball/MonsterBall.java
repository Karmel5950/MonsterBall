package com.github.Karmel5950.monsterball;

import com.github.Karmel5950.monsterball.creativeTab.CreativeTabsMyFirstMod;
import com.github.Karmel5950.monsterball.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = com.github.Karmel5950.monsterball.MonsterBall.MODID, name = com.github.Karmel5950.monsterball.MonsterBall.NAME, version = com.github.Karmel5950.monsterball.MonsterBall.VERSION, useMetadata = true)
public enum MonsterBall {
    INSTANCE;
    public static final String MODID = "monster_ball";
    public static final String NAME = "Monster Ball";
    public static final String VERSION = "1.0.0";
    public static CreativeTabsMyFirstMod CreativeTabsMyFirstMod;

    @Mod.InstanceFactory
    public static MonsterBall getInstance() {
        return INSTANCE;
    }

    @SidedProxy(clientSide = "com.github.ssg.myfirstmod.proxy.ClientProxy", serverSide = "com.github.ssg.myfirstmod.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }




}
