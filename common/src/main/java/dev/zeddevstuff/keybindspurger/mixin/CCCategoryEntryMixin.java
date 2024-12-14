package dev.zeddevstuff.keybindspurger.mixin;

import com.blamejared.controlling.client.NewKeyBindsList;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.zeddevstuff.keybindspurger.Keybindspurger;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsListMixin;
import dev.zeddevstuff.keybindspurger.access.IKeyBindsScreenMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
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

@Mixin(NewKeyBindsList.CategoryEntry.class)
public abstract class CCCategoryEntryMixin
{
    @Unique
    Button keybindspurger$purgeButton, keybindspurger$resetButton;

    @Shadow @Final
    NewKeyBindsList this$0;

    @Shadow @Final private String name;

    @Shadow @Final private String labelText;

    @Shadow public abstract String getName();

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(NewKeyBindsList this$0, String name, CallbackInfo ci)
    {
        keybindspurger$purgeButton = new Button(
            0,0,
            12, 12,
            Component.literal("x"),
            this::keybindspurger$onButtonClicked,
            (button, poseStack, mouseX, mouseY) -> keybindspurger$onTooltip(Component.translatable("button.keybindspurger.purge"), button, poseStack, mouseX, mouseY));
        keybindspurger$resetButton = new Button(
            12,0,
            12, 12,
            Component.literal("r"),
            this::keybindspurger$onButtonClicked,
            (button, poseStack, mouseX, mouseY) -> keybindspurger$onTooltip(Component.translatable("button.keybindspurger.reset"), button, poseStack, mouseX, mouseY));
        keybindspurger$purgeButton = ((IKeyBindsScreenMixin)((IKeyBindsListMixin)this$0).keybindspurger$parent()).keybindspurger$addButton(keybindspurger$purgeButton);
        keybindspurger$resetButton = ((IKeyBindsScreenMixin)((IKeyBindsListMixin)this$0).keybindspurger$parent()).keybindspurger$addButton(keybindspurger$resetButton);
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
            //((KeyBindsList)this$0).refreshEntries();
        }
        else if(button == keybindspurger$resetButton)
        {
            var key = keybindspurger$getTranslationKey();
            Arrays.stream(Minecraft.getInstance().options.keyMappings).filter(km -> km.getCategory().equals(key)).forEach(km -> {
                km.setKey(km.getDefaultKey());
            });
            //((KeyBindsList)this$0).refreshEntries();
        }
        else throw new RuntimeException("Unknown button clicked");
    }
    @Unique
    private void keybindspurger$onTooltip(Component component, Button button, PoseStack poseStack, int mouseX, int mouseY)
    {
        ((IKeyBindsListMixin)this$0).keybindspurger$parent().renderTooltip(poseStack, component, mouseX, mouseY);
    }

    // FOR SOME REASON THE BUTTON ISN'T WHERE IT'S RENDERED
    @Inject(method = "render", at = @At("TAIL"))
    public void render(PoseStack poseStack, int i, int j, int k, int l, int m, int n, int o, boolean bl, float f, CallbackInfo ci)
    {
        keybindspurger$purgeButton.x = 0;
        keybindspurger$purgeButton.y = (j + m - 9 - 1);
        keybindspurger$resetButton.x = 12;
        keybindspurger$resetButton.y = (j + m - 9 - 1);
        keybindspurger$purgeButton.render(poseStack, n, o, f);
        keybindspurger$resetButton.render(poseStack, n, o, f);
    }

    @Unique
    public String keybindspurger$getTranslationKey()
    {
        return this.getName();
    }
}
