package dev.zeddevstuff.keybindspurger.fabric;

import java.util.List;

public class ModListGetterImpl
{
    public static List<String> getModList()
    {
        return net.fabricmc.loader.api.FabricLoader.getInstance().getAllMods().stream().map(modContainer -> modContainer.getMetadata().getId()).toList();
    }
}
