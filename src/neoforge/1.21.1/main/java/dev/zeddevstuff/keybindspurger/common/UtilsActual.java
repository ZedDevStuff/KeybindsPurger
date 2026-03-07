package dev.zeddevstuff.keybindspurger.common;

import net.msrandom.multiplatform.annotations.Actual;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.fml.loading.moddiscovery.ModInfo;

import java.util.List;


public class UtilsActual
{
	@Actual
	public static List<String> getMods()
	{
		return LoadingModList.get().getMods().stream().map(ModInfo::getModId).toList();
	}
}
