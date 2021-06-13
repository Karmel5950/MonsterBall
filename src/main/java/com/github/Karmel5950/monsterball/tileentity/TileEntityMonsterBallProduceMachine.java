package com.github.Karmel5950.monsterball.tileentity;


import com.github.Karmel5950.monsterball.block.BlockMonsterBallProduceMachine;
import com.github.Karmel5950.monsterball.config.config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Objects;

public class TileEntityMonsterBallProduceMachine extends TileEntityFurnace {

    //0 , 1 , 2 - Raw Meterial Slot , 3 - Fuel Slot , 4 - Produce Slot
    private final ItemStackHandler inventory = new ItemStackHandler(5);
    private int burnTime = 0;
    private final int maxBurnTime = 2000;
    private int burnProcess = 0;
    private final int burnTagetProcess = 200;
    private boolean isCosumeRawMeterial = false ;
    private final String[] recipe1 = config.recipe1;
    private final String[] recipe2 = config.recipe2;
    private final String[] recipe3 = config.recipe3;
    private final String[] produceRecipe = config.produce;
    private ItemStack produceItem;
    @Override
    public void update() {
        ItemStack[] rawMaterialSlot = new ItemStack[]{inventory.getStackInSlot(0),inventory.getStackInSlot(1),inventory.getStackInSlot(2)};
        ItemStack fuelSlot = inventory.getStackInSlot(3);
        ItemStack produceSlot = inventory.getStackInSlot(4);

        //add fule
        if(isNeedFuel(fuelSlot)){
            if(!fuelSlot.isEmpty()){
                burnTime += getItemBurnTime(fuelSlot);
                fuelSlot.shrink(1);
            }
            if (burnTime > maxBurnTime){
                burnTime = maxBurnTime;
            }
        }

        //cosume raw meterial
        if (!isCosumeRawMeterial){
            if (isRecipe(rawMaterialSlot) != ItemStack.EMPTY && produceSlot.isEmpty()){
                produceItem = isRecipe(rawMaterialSlot);
                for (int i = 0 ; i < 3 ; i++){
                    rawMaterialSlot[i].shrink(1);
                }
                isCosumeRawMeterial = true ;
            }
        }

        //run
        if (isRunning()){
            changeBurningStates(true);
            burnTime--;
            burnProcess++;
        }else {
            changeBurningStates(false);
        }

        //produce
        if (burnTagetProcess == burnProcess){
            burnProcess = 0 ;
            produceItem(produceItem);
            isCosumeRawMeterial = false;
        }

        this.markDirty();
    }

    @Override
    public int getCookTime(ItemStack stack) {
        return super.getCookTime(stack);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        this.burnProcess = compound.getInteger("burnProgress");
        this.burnTime = compound.getInteger("burnTime");
        this.isCosumeRawMeterial = compound.getBoolean("isCosumeRawMeterial");
        this.produceItem.deserializeNBT(compound.getCompoundTag("produceItem"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory",this.inventory.serializeNBT());
        compound.setInteger("burnProgress", this.burnProcess);
        compound.setInteger("burnTagetProcess", this.burnTagetProcess);
        compound.setInteger("burnTime",this.burnTime);
        compound.setInteger("maxBurnTime", this.maxBurnTime);
        compound.setBoolean("isCosumeRawMeterial",isCosumeRawMeterial);
        compound.setTag("produceItem",this.produceItem.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return (T) inventory;
        }
        return super.getCapability(capability, facing);
    }

    public boolean isRunning(){
        return burnTime > 0 && isCosumeRawMeterial ;
    }

    public void produceItem(ItemStack itemStack)
    {
            inventory.setStackInSlot(4,itemStack);
    }

    public  ItemStack isRecipe(ItemStack[] rawMaterialSlot){
        if(!rawMaterialSlot[0].isEmpty() && !rawMaterialSlot[1].isEmpty() && !rawMaterialSlot[2].isEmpty()){
            String items =Objects.requireNonNull(rawMaterialSlot[0].getItem().getRegistryName()).getPath()+
                    Objects.requireNonNull(rawMaterialSlot[1].getItem().getRegistryName()).getPath()+
                    Objects.requireNonNull(rawMaterialSlot[2].getItem().getRegistryName()).getPath();
            for (int i = 0;i < recipe1.length;i++){
                String recipe = recipe1[i]+recipe2[i]+recipe3[i];
                if (items.equals(recipe) ){
                    Item item  =  Item.REGISTRY.getObject(new ResourceLocation(produceRecipe[i]));
                    return new ItemStack(item);
                }
            }
        }

        return ItemStack.EMPTY;
    }

    public boolean isNeedFuel(ItemStack fuelItemStack){
        return (maxBurnTime - burnTime) >= getItemBurnTime(fuelItemStack) && getItemBurnTime(fuelItemStack) > 0;
    }

    public void changeBurningStates(boolean burningStates){
        IBlockState blockState = world.getBlockState(pos);
        IBlockState newBlockState =  world.getBlockState(pos).withProperty(BlockMonsterBallProduceMachine.BURNING,burningStates);
        if (blockState != newBlockState){
            world.setBlockState(pos,newBlockState);
            this.validate();
            world.setTileEntity(pos, this);
        }
    }

    public int getBurnTime() {
        return burnTime;
    }

    public int getMaxBurnTime() {
        return maxBurnTime;
    }

    public int getBurnProcess() {
        return burnProcess;
    }

    public int getBurnTagetProcess() {
        return burnTagetProcess;
    }
}
