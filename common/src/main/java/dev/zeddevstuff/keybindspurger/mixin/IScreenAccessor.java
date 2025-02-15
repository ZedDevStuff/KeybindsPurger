package dev.zeddevstuff.keybindspurger.mixin;

import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Screen.class)
public interface IScreenAccessor
{
    @Invoker("addRenderableWidget")
    <T extends GuiEventListener & Renderable & NarratableEntry> T pAddRenderableWidget(T guiEventListener);
    @Invoker("addWidget")
    <T extends GuiEventListener & NarratableEntry> T pAddWidget(T guiEventListener);
    @Invoker("addRenderableOnly")
    <T extends Renderable> T pAddRenderableWidget(T renderable);

}
