package com.github.Karmel5950.monsterball.block;

import com.github.Karmel5950.monsterball.MonsterBall;
import com.github.Karmel5950.monsterball.instanceHandler.BlockHandler;
import com.github.Karmel5950.monsterball.instanceHandler.GuiHandler;
import com.github.Karmel5950.monsterball.instanceHandler.ItemHandler;
import com.github.Karmel5950.monsterball.item.API.IHasModel;
import com.github.Karmel5950.monsterball.tileentity.TileEntityMonsterBallProduceMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMonsterBallProduceMachine extends Block implements IHasModel {
    public static final String name = "produce_machine";
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool BURNING = PropertyBool.create("burning");

    public BlockMonsterBallProduceMachine() {
        super(Material.IRON);
        BlockHandler.blocks.add(this);
        ItemHandler.items.add(new ItemBlock(this).setRegistryName(name));
        this.setRegistryName(MonsterBall.MODID + ":" + name);
        this.setTranslationKey(MonsterBall.MODID + "." + name);
        this.setCreativeTab(MonsterBall.creativeTabsMonsterBall);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH)
                .withProperty(BURNING, Boolean.FALSE));
        this.setLightLevel(1F);
    }


    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.byHorizontalIndex(meta & 3);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }
        Boolean burning = Boolean.valueOf((meta & 4) != 0);

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(BURNING, burning);
    }

    public int getMetaFromState(IBlockState state)
    {
        int facing = state.getValue(FACING).getHorizontalIndex();
        int burning = state.getValue(BURNING).booleanValue() ? 4 : 0;
        return facing | burning ;
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, BURNING, FACING);
    }

    @Override
    public void registerModel() {
        MonsterBall.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
        {
            int id = GuiHandler.GUI_PRODUCE_MACHINE;
            player.openGui(MonsterBall.getInstance(), id, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
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
