package dev.zeddevstuff.keybindspurger.mixin;

import com.blamejared.controlling.client.NewKeyBindsScreen;
import com.mojang.blaze3d.platform.InputConstants;
import dev.zeddevstuff.keybindspurger.Keybindspurger;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(NewKeyBindsScreen.class)
public abstract class CCKeyBindsScreenMixin extends Screen implements IKeyBindsScreenMixin
{

    @Shadow
    public abstract KeyBindsList getKeyBindsList();

    protected CCKeyBindsScreenMixin(Component component)
    {
        super(component);
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void init(CallbackInfo ci)
    {
        if(minecraft == null)
            return;
        addRenderableWidget(Button.builder(Component.literal("A"), this::keybindspurger$purgeAll)
                .tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge_all")))
                .pos(0, minecraft.getWindow().getGuiScaledHeight() - 32)
                .size(16,16)
                .build());
        addRenderableWidget(Button.builder(Component.literal("M"), this::keybindspurger$purgeAllNonVanilla)
                .tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge_non_vanilla")))
                .pos(0, minecraft.getWindow().getGuiScaledHeight() - 16)
                .size(16,16)
                .build());
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
        this.getKeyBindsList().refreshEntries();
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
        this.getKeyBindsList().refreshEntries();
    }

    @Override
    public void keybindspurger$addButton(Button button)
    {
        addWidget(button);
    }
}