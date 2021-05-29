package com.github.Karmel5950.monsterball.entity.render;

import com.github.Karmel5950.monsterball.entity.EntityBasicMonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.ItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;

public class RenderBasicMonsterBall extends RenderSnowball<EntityBasicMonsterBall> {
    public RenderBasicMonsterBall(RenderManager renderManagerIn) {
        super(renderManagerIn, ItemHandler.itemBasicMonsterBall, Minecraft.getMinecraft().getRenderItem());
    }
}
