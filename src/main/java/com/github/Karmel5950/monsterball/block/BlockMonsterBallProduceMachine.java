package com.github.Karmel5950.monsterball.block;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.BlockHandler;
import com.github.Karmel5950.monsterball.instanceHandler.ItemHandler;
import com.github.Karmel5950.monsterball.item.API.IHasModel;
import com.github.Karmel5950.monsterball.tileentity.TileEntityMonsterBallProduceMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMonsterBallProduceMachine extends Block implements IHasModel {
    private final String name = "monsterball_produce_machine";
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockMonsterBallProduceMachine() {
        super(Material.IRON);
        BlockHandler.blocks.add(this);
        ItemHandler.items.add(new ItemBlock(this).setRegistryName(name));
        this.setRegistryName(MonsterBall.MODID + ":" + name);
        this.setTranslationKey(MonsterBall.MODID + "." + name);
        this.setCreativeTab(MonsterBall.creativeTabsMonsterBall);

    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public void registerModel() {
        MonsterBall.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
        {
            BlockPos playerPos = player.getPosition();
            //int id = GUIHandler.GUI_GLASS_FURNACE;
            //player.openGui(MyFirstMod.getInstance(), id, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityMonsterBallProduceMachine();
    }

}
