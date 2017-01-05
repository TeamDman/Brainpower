package com.teamdman.brainpower.blocks;

import com.teamdman.brainpower.client.IVariantProvider;
import com.teamdman.brainpower.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static it.unimi.dsi.fastutil.objects.ObjectArrays.reverse;

public class BlockCharacter extends Block implements IVariantProvider {
	protected static final AxisAlignedBB BOUNDS = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.0625D, 0.9375D);
	protected static final PropertyDirection FACING = PropertyDirection.create("facing");

	public BlockCharacter() {
		super(Material.CIRCUITS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getAdjustedHorizontalFacing()));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDS;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add("Must be placed on a circuitboard block");
		tooltip.add("Shift+scroll to cycle characters");
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.CIRCUITBOARD;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (heldItem != null && heldItem.getItem() instanceof ItemBlock && ((ItemBlock) heldItem.getItem()).getBlock() instanceof BlockCharacter)
			worldIn.setBlockState(pos,((ItemBlock) heldItem.getItem()).getBlock().getDefaultState().withProperty(FACING,state.getValue(FACING)));
		return true;
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return BOUNDS;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public List<Pair<Integer, String>> getVariants() {
		List<Pair<Integer, String>> ret = new ArrayList<>();
		ret.add(new ImmutablePair<>(0, "facing=north"));
		return ret;
	}

	public enum EnumCharacter {
		PLUS(ModBlocks.PLUS),
		MINUS(ModBlocks.MINUS),
		ARROW(ModBlocks.ARROW),
		PERIOD(ModBlocks.PERIOD),
		COMMA(ModBlocks.COMMA),
		BRACKET(ModBlocks.BRACKET),
		CIRCLE(ModBlocks.CIRCLE);

		private static final EnumCharacter[] cache = values();
		public final Block block;

		EnumCharacter(Block block) {
			this.block = block;
		}

		public static EnumCharacter getFromBlock(Block block) {
			for (EnumCharacter ch : EnumCharacter.values()) {
				if (ch.block == block)
					return ch;
			}
			return CIRCLE;
		}

		public EnumCharacter getNext() {
			return cache[(ordinal() + 1) % (cache.length)];
		}

		public EnumCharacter getPrev() {
			return cache[(ordinal()==0?cache.length-1:ordinal() - 1) % (cache.length)];
		}
	}
}
