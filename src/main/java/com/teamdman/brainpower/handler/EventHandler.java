package com.teamdman.brainpower.handler;

import com.teamdman.brainpower.Brainpower;
import com.teamdman.brainpower.blocks.BlockCharacter;
import com.teamdman.brainpower.netty.MessageChangeCharacter;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import static com.teamdman.brainpower.netty.MessageChangeCharacter.EnumScrollDirection.BACKWARDS;
import static com.teamdman.brainpower.netty.MessageChangeCharacter.EnumScrollDirection.FORWARDS;

public class EventHandler {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onMouseEvent(MouseEvent e) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() != null && Block.getBlockFromItem(player.getHeldItemMainhand().getItem()) != null && Block.getBlockFromItem(player.getHeldItemMainhand().getItem()) instanceof BlockCharacter) {
				if (e.getDwheel() != 0) {
					Brainpower.network.sendToServer(new MessageChangeCharacter(e.getDwheel() > 0 ? FORWARDS : BACKWARDS));
					e.setCanceled(true);
				}
			}
		}
	}
}
