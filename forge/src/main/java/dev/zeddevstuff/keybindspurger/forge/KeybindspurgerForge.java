package dev.zeddevstuff.keybindspurger.forge;

import net.minecraftforge.fml.common.Mod;

import dev.zeddevstuff.keybindspurger.Keybindspurger;

@Mod(Keybindspurger.MOD_ID)
public final class KeybindsPurgerForge {
    public KeybindsPurgerForge() {
        // Run our common setup.
        Keybindspurger.init();
    }
}
