package com.teamdman.brainpower;

import com.teamdman.brainpower.netty.MessageChangeCharacter;
import com.teamdman.brainpower.proxy.CommonProxy;
import com.teamdman.brainpower.registry.ModBlocks;
import com.teamdman.brainpower.registry.ModTiles;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


@Mod(modid = Brainpower.MODID, version = Brainpower.VERSION)
public class Brainpower {
	public static final String MODID = "brainpower";
	public static final String VERSION = "1.0";
	public static final CreativeTabs tab = new CreativeTabs(MODID) {
		@Override
		public Item getTabIconItem() {
			return Items.ACACIA_BOAT;
		}
	};
	public static SimpleNetworkWrapper network;
	@SidedProxy(clientSide = "com.teamdman.brainpower.proxy.ClientProxy", serverSide = "com.teamdman.brainpower.proxy.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel("changechar");
		network.registerMessage(MessageChangeCharacter.Handler.class,MessageChangeCharacter.class,0, Side.SERVER);
		ModBlocks.init();
		ModTiles.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new com.teamdman.brainpower.handler.EventHandler());
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {

	}

	//TODO: Make characters able to overwrite one another
	//TODO: Increase character icon clarity
}
