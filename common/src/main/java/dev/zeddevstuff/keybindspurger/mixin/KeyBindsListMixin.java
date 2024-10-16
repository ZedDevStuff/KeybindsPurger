package dev.zeddevstuff.keybindspurger.mixin;

import dev.zeddevstuff.keybindspurger.access.IKeyBindsListMixin;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.controls.KeyBindsList;
import net.minecraft.client.gui.screens.controls.KeyBindsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBindsList.class)
public class KeyBindsListMixin implements IKeyBindsListMixin
{

    @Shadow @Final private KeyBindsScreen keyBindsScreen;

    @Override
    public KeyBindsScreen keybindspurger$parent()
    {
        return keyBindsScreen;
    }
}
