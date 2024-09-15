package dev.zeddevstuff.keybindspurger.forge;

import dev.zeddevstuff.keybindspurger.Keybindspurger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Keybindspurger.MOD_ID)
public final class KeybindspurgerForge
{
    public KeybindspurgerForge()
    {
        // Run our common setup.
        Keybindspurger.init();
    }
}
