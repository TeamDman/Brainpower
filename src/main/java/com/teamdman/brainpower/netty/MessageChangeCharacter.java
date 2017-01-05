package com.teamdman.brainpower.netty;

import com.teamdman.brainpower.blocks.BlockCharacter;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageChangeCharacter implements IMessage {
	EnumScrollDirection dir;

	public MessageChangeCharacter(EnumScrollDirection dir) {
		this.dir = dir;
	}

	public MessageChangeCharacter() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		dir = buf.readByte() == 0 ? EnumScrollDirection.FORWARDS : EnumScrollDirection.BACKWARDS;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(dir == EnumScrollDirection.FORWARDS ? 0 : 1);
	}

	public enum EnumScrollDirection {
		FORWARDS,
		BACKWARDS
	}

	public static class Handler implements IMessageHandler<MessageChangeCharacter, IMessage> {
		@Override
		public IMessage onMessage(MessageChangeCharacter message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.worldObj;
			mainThread.addScheduledTask(new Runnable() {
				@Override
				public void run() {
					EntityPlayer player = ctx.getServerHandler().playerEntity;
					BlockCharacter.EnumCharacter heldChar = BlockCharacter.EnumCharacter.getFromBlock(((ItemBlock) player.getHeldItemMainhand().getItem()).getBlock());
					int size = player.getHeldItemMainhand().stackSize;
					ItemStack stack;
					if (message.dir==EnumScrollDirection.FORWARDS) {
						stack = new ItemStack(heldChar.getNext().block,size);
					} else {
						stack = new ItemStack(heldChar.getPrev().block,size);
					}

					player.setHeldItem(EnumHand.MAIN_HAND, stack);
				}
			});
			return null;
		}
	}
}
