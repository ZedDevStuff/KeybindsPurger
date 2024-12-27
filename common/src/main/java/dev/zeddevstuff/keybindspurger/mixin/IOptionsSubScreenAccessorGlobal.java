package dev.zeddevstuff.keybindspurger.mixin;

import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(OptionsSubScreen.class)
public interface IOptionsSubScreenAccessor
{
    @Accessor("options")
    Options pOptions();
}
