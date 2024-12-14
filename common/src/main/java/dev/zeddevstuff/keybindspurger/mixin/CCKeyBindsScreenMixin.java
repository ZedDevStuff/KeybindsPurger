package dev.zeddevstuff.keybindspurger.mixin;

import com.blamejared.controlling.client.NewKeyBindsScreen;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.zeddevstuff.keybindspurger.Keybindspurger;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(NewKeyBindsScreen.class)
public abstract class CCKeyBindsScreenMixin extends Screen implements IKeyBindsScreenMixin
{
    protected CCKeyBindsScreenMixin(Component component)
    {
        super(component);
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void init(CallbackInfo ci)
    {
        if(minecraft == null)
            return;
        addRenderableWidget(new Button(
            0, minecraft.getWindow().getGuiScaledHeight() - 32,
            16,16,
            Component.literal("A"),
            this::keybindspurger$purgeAll,
            (button, poseStack, mouseX, mouseY) -> keybindspurger$onTooltip(Component.translatable("button.keybindspurger.purge_all"), button, poseStack, mouseX, mouseY)));
        addRenderableWidget(new Button(
            0, minecraft.getWindow().getGuiScaledHeight() - 16,
            16,16,
            Component.literal("M"),
            this::keybindspurger$purgeAllNonVanilla,
            (button, poseStack, mouseX, mouseY) -> keybindspurger$onTooltip(Component.translatable("button.keybindspurger.purge_non_vanilla"), button, poseStack, mouseX, mouseY)));
    }

    @Unique
    private void keybindspurger$onTooltip(Component component, Button button, PoseStack poseStack, int mouseX, int mouseY)
    {
        if(minecraft == null)
            return;
        renderTooltip(poseStack, component, mouseX, mouseY);
    }

    @Unique
    public void keybindspurger$purgeAll(Button button)
    {
        if(minecraft == null)
            return;
        for (KeyMapping keyMapping : this.minecraft.options.keyMappings)
        {
            keyMapping.setKey(InputConstants.UNKNOWN);
        }
    }
    @Unique
    public void keybindspurger$purgeAllNonVanilla(Button button)
    {
        if(minecraft == null)
            return;
        for (KeyMapping keyMapping : this.minecraft.options.keyMappings)
        {
            if(!Keybindspurger.VANILLA_KEYBINDS.contains(keyMapping.getName()))
                keyMapping.setKey(InputConstants.UNKNOWN);
        }
    }

    @Unique
    @Override
    public Button keybindspurger$addButton(Button button)
    {
        return addWidget(button);
    }
}