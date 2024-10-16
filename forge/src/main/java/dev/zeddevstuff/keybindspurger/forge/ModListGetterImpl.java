package dev.zeddevstuff.keybindspurger.forge;

import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.util.List;

public class ModListGetterImpl
{
    public static List<String> getModList()
    {
        return LoadingModList.get().getMods().stream().map(ModInfo::getModId).toList();
    }
}
