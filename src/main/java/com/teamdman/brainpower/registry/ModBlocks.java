package com.teamdman.brainpower.registry;

import com.teamdman.brainpower.Brainpower;
import com.teamdman.brainpower.blocks.BlockCharacter;
import com.teamdman.brainpower.blocks.BlockCircuitBoard;
import com.teamdman.brainpower.blocks.BlockControllerBasic;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static Block CIRCUITBOARD;
	public static Block ARROW;
	public static Block PLUS;
	public static Block MINUS;
	public static Block PERIOD;
	public static Block COMMA;
	public static Block BRACKET;
	public static Block CIRCLE;
	public static Block CONTROLLER_BASIC;

	public static void init() {
		CIRCUITBOARD = registerBlock(new BlockCircuitBoard(), "blockcircuitboard");
		ARROW = registerBlock(new BlockCharacter(),"blockarrow");
		PLUS = registerBlock(new BlockCharacter(),"blockplus");
		MINUS = registerBlock(new BlockCharacter(),"blockminus");
		PERIOD = registerBlock(new BlockCharacter(),"blockperiod");
		COMMA = registerBlock(new BlockCharacter(),"blockcomma");
		BRACKET = registerBlock(new BlockCharacter(),"blockbracket");
		CIRCLE = registerBlock(new BlockCharacter(),"blockcircle");
		CONTROLLER_BASIC = registerBlock(new BlockControllerBasic(), "blockcontrollerbasic");
	}

	private static Block registerBlock(Block block, String name,ItemBlock itemblock) {
		block.setUnlocalizedName(name);
		block.setCreativeTab(Brainpower.tab);

		if (block.getRegistryName()==null)
			block.setRegistryName(name);

		GameRegistry.register(block);
		GameRegistry.register(itemblock);
		Brainpower.proxy.tryHandleBlockModel(block, name);
		return block;
	}

	private static Block registerBlock(Block block, String name) {
		ItemBlock itemblock = new ItemBlock(block){
			@Override
			public boolean getHasSubtypes() {
				return true;
			}

			@Override
			public int getMetadata(int damage) {
				return damage;
			}
		};
		itemblock.setRegistryName(name);
		return registerBlock(block,name,itemblock);
	}
}
