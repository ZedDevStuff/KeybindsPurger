package dev.zeddevstuff.keybindspurger.neoforge;

import dev.zeddevstuff.keybindspurger.Keybindspurger;
import net.neoforged.fml.common.Mod;

@Mod(Keybindspurger.MOD_ID)
public final class KeybindspurgerNeoForge {
    public KeybindspurgerNeoForge() {
        // Run our common setup.
        Keybindspurger.init();
    }
}
