package com.teamdman.brainpower.client;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public interface IMeshProvider {
	/**
	 * Gets the custom ItemMeshDefinition to use for the item.
	 *
	 * @return - the custom ItemMeshDefinition to use for the item.
	 */
	@SideOnly(Side.CLIENT)
	ItemMeshDefinition getMeshDefinition();

	/**
	 * Gets all possible variants for this item
	 *
	 * @return - All possible variants for this item
	 */
	List<String> getVariants();

	/**
	 * If a custom ResourceLocation is required, return it here.
	 *
	 * Can be null if unneeded.
	 *
	 * @return - The custom ResourceLocation
	 */
	@Nullable
	ResourceLocation getCustomLocation();
}
