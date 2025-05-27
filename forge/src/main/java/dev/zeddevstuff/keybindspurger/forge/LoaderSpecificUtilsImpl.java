package dev.zeddevstuff.keybindspurger.forge;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyModifier;

public class LoaderSpecificUtilsImpl
{
    public static void clear(KeyMapping keyMapping)
    {
        keyMapping.setKeyModifierAndCode(KeyModifier.NONE, InputConstants.UNKNOWN);
    }

    public static void reset(KeyMapping keyMapping)
    {
        keyMapping.setKeyModifierAndCode(keyMapping.getDefaultKeyModifier(), keyMapping.getDefaultKey());
    }
}
