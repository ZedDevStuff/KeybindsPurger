package dev.zeddevstuff.keybindspurger;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.controls.KeyBindsScreen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;

import java.util.List;

public final class Keybindspurger
{
    public static final String MOD_ID = "keybindspurger";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final List<String> VANILLA_KEYBINDS = List.of(
        "key.attack",
        "key.use",
        "key.forward",
        "key.left",
        "key.back",
        "key.right",
        "key.jump",
        "key.sneak",
        "key.sprint",
        "key.drop",
        "key.inventory",
        "key.chat",
        "key.playerlist",
        "key.pickItem",
        "key.command",
        "key.socialInteractions",
        "key.screenshot",
        "key.togglePerspective",
        "key.smoothCamera",
        "key.fullscreen",
        "key.spectatorOutlines",
        "key.swapOffhand",
        "key.saveToolbarActivator",
        "key.loadToolbarActivator",
        "key.advancements",
        "key.hotbar.1",
        "key.hotbar.2",
        "key.hotbar.3",
        "key.hotbar.4",
        "key.hotbar.5",
        "key.hotbar.6",
        "key.hotbar.7",
        "key.hotbar.8",
        "key.hotbar.9",
        "key.modmenu.open_menu"
    );

    private static Component PURGE = null;
    public static Component getPURGE()
    {
        if(PURGE == null)
        {
            if(Component.translatable("button.keybindspurger.purge").getString().equals("button.keybindspurger.purge"))
            {
                PURGE = Component.literal("Purge");
            }
            else PURGE = Component.translatable("button.keybindspurger.purge");
        }
        return PURGE;
    }
    private static Component ALL = null;

    public static Component getALL()
    {
        if(ALL == null)
        {
            if(Component.translatable("button.keybindspurger.purge_all").getString().equals("button.keybindspurger.purge_all"))
            {
                ALL = Component.literal("Purge All");
            }
            else ALL = Component.translatable("button.keybindspurger.purge_all");
        }
        return ALL;
    }

    private static Component NON_VANILLA = null;

    public static Component getNON_VANILLA()
    {
        if(NON_VANILLA == null)
        {
            if(Component.translatable("button.keybindspurger.purge_non_vanilla").getString().equals("button.keybindspurger.purge_non_vanilla"))
            {
                NON_VANILLA = Component.literal("Purge Non-Vanilla");
            }
            else NON_VANILLA = Component.translatable("button.keybindspurger.purge_non_vanilla");
        }
        return NON_VANILLA;
    }

    public static void init()
    {
        // Write common init code here.
    }
}
