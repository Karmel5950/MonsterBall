package com.github.Karmel5950.monsterball.creativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTabsMyFirstMod extends CreativeTabs {


    public CreativeTabsMyFirstMod() {
        super("MyFirstMod");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.DIAMOND);
    }
}
