package com.github.Karmel5950.monsterball.item;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.ItemHandler;
import com.github.Karmel5950.monsterball.item.API.IHasModel;
import net.minecraft.item.Item;

public class ItemArchitectureMonsterBall extends Item implements IHasModel {
    private final String name = "architecture_monsterbal";
    public ItemArchitectureMonsterBall(){
        super();
        this.setTranslationKey(MonsterBall.MODID + "." + this.name);
        this.setRegistryName(MonsterBall.MODID + ":" + this.name);
        this.setCreativeTab(MonsterBall.creativeTabsMonsterBall);
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.setMaxDamage(0);
        ItemHandler.items.add(this);
    }

    @Override
    public void registerModel() {
        MonsterBall.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
