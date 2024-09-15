package dev.zeddevstuff.keybindspurger.mixin;

import dev.zeddevstuff.keybindspurger.access.IControlListMixin;
import net.minecraft.client.gui.screens.controls.ControlList;
import net.minecraft.client.gui.screens.controls.ControlsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ControlList.class)
public class ControlListMixin implements IControlListMixin
{

    @Shadow @Final private ControlsScreen controlsScreen;

    @Override
    public ControlsScreen parent()
    {
        return controlsScreen;
    }
}
