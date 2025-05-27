package dev.zeddevstuff.keybindspurger.fabric;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class LoaderSpecificUtilsImpl
{
    public static void clear(KeyMapping keyMapping)
    {
        keyMapping.setKey(InputConstants.UNKNOWN);
    }

    public static void reset(KeyMapping keyMapping)
    {
        keyMapping.setKey(keyMapping.getDefaultKey());
    }
}
