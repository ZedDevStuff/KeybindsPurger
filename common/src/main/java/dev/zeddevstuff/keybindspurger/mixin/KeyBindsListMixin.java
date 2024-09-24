package dev.zeddevstuff.keybindspurger.mixin;

import dev.zeddevstuff.keybindspurger.access.IKeyBindsListMixin;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyBindsList.class)
public class KeyBindsListMixin implements IKeyBindsListMixin
{

    @Shadow @Final
    KeyBindsScreen keyBindsScreen;

    @Override
    public KeyBindsScreen keybindspurger$parent()
    {
        return keyBindsScreen;
    }
}
