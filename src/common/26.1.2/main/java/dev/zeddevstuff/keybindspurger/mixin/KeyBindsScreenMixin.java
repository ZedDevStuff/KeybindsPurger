package dev.zeddevstuff.keybindspurger.mixin;

import dev.zeddevstuff.keybindspurger.common.Constants;
import dev.zeddevstuff.keybindspurger.common.Utils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBindsScreen.class)
public class KeyBindsScreenMixin extends Screen
{
	@Shadow
	public KeyBindsList keyBindsList;
	@Unique
	private Button keybindsPurger$purgeAllButton;
	@Unique
	private Button keybindsPurger$purgeNonVanillaButton;

	protected KeyBindsScreenMixin(Component component)
	{
		super(component);
	}

	@Inject(method = "addContents", at = @At("TAIL"))
	private void keybindsPurger$init(CallbackInfo ci)
	{
		keybindsPurger$purgeAllButton = addRenderableWidget(Button.builder(Component.literal("A"), this::keybindsPurger$purgeAll)
			.tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge_all")))
			.pos(0, minecraft.getWindow().getGuiScaledHeight() - 32)
			.size(16,16)
			.build());
		keybindsPurger$purgeNonVanillaButton = addRenderableWidget(Button.builder(Component.literal("M"), this::keybindsPurger$purgeAllNonVanilla)
			.tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge_non_vanilla")))
			.pos(0, minecraft.getWindow().getGuiScaledHeight() - 16)
			.size(16,16)
			.build());
	}
	@Inject(method = "repositionElements", at = @At("TAIL"))
	private void keybindsPurger$repositionElements(CallbackInfo ci)
	{
		if(keybindsPurger$purgeAllButton == null || keybindsPurger$purgeNonVanillaButton == null || minecraft == null)
			return;
		keybindsPurger$purgeAllButton.setPosition(
			0,
			minecraft.getWindow().getGuiScaledHeight() - 32
		);
		keybindsPurger$purgeNonVanillaButton.setPosition(
			0,
			minecraft.getWindow().getGuiScaledHeight() - 16
		);
	}

	@Unique
	private void keybindsPurger$purgeAll(Button button)
	{
		assert this.minecraft != null;
		for (KeyMapping keyMapping : this.minecraft.options.keyMappings)
		{
			Utils.clearKeyMapping(keyMapping);
		}
		keyBindsList.refreshEntries();
	}
	@Unique
	private void keybindsPurger$purgeAllNonVanilla(Button button)
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
