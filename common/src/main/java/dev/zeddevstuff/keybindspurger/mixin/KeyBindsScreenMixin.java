package dev.zeddevstuff.keybindspurger.mixin;

import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.controls.KeyBindsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(Screen.class)
public class KeyBindsScreenMixin implements IKeyBindsScreenMixin
{
    @Shadow
    protected <T extends GuiEventListener & NarratableEntry> T addWidget(T guiEventListener) { return null; }

    @Override
    public void addButton(Button button)
    {
        addWidget(button);
    }
}
