package dev.zeddevstuff.keybindspurger;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.controls.KeyBindsScreen;
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

    public static void init()
    {
        // Write common init code here.
    }
}
