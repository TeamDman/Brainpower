package com.teamdman.brainpower.blocks;

import com.teamdman.brainpower.client.IVariantProvider;
import com.teamdman.brainpower.tiles.TileCircuitBasic;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class BlockControllerBasic extends Block implements IVariantProvider, ITileEntityProvider {
	public BlockControllerBasic() {
		super(Material.CIRCUITS);
		this.isBlockContainer = true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCircuitBasic();
	}

	@Override
	public List<Pair<Integer, String>> getVariants() {
	    List<Pair<Integer, String>> ret = new ArrayList<>();
	    ret.add(new ImmutablePair<>(0, "normal"));
	    return ret;
	}
}
