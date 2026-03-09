package dev.zeddevstuff.keybindspurger.mixin;

import com.blamejared.controlling.client.NewKeyBindsList;
import dev.zeddevstuff.keybindspurger.common.Utils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(NewKeyBindsList.CategoryEntry.class)
public class CCKeyBindsListCategoryEntryMixin
{
	private NewKeyBindsList keyBindsList;
	private Button purgeButton;
	private Button resetButton;
	@Inject(method = "<init>", at = @At("TAIL"))
	private void keybindspurger$init(NewKeyBindsList keybindsList, Component par2, CallbackInfo ci)
	{
		this.keyBindsList = keybindsList;
		purgeButton = Button.builder(Component.literal("x"), this::keybindspurger$purgeButtonClicked)
			.tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge")))
			.pos(0, 0)
			.size(12, 12)
			.build();
		resetButton = Button.builder(Component.literal("r"), this::keybindspurger$resetButtonClicked)
			.tooltip(Tooltip.create(Component.translatable("button.keybindspurger.reset")))
			.pos(0, 0)
			.size(12, 12)
			.build();
		keybindsList.keyBindsScreen.addWidget(purgeButton);
		keybindsList.keyBindsScreen.addWidget(resetButton);
	}
	@Inject(method = "render", at = @At("TAIL"))
	private void keybindspurger$render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick, CallbackInfo ci)
	{
		if(!purgeButton.isHovered())
			purgeButton.setFocused(false);
		if(!resetButton.isHovered())
			resetButton.setFocused(false);
		purgeButton.setX(0);
		purgeButton.setY((top + height - 9 - 1));
		resetButton.setX(12);
		resetButton.setY((top + height - 9 - 1));
		purgeButton.render(guiGraphics, mouseX, mouseY, partialTick);
		resetButton.render(guiGraphics, mouseX, mouseY, partialTick);
	}

	private void keybindspurger$purgeButtonClicked(Button button)
	{
		for(var keyMapping : getKeyMappingsForCategory((NewKeyBindsList.CategoryEntry) (Object) this))
			Utils.clearKeyMapping(keyMapping);
		keyBindsList.refreshEntries();
	}
	private void keybindspurger$resetButtonClicked(Button button)
	{
		for(var keyMapping : getKeyMappingsForCategory((NewKeyBindsList.CategoryEntry) (Object) this))
			Utils.resetKeyMapping(keyMapping);
		keyBindsList.refreshEntries();
	}

	private List<KeyMapping> getKeyMappingsForCategory(NewKeyBindsList.CategoryEntry category)
	{
		int items = keyBindsList.getItemCount();
		List<KeyMapping> keyMappings = null;
		for(int i = 0; i < items; i++)
		{
			NewKeyBindsList.Entry entry = keyBindsList.getEntry(i);
			if(entry == category)
				keyMappings = new ArrayList<>();
			else if(entry instanceof NewKeyBindsList.KeyEntry keyEntry && keyMappings != null)
			{
				keyMappings.add(keyEntry.getKey());
			}
			else if (entry instanceof NewKeyBindsList.CategoryEntry && keyMappings != null)
				break;
		}
		if(keyMappings == null)
			keyMappings = new ArrayList<>();
		return keyMappings;
	}
}
