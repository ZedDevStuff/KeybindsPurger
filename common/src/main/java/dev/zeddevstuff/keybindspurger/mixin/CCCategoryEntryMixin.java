package dev.zeddevstuff.keybindspurger.mixin;

import com.blamejared.controlling.client.NewKeyBindsList;
import com.mojang.blaze3d.platform.InputConstants;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsListMixin;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
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

@Mixin(com.blamejared.controlling.client.NewKeyBindsList.CategoryEntry.class)
public class CCCategoryEntryMixin
{
    @Unique
    Button keybindspurger$purgeButton, keybindspurger$resetButton;

    @Shadow
    @Final
    private Component name;

    @Shadow @Final
    NewKeyBindsList this$0;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(NewKeyBindsList this$0, Component name, CallbackInfo ci)
    {
        keybindspurger$purgeButton = Button.builder(Component.literal("x"), this::keybindspurger$onButtonClicked)
                .tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge")))
                .size(12,12)
                .build();
        keybindspurger$resetButton = Button.builder(Component.literal("r"), this::keybindspurger$onButtonClicked)
                .tooltip(Tooltip.create(Component.translatable("button.keybindspurger.reset")))
                .size(12,12)
                .build();
        ((IKeyBindsScreenMixin)((IKeyBindsListMixin)this$0).keybindspurger$parent()).keybindspurger$addButton(keybindspurger$purgeButton);
        ((IKeyBindsScreenMixin)((IKeyBindsListMixin)this$0).keybindspurger$parent()).keybindspurger$addButton(keybindspurger$resetButton);
    }

    @Unique
    void keybindspurger$onButtonClicked(Button button)
    {
        if(button == keybindspurger$purgeButton)
        {
            var key = keybindspurger$getTranslationKey();
            Arrays.stream(Minecraft.getInstance().options.keyMappings).filter(km -> km.getCategory().equals(key)).forEach(km -> {
                km.setKey(InputConstants.UNKNOWN);
            });
            ((KeyBindsList)this.this$0).refreshEntries();
        }
        else if(button == keybindspurger$resetButton)
        {
            var key = keybindspurger$getTranslationKey();
            Arrays.stream(Minecraft.getInstance().options.keyMappings).filter(km -> km.getCategory().equals(key)).forEach(km -> {
                km.setKey(km.getDefaultKey());
            });
            ((KeyBindsList)this.this$0).refreshEntries();
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(GuiGraphics guiGraphics, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f, CallbackInfo ci)
    {
        if(!keybindspurger$purgeButton.isHovered())
        {
            keybindspurger$purgeButton.setFocused(false);
        }
        if(!keybindspurger$resetButton.isHovered())
        {
            keybindspurger$resetButton.setFocused(false);
        }
        keybindspurger$purgeButton.setX(0);
        keybindspurger$purgeButton.setY(j + m - 9 - 1);
        keybindspurger$resetButton.setX(12);
        keybindspurger$resetButton.setY(j + m - 9 - 1);
        keybindspurger$purgeButton.render(guiGraphics, n, o, f);
        keybindspurger$resetButton.render(guiGraphics, n, o, f);
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
