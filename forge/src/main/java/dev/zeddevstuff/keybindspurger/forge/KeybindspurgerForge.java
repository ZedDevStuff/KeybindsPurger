package dev.zeddevstuff.keybindspurger.forge;

import dev.zeddevstuff.keybindspurger.Keybindspurger;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Keybindspurger.MOD_ID)
public final class KeybindspurgerForge
{
    public KeybindspurgerForge()
    {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(Keybindspurger.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        Keybindspurger.init();
    }
}
