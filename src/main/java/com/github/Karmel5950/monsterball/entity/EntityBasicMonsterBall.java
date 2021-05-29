package com.github.Karmel5950.monsterball.entity;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.EntityHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class EntityBasicMonsterBall extends EntityThrowable {

    public EntityBasicMonsterBall(World worldIn) {
        super(worldIn);
    }
    public EntityBasicMonsterBall(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn,throwerIn);
    }
    public EntityBasicMonsterBall(World worldIn, double x, double y, double z) {
        super(worldIn,x,y,z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

            if (result.entityHit != null)
            {
                result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
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


}
