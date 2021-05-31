package com.github.Karmel5950.monsterball.instanceHandler;

import com.github.Karmel5950.monsterball.entity.EntityBasicMonsterBall;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class EntityHandler {
    public static final List<EntityEntry> entities = new ArrayList<EntityEntry>();

    public EntityHandler(){
        EntityBasicMonsterBall.registry();
    }

}
