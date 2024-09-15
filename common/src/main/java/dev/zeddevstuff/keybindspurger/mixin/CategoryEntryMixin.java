package dev.zeddevstuff.keybindspurger.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsListMixin;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.controls.KeyBindsList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(KeyBindsList.CategoryEntry.class)
public class CategoryEntryMixin
{
    Button purgeButton, resetButton;

    @Shadow @Final Component name;

    @Shadow @Final private KeyBindsList field_2738;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(KeyBindsList keyBindsList, Component component, CallbackInfo ci)
    {
        purgeButton = Button.builder(Component.literal("x"), this::keybindspurger$onButtonClicked)
            .tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge")))
            .size(12,12)
            .build();
        resetButton = Button.builder(Component.literal("r"), this::keybindspurger$onButtonClicked)
            .tooltip(Tooltip.create(Component.translatable("button.keybindspurger.reset")))
            .size(12,12)
            .build();
        ((IKeyBindsScreenMixin)((IKeyBindsListMixin)keyBindsList).parent()).addButton(purgeButton);
        ((IKeyBindsScreenMixin)((IKeyBindsListMixin)keyBindsList).parent()).addButton(resetButton);
    }

    @Unique
    void keybindspurger$onButtonClicked(Button button)
    {
        if(button == purgeButton)
        {
            var key = keybindspurger$getTranslationKey();
            Arrays.stream(Minecraft.getInstance().options.keyMappings).filter(km -> km.getCategory().equals(key)).forEach(km -> {
                km.setKey(InputConstants.UNKNOWN);
            });
            field_2738.refreshEntries();
        }
        else if(button == resetButton)
        {
            var key = keybindspurger$getTranslationKey();
            Arrays.stream(Minecraft.getInstance().options.keyMappings).filter(km -> km.getCategory().equals(key)).forEach(km -> {
                km.setKey(km.getDefaultKey());
            });
            field_2738.refreshEntries();
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(GuiGraphics guiGraphics, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f, CallbackInfo ci)
    {
        if(!purgeButton.isHovered())
        {
            purgeButton.setFocused(false);
        }
        if(!resetButton.isHovered())
        {
            resetButton.setFocused(false);
        }
        purgeButton.setX(0);
        purgeButton.setY(j + m - 9 - 1);
        resetButton.setX(12);
        resetButton.setY(j + m - 9 - 1);
        purgeButton.render(guiGraphics, n, o, f);
        resetButton.render(guiGraphics, n, o, f);
    }

    @Unique
    public String keybindspurger$getTranslationKey()
    {
        if(name.getContents() instanceof TranslatableContents tr)
        {
            return tr.getKey();
        }
        return "not translatable";
    }
}
