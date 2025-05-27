package dev.zeddevstuff.keybindspurger;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.KeyMapping;

import java.util.List;

public class LoaderSpecificUtils
{
    @ExpectPlatform
    public static void clear(KeyMapping keyMapping)
    {
        throw new AssertionError("This should not be called!");
    }

    @ExpectPlatform
    public static void reset(KeyMapping keyMapping)
    {
        throw new AssertionError("This should not be called!");
    }
}
