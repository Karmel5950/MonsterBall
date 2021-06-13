package com.github.Karmel5950.monsterball.item;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.config.config;
import com.github.Karmel5950.monsterball.entity.EntityBasicMonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.ItemHandler;
import com.github.Karmel5950.monsterball.item.API.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBasicMonsterBall extends Item implements IHasModel {
    private final String name = "basic_monsterball";

    public ItemBasicMonsterBall(){
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

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        NBTTagCompound tag = getItemTagCompound(itemstack);

        if (!tag.getBoolean("monsterBallEntityIsSpawn")){
            if (!playerIn.capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }

            if (!worldIn.isRemote)
            {
                EntityBasicMonsterBall entityBasicMonsterBall = new EntityBasicMonsterBall(worldIn, playerIn);
                entityBasicMonsterBall.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float) (1.5), 0.0F);
                worldIn.spawnEntity(entityBasicMonsterBall);
            }
        }else {
            EntityZombie monster = new EntityZombie(worldIn);
            monster.deserializeNBT(tag.getCompoundTag("monsterBallEntity"));
            monster.setDead();
            tag.setBoolean("monsterBallEntityIsSpawn",false);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        NBTTagCompound tag = getItemTagCompound(stack);
        if(!tag.hasKey("monsterBallEntity")){
            tooltip.add(I18n.format("item.monster_ball.itemImformation.entity") + "empty");
        }else {
            tooltip.add(I18n.format("item.monster_ball.itemImformation.entity") + tag.getString("monsterBallEntityName"));
        }
    }

    public static NBTTagCompound getItemTagCompound(ItemStack stack){
        NBTTagCompound tag;
        if(stack.hasTagCompound()){
            tag = stack.getTagCompound();
        }else{
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        return tag;
    }
}
