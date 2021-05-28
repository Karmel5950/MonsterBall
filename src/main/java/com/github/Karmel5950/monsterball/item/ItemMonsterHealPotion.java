package com.github.Karmel5950.monsterball.item;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.ItemHandler;
import com.github.Karmel5950.monsterball.item.API.IHasModel;
import net.minecraft.item.Item;

public class ItemMonsterHealPotion extends Item implements IHasModel {
    private final String name = "monster_heal_potion";
    public ItemMonsterHealPotion(){
        super();
        this.setTranslationKey(MonsterBall.MODID + "." + this.name);
        this.setRegistryName(MonsterBall.MODID + ":" + this.name);
        this.setCreativeTab(MonsterBall.creativeTabsMonsterBall);
        this.setMaxStackSize(64);
        this.setNoRepair();
        this.setMaxDamage(0);
        ItemHandler.items.add(this);
    }

    @Override
    public void registerModel() {
        MonsterBall.proxy.registerItemRenderer(this, 0, "inventory");
    }
}