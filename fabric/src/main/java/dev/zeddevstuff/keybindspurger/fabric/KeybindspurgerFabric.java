package dev.zeddevstuff.keybindspurger.fabric;

import dev.zeddevstuff.keybindspurger.Keybindspurger;
import net.fabricmc.api.ModInitializer;

public final class KeybindspurgerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Keybindspurger.init();
    }
}
