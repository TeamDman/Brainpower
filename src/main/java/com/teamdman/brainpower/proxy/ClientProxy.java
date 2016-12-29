package com.teamdman.brainpower.proxy;

import com.teamdman.brainpower.Brainpower;
import com.teamdman.brainpower.client.IMeshProvider;
import com.teamdman.brainpower.client.IVariantProvider;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import org.apache.commons.lang3.tuple.Pair;

public class ClientProxy extends CommonProxy {




	@Override
	public void tryHandleItemModel(Item item, String name)
	{
		if (item instanceof IMeshProvider)
		{
			IMeshProvider meshProvider = (IMeshProvider) item;
			ModelLoader.setCustomMeshDefinition(item, meshProvider.getMeshDefinition());
			ResourceLocation resourceLocation = meshProvider.getCustomLocation();
			if (resourceLocation == null)
				resourceLocation = new ResourceLocation(Brainpower.MODID, "item/" + name);
			for (String variant : meshProvider.getVariants())
				ModelLoader.registerItemVariants(item, new ModelResourceLocation(resourceLocation, variant));
		} else if (item instanceof IVariantProvider)
		{
			IVariantProvider variantProvider = (IVariantProvider) item;
			for (Pair<Integer, String> variant : variantProvider.getVariants())
				ModelLoader.setCustomModelResourceLocation(item, variant.getLeft(), new ModelResourceLocation(new ResourceLocation(Brainpower.MODID, "item/" + name), variant.getRight()));
		}
	}


	@Override
	public void tryHandleBlockModel(Block block, String name)
	{
		if (block instanceof IVariantProvider)
		{
			IVariantProvider variantProvider = (IVariantProvider) block;
			for (Pair<Integer, String> variant : variantProvider.getVariants())
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), variant.getLeft(), new ModelResourceLocation(new ResourceLocation(Brainpower.MODID, name), variant.getRight()));
		}
	}

}
