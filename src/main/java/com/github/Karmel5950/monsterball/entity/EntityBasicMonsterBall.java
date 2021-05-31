package com.github.Karmel5950.monsterball.entity;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.EntityHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityBasicMonsterBall extends EntityThrowable {
    ItemStack itemStackInPlayer;

    public EntityBasicMonsterBall(World worldIn) {
        super(worldIn);
    }
    public EntityBasicMonsterBall(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn,throwerIn);
        itemStackInPlayer = throwerIn.getHeldItem(EnumHand.MAIN_HAND);
    }
    public EntityBasicMonsterBall(World worldIn, double x, double y, double z) {
        super(worldIn,x,y,z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

            if (result.entityHit != null)
            {
                if (!this.world.isRemote){
                    NBTTagCompound tag = getItemTagCompound(itemStackInPlayer);
                    if(!tag.hasKey("monsterBallEntity")){
                        result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
                        if(result.entityHit instanceof EntityZombie && ((EntityZombie) result.entityHit).getHealth() < ((EntityZombie) result.entityHit).getMaxHealth() * 0.5){
                            result.entityHit.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                            ((EntityZombie) result.entityHit).setHealth(((EntityZombie) result.entityHit).getMaxHealth());
                            tag.setBoolean("monsterBallEntityIsSpawn",false);
                            tag.setTag("monsterBallEntity",((EntityZombie)(result.entityHit)).serializeNBT());
                            tag.setString("monsterBallEntityName",result.entityHit.getName());
                            tag.setString("monsterBallEntityClass",result.entityHit.getClass().getName());
                            result.entityHit.setDead();
                        }
                    }
                }
            }else {
                if (!this.world.isRemote){
                    NBTTagCompound tag = getItemTagCompound(itemStackInPlayer);
                    if(tag.hasKey("monsterBallEntity")) {
                        EntityZombie monster = new EntityZombie(world);
                        monster.deserializeNBT(tag.getCompoundTag("monsterBallEntity"));
                        BlockPos pos = result.getBlockPos();
                        monster.setPosition(pos.getX(),pos.getY() + 2,pos.getZ());
                        this.world.spawnEntity(monster);
                        tag.setBoolean("monsterBallEntityIsSpawn",true);
                        this.thrower.sendMessage(new TextComponentString("spawn success"));
                    }
                }
            }

            if (!this.world.isRemote)
            {
                this.world.setEntityState(this, (byte)3);
                this.setDead();
            }

    }

    public static void registry(){
        EntityHandler.entities.add(
                EntityEntryBuilder.create()
                .entity(EntityBasicMonsterBall.class)
                .id(new ResourceLocation(MonsterBall.MODID, "basic_monsterball_entity"), 233)
                .name("basic_monsterball")
                .tracker(80, 1, false)
                .build()
        );

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
