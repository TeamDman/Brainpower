package com.teamdman.brainpower.registry;

import com.teamdman.brainpower.Brainpower;
import com.teamdman.brainpower.blocks.BlockCharacter;
import com.teamdman.brainpower.blocks.BlockCircuitBoard;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static Block CIRCUITBOARD;
	public static Block ARROW;
	public static Block PLUS;
	public static void init() {
		CIRCUITBOARD = registerBlock(new BlockCircuitBoard(), "blockcircuitboard");
		ARROW = registerBlock(new BlockCharacter(),"blockarrow");
		PLUS = registerBlock(new BlockCharacter(),"blockplus");
	}

	private static Block registerBlock(Block block, String name) {
		block.setUnlocalizedName(name);
		block.setCreativeTab(Brainpower.tab);

		if (block.getRegistryName()==null)
			block.setRegistryName(name);

		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block){
			@Override
			public boolean getHasSubtypes() {
				return true;
			}

			@Override
			public int getMetadata(int damage) {
				return damage;
			}
		}.setRegistryName(name));
		Brainpower.proxy.tryHandleBlockModel(block, name);
		return block;
	}
}
