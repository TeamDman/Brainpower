package com.teamdman.brainpower.blocks;

import com.teamdman.brainpower.client.IVariantProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class BlockCircuitBoard extends Block implements IVariantProvider {
	public static final PropertyEnum BOARDTYPE = PropertyEnum.create("tier", EnumBoardType.class);

	public BlockCircuitBoard() {
		super(Material.CIRCUITS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BOARDTYPE,EnumBoardType.BASIC));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{BOARDTYPE});
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(BOARDTYPE, meta == 0 ? EnumBoardType.BASIC : EnumBoardType.ADVANCED);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumBoardType) state.getValue(BOARDTYPE)).getID();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(itemIn,1,0));
		list.add(new ItemStack(itemIn,1,1));
	}

	public enum EnumBoardType implements IStringSerializable {
		BASIC(0, "basic"),
		ADVANCED(1, "advanced");

		private int ID;
		private String name;

		private EnumBoardType(int ID, String name) {
			this.ID = ID;
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}

		public int getID() {
			return ID;
		}

		@Override
		public String toString() {
			return getName();
		}
	}

	@Override
	public List<Pair<Integer, String>> getVariants() {
		List<Pair<Integer, String>> ret = new ArrayList<>();
		ret.add(new ImmutablePair<>(0, "tier=basic"));
		ret.add(new ImmutablePair<>(0, "tier=advanced"));
		return ret;
	}
}
