package dev.zeddevstuff.keybindspurger;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.Arrays;
import java.util.List;

public final class Keybindspurger
{
    public static final String MOD_ID = "keybindspurger";
    public static final List<String> VANILLA_KEYBINDS = Arrays.asList(
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
            if(new TranslatableComponent("button.keybindspurger.purge").getString().equals("button.keybindspurger.purge"))
            {
                PURGE = new TextComponent("Purge all keybinds in this category");
            }
            else PURGE = new TranslatableComponent("button.keybindspurger.purge");
        }
        return PURGE;
    }
    private static Component ALL = null;

    public static Component getALL()
    {
        if(ALL == null)
        {
            if(new TranslatableComponent("button.keybindspurger.purge_all").getString().equals("button.keybindspurger.purge_all"))
            {
                ALL = new TextComponent("Purge all keybinds");
            }
            else ALL = new TranslatableComponent("button.keybindspurger.purge_all");
        }
        return ALL;
    }

    private static Component NON_VANILLA = null;

    public static Component getNON_VANILLA()
    {
        if(NON_VANILLA == null)
        {
            if(new TranslatableComponent("button.keybindspurger.purge_non_vanilla").getString().equals("button.keybindspurger.purge_non_vanilla"))
            {
                NON_VANILLA = new TextComponent("Purge all non-vanilla keybinds");
            }
            else NON_VANILLA = new TranslatableComponent("button.keybindspurger.purge_non_vanilla");
        }
        return NON_VANILLA;
    }

    private static Component RESET = null;

    public static Component getRESET()
    {
        if(RESET == null)
        {
            if(new TranslatableComponent("button.keybindspurger.reset").getString().equals("button.keybindspurger.reset"))
            {
                RESET = new TextComponent("Reset all keybinds in this category");
            }
            else RESET = new TranslatableComponent("button.keybindspurger.reset");
        }
        return RESET;
    }
    
    public static void init() {
        // Write common init code here.
    }
}
