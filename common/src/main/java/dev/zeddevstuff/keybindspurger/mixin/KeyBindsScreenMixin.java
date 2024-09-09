package dev.zeddevstuff.keybindspurger.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import dev.zeddevstuff.keybindspurger.Keybindspurger;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.controls.KeyBindsList;
import net.minecraft.client.gui.screens.controls.KeyBindsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(KeyBindsScreen.class)
public class KeyBindsScreenMixin extends Screen implements IKeyBindsScreenMixin
{
    @Shadow private KeyBindsList keyBindsList;

    protected KeyBindsScreenMixin(Component component)
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
            60,16,
            Keybindspurger.getALL(),
            this::keybindspurger$purgeAll));
        addRenderableWidget(new Button(
            0, minecraft.getWindow().getGuiScaledHeight() - 16,
            60,16,
            Keybindspurger.getNON_VANILLA(),
            this::keybindspurger$purgeAllNonVanilla));
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
        //keyBindsList.refreshEntries();
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
        //keyBindsList.refreshEntries();
    }

    @Override
    public void addButton(Button button)
    {
        addWidget(button);
    }
}
