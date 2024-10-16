package dev.zeddevstuff.keybindspurger.mixin;

import com.blamejared.controlling.client.NewKeyBindsList;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsListMixin;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NewKeyBindsList.class)
public class CCKeyBindsListMixin implements IKeyBindsListMixin
{
    @Shadow @Final private KeyBindsScreen controlsScreen;

    @Override
    public KeyBindsScreen keybindspurger$parent() { return controlsScreen; }
}
