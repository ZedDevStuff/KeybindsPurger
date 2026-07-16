package dev.zeddevstuff.keybindspurger.common;

import com.mojang.blaze3d.platform.InputConstants;
import net.msrandom.multiplatform.annotations.Actual;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import net.neoforged.neoforge.client.settings.KeyModifier;

import java.util.List;


public class UtilsActual
{
	@Actual
	public static List<String> getMods()
	{
		return FMLLoader.getCurrent().getLoadingModList().getMods().stream().map(ModInfo::getModId).toList();
	}
	@Actual
	public static void clearKeyMapping(Object keyMapping)
	{
		if(keyMapping instanceof net.minecraft.client.KeyMapping km)
			km.setKeyModifierAndCode(KeyModifier.NONE, InputConstants.UNKNOWN);
	}
	@Actual
	public static void resetKeyMapping(Object keyMapping)
	{
		if(keyMapping instanceof net.minecraft.client.KeyMapping km)
			km.setKeyModifierAndCode(km.getDefaultKeyModifier(), km.getDefaultKey());
	}
}
