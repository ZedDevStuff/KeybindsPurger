package dev.zeddevstuff.keybindspurger.neoforge;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyModifier;

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
