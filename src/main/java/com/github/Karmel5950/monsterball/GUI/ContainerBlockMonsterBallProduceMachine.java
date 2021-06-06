package com.github.Karmel5950.monsterball.GUI;

import com.github.Karmel5950.monsterball.tileentity.TileEntityMonsterBallProduceMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBlockMonsterBallProduceMachine extends Container {
    protected TileEntityMonsterBallProduceMachine tileEntity;
    IItemHandler inventory;

    private int burnProcess ;
    private int burnTagetProcess ;
    private int burnTime ;
    private int maxBurnTime;

    public ContainerBlockMonsterBallProduceMachine(EntityPlayer player,TileEntity tileEntity) {
        super();
        this.tileEntity = (TileEntityMonsterBallProduceMachine)tileEntity;

        inventory = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);

        this.addSlotToContainer(new SlotItemHandler(inventory, 0, 26 , 17)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack != null &&  super.isItemValid(stack) ;
            }
            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 64;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(inventory, 1, 56 , 17)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack != null &&  super.isItemValid(stack) ;
            }
            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 64;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(inventory, 2, 86 , 17)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack != null &&  super.isItemValid(stack) ;
            }
            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 64;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(inventory, 3, 56, 53)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack != null &&  super.isItemValid(stack) && TileEntityMonsterBallProduceMachine.getItemBurnTime(stack) > 0;
            }

            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 64;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(inventory, 4, 116, 35)
        {
            @Override
            public boolean isItemValid(ItemStack stack)
            {
                return false;
            }
        });

        //Player Inventory
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        this.burnTime = tileEntity.getBurnTime();
        this.maxBurnTime = tileEntity.getMaxBurnTime();
        this.burnProcess = tileEntity.getBurnProcess();
        this.burnTagetProcess = tileEntity.getBurnTagetProcess();

        for (int j = 0; j < this.listeners.size(); ++j)
        {
            ((IContainerListener)this.listeners.get(j)).sendWindowProperty(this, 0, this.burnTime);
            ((IContainerListener)this.listeners.get(j)).sendWindowProperty(this, 1, this.maxBurnTime);
            ((IContainerListener)this.listeners.get(j)).sendWindowProperty(this, 2, this.burnProcess);
            ((IContainerListener)this.listeners.get(j)).sendWindowProperty(this, 3, this.burnTagetProcess);
        }

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        super.updateProgressBar(id, data);

        switch (id)
        {
            case 0:
                this.burnTime = data;
                break;
            case 1:
                this.maxBurnTime = data;
                break;
            case 2:
                this.burnProcess = data;
                break;
            case 3:
                this.burnTagetProcess = data;
                break;
            default:
                break;
        }
    }

    public int getBurnTime(){
        return this.burnTime;
    }

    public int getBurnProcess(){
        return this.burnProcess;
    }

    public int getBurnTagetProcess(){
        return this.burnTagetProcess;
    }

    public int getMaxBurnTime(){
        return this.maxBurnTime;
    }
}
