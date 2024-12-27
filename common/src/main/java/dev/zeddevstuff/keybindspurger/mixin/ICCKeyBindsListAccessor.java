package dev.zeddevstuff.keybindspurger.mixin;

import com.blamejared.controlling.client.NewKeyBindsList;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NewKeyBindsList.class)
public interface ICCKeyBindsListAccessor
{
    @Accessor("controlsScreen")
    KeyBindsScreen pKeyBindsScreen();
}
