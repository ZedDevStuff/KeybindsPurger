package dev.zeddevstuff.keybindspurger.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.*;
import dev.zeddevstuff.keybindspurger.Keybindspurger;
import dev.zeddevstuff.keybindspurger.access.IControlListMixin;
import dev.zeddevstuff.keybindspurger.access.IControlsScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.controls.ControlList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(ControlList.CategoryEntry.class)
public class CategoryEntryMixin
{
    Button purgeButton, resetButton;

    @Shadow @Final Component name;

    @Shadow @Final private ControlList field_2738;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(ControlList controlList, Component component, CallbackInfo ci)
    {
        purgeButton = new Button(
            0,0,
            12, 12,
            new TextComponent("x"),
            this::keybindspurger$onButtonClicked,
            (button, poseStack, mouseX, mouseY) -> keybindspurger$onTooltip(Keybindspurger.getPURGE(), button, poseStack, mouseX, mouseY));
        resetButton = new Button(
            0,0,
            12, 12,
            new TextComponent("r"),
            this::keybindspurger$onButtonClicked,
            (button, poseStack, mouseX, mouseY) -> keybindspurger$onTooltip(Keybindspurger.getRESET(), button, poseStack, mouseX, mouseY));
        ((IControlsScreenMixin)((IControlListMixin)controlList).parent()).mAddButton(purgeButton);
        ((IControlsScreenMixin)((IControlListMixin)controlList).parent()).mAddButton(resetButton);
    }

    @Unique
    void keybindspurger$onButtonClicked(Button button)
    {
        if(button == purgeButton)
        {
            String key = getTranslationKey();
            Arrays.stream(Minecraft.getInstance().options.keyMappings).filter(km -> km.getCategory().equals(key)).forEach(km -> {
                km.setKey(InputConstants.UNKNOWN);
            });
        }
        else if(button == resetButton)
        {
            String key = getTranslationKey();
            Arrays.stream(Minecraft.getInstance().options.keyMappings).filter(km -> km.getCategory().equals(key)).forEach(km -> {
                km.setKey(km.getDefaultKey());
            });
        }
    }
    private void keybindspurger$onTooltip(Component component, Button button, PoseStack poseStack, int mouseX, int mouseY)
    {
        ((IControlListMixin)field_2738).parent().renderTooltip(poseStack, component, mouseX, mouseY);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(PoseStack poseStack, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f, CallbackInfo ci)
    {
        purgeButton.x = 0;
        purgeButton.y = (j + m - 9 - 1);
        resetButton.x = 12;
        resetButton.y = (j + m - 9 - 1);
        purgeButton.render(poseStack, n, o, f);
        resetButton.render(poseStack, n, o, f);
    }

    public String getTranslationKey()
    {
        if(name instanceof TranslatableComponent)
        {
            return ((TranslatableComponent)name).getKey();
        }
        return name.getString();
    }
}
