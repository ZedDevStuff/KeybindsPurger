package dev.zeddevstuff.keybindspurger.neoforge;

import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.fml.loading.moddiscovery.ModInfo;

import java.util.List;

public class ModListGetterImpl
{
    public static List<String> getModList()
    {
        return LoadingModList.get().getMods().stream().map(ModInfo::getModId).toList();
    }
}
