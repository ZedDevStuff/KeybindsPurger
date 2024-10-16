package dev.zeddevstuff.keybindspurger.neoforge;

import dev.zeddevstuff.keybindspurger.Keybindspurger;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.moddiscovery.ModInfo;

@Mod(Keybindspurger.MOD_ID)
public final class KeybindspurgerNeoForge {
    public KeybindspurgerNeoForge() {
        // Run our common setup.
        net.neoforged.fml.loading.LoadingModList.get().getMods().stream().map(mod -> mod.getModId()).toList();
        Keybindspurger.init();
    }
}
