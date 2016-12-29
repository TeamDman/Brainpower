package com.teamdman.brainpower;

import com.teamdman.brainpower.proxy.CommonProxy;
import com.teamdman.brainpower.registry.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Brainpower.MODID, version = Brainpower.VERSION)
public class Brainpower {
	public static final String MODID = "brainpower";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide="com.teamdman.brainpower.proxy.ClientProxy", serverSide = "com.teamdman.brainpower.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs tab = new CreativeTabs(MODID) {
		@Override
		public Item getTabIconItem() {
			return Items.ACACIA_BOAT;
		}
	};

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		ModBlocks.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {

	}
}
