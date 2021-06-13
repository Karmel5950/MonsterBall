package com.github.Karmel5950.monsterball.item;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.config.config;
import com.github.Karmel5950.monsterball.instanceHandler.ItemHandler;
import com.github.Karmel5950.monsterball.item.API.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemAnimalMonsterBall extends Item implements IHasModel {
    private final String name = "animal_monsterball";
    public ItemAnimalMonsterBall(){
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        //playerIn.sendMessage(new TextComponentString(playerIn.getHeldItem(handIn).getItem().getRegistryName().getPath()));
        Item item  =  Item.REGISTRY.getObject(new ResourceLocation(config.produce[0]));
        playerIn.dropItem(new ItemStack(item),false);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
