package com.github.Karmel5950.monsterball.item;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.ItemHandler;
import com.github.Karmel5950.monsterball.item.API.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ItemMonsterBall extends Item  implements IHasModel {
    private  String name;

    @Override
    public void registerModel() {
        MonsterBall.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public abstract ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn);

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound tag = getItemTagCompound(stack);
        if(!tag.hasKey("monsterBallEntity")){
            tooltip.add(I18n.format("item.monster_ball.itemImformation.entity") + "empty");
        }else {
            tooltip.add(I18n.format("item.monster_ball.itemImformation.entity") + tag.getString("monsterBallEntityName"));
        }
    }

    public  NBTTagCompound getItemTagCompound(ItemStack stack){
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
