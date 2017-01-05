package com.teamdman.brainpower.registry;

import com.teamdman.brainpower.tiles.TileCircuitBasic;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTiles {
	public static void init() {
		GameRegistry.registerTileEntity(TileCircuitBasic.class,"tilecircuit");
	}
}
