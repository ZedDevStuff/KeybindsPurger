package dev.zeddevstuff.keybindspurger.mixin;

import com.blamejared.controlling.client.NewKeyBindsScreen;
import dev.zeddevstuff.keybindspurger.common.Constants;
import dev.zeddevstuff.keybindspurger.common.Utils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
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

@Mixin(NewKeyBindsScreen.class)
public class CCKeyBindsScreenMixin extends KeyBindsScreen
{
	@Unique
	private Button purgeAllButton;
	@Unique
	private Button purgeNonVanillaButton;

	public CCKeyBindsScreenMixin(Screen screen, Options options) { super(screen, options); }


	@Inject(method = "init", at = @At("TAIL"))
	private void keybindspurger$init(CallbackInfo ci)
	{
		if(minecraft == null)
			return;
		purgeAllButton = addRenderableWidget(Button.builder(Component.literal("A"), this::keybindspurger$purgeAll)
			.tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge_all")))
			.pos(0, minecraft.getWindow().getGuiScaledHeight() - 32)
			.size(16,16)
			.build());
		purgeNonVanillaButton = addRenderableWidget(Button.builder(Component.literal("M"), this::keybindspurger$purgeAllNonVanilla)
			.tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge_non_vanilla")))
			.pos(0, minecraft.getWindow().getGuiScaledHeight() - 16)
			.size(16,16)
			.build());
	}
//	@Inject(method = "repositionElements", at = @At("TAIL"))
//	private void keybindspurger$repositionElements(CallbackInfo ci)
//	{
//		if(purgeAllButton == null || purgeNonVanillaButton == null || minecraft == null)
//			return;
//		purgeAllButton.setPosition(
//			0,
//			minecraft.getWindow().getGuiScaledHeight() - 32
//		);
//		purgeNonVanillaButton.setPosition(
//			0,
//			minecraft.getWindow().getGuiScaledHeight() - 16
//		);
//	}

	@Unique
	private void keybindspurger$purgeAll(Button button)
	{
		assert this.minecraft != null;
		for (KeyMapping keyMapping : this.minecraft.options.keyMappings)
		{
			Utils.clearKeyMapping(keyMapping);
		}
		keyBindsList.refreshEntries();
	}
	@Unique
	private void keybindspurger$purgeAllNonVanilla(Button button)
	{
		assert this.minecraft != null;
		for (KeyMapping keyMapping : this.minecraft.options.keyMappings)
		{
			if(!Constants.VANILLA_KEYBINDS.contains(keyMapping.getName()))
				Utils.clearKeyMapping(keyMapping);
		}
		keyBindsList.refreshEntries();
	}
}
