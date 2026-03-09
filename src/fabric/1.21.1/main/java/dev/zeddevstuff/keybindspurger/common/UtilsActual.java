package dev.zeddevstuff.keybindspurger.common;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.loader.api.FabricLoader;
import net.msrandom.multiplatform.annotations.Actual;

import java.util.List;

public class UtilsActual
{
	@Actual
	public static List<String> getMods()
	{
		return FabricLoader.getInstance().getAllMods()
			.stream()
			.map(modContainer -> modContainer.getMetadata().getId())
			.toList();
	}

	@Actual
	public static void clearKeyMapping(Object keyMapping)
	{
		if (keyMapping instanceof net.minecraft.client.KeyMapping km)
			km.setKey(InputConstants.UNKNOWN);
	}
	@Actual
	public static void resetKeyMapping(Object keyMapping)
	{
		if (keyMapping instanceof net.minecraft.client.KeyMapping km)
			km.setKey(km.getDefaultKey());
	}
}
