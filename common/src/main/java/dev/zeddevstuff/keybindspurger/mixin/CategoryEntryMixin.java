package dev.zeddevstuff.keybindspurger.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.zeddevstuff.keybindspurger.Keybindspurger;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsListMixin;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.controls.KeyBindsList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(KeyBindsList.CategoryEntry.class)
public class CategoryEntryMixin
{
    Button purgeButton;

    @Shadow @Final Component name;

    @Shadow @Final private KeyBindsList field_2738;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(KeyBindsList keyBindsList, Component component, CallbackInfo ci)
    {
        purgeButton = new Button(
            0,0,
            50, 10,
            Keybindspurger.getPURGE(),
            this::onButtonClicked);
        ((IKeyBindsScreenMixin)((IKeyBindsListMixin)keyBindsList).parent()).addButton(purgeButton);
    }

    void onButtonClicked(Button button)
    {
        var key = getTranslationKey();
        Arrays.stream(Minecraft.getInstance().options.keyMappings).filter(km -> km.getCategory().equals(key)).forEach(km -> {
            km.setKey(InputConstants.UNKNOWN);
        });
        //field_2738.refreshEntries();
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(PoseStack poseStack, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f, CallbackInfo ci)
    {
        /*if(!purgeButton.isHoveredOrFocused())
        {
            purgeButton.changeFocus(false);
        }*/
        //var right = guiGraphics.guiWidth() - 50;
        purgeButton.x = 0;
        purgeButton.y = (j + m - 9 - 1);
        purgeButton.render(poseStack, n, o, f);
    }

    public String getTranslationKey()
    {
        if(name.getContents() instanceof TranslatableContents tr)
        {
            return tr.getKey();
        }
        return "not translatable";
    }
}
