package dev.zeddevstuff.keybindspurger;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.util.List;

public class ModListGetter
{
    @ExpectPlatform
    public static List<String> getModList()
    {
        throw new AssertionError("This should not be called!");
    }
}
