package dev.zeddevstuff.keybindspurger.mixin;

import com.blamejared.controlling.client.NewKeyBindsList;
import dev.zeddevstuff.keybindspurger.common.Utils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.options.controls.KeyBindsList;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(NewKeyBindsList.CategoryEntry.class)
public abstract class CCKeyBindsListCategoryEntryMixin extends KeyBindsList.Entry
{
	private NewKeyBindsList keybindsPurger$keyBindsList;
	private KeyMapping.Category keybindsPurger$category;
	private Button keybindsPurger$purgeButton;
	private Button keybindsPurger$resetButton;
	@Inject(method = "<init>", at = @At("TAIL"))
	private void keybindsPurger$init(NewKeyBindsList keybindsList, KeyMapping.Category category, CallbackInfo ci)
	{
		this.keybindsPurger$keyBindsList = keybindsList;
		keybindsPurger$purgeButton = Button.builder(Component.literal("x"), this::keybindsPurger$purgeButtonClicked)
			.tooltip(Tooltip.create(Component.translatable("button.keybindspurger.purge")))
			.pos(0, 0)
			.size(12, 12)
			.build();
		keybindsPurger$resetButton = Button.builder(Component.literal("r"), this::keybindsPurger$resetButtonClicked)
			.tooltip(Tooltip.create(Component.translatable("button.keybindspurger.reset")))
			.pos(0, 0)
			.size(12, 12)
			.build();
		keybindsList.keyBindsScreen.addWidget(keybindsPurger$purgeButton);
		keybindsList.keyBindsScreen.addWidget(keybindsPurger$resetButton);
	}
	@Inject(method = "extractContent", at = @At("TAIL"))
	private void keybindsPurger$render(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY, boolean hovered, float partialTick, CallbackInfo ci)
	{
		if(!keybindsPurger$purgeButton.isHovered())
			keybindsPurger$purgeButton.setFocused(false);
		if(!keybindsPurger$resetButton.isHovered())
			keybindsPurger$resetButton.setFocused(false);
		keybindsPurger$purgeButton.setX(0);
		keybindsPurger$purgeButton.setY((getY() + getHeight()/2 - 9 - 1));
		keybindsPurger$resetButton.setX(12);
		keybindsPurger$resetButton.setY((getY() + getHeight()/2 - 9 - 1));
		keybindsPurger$purgeButton.extractRenderState(guiGraphicsExtractor, mouseX, mouseY, partialTick);
		keybindsPurger$resetButton.extractRenderState(guiGraphicsExtractor, mouseX, mouseY, partialTick);
	}

	private void keybindsPurger$purgeButtonClicked(Button button)
	{
		for(var keyMapping : getKeyMappingsForCategory((NewKeyBindsList.CategoryEntry) (Object) this))
			Utils.clearKeyMapping(keyMapping);
		keybindsPurger$keyBindsList.refreshEntries();
	}
	private void keybindsPurger$resetButtonClicked(Button button)
	{
		for(var keyMapping : getKeyMappingsForCategory((NewKeyBindsList.CategoryEntry) (Object) this))
			Utils.resetKeyMapping(keyMapping);
		keybindsPurger$keyBindsList.refreshEntries();
	}

	private List<KeyMapping> getKeyMappingsForCategory(NewKeyBindsList.CategoryEntry category)
	{
		int items = keybindsPurger$keyBindsList.getItemCount();
		List<KeyMapping> keyMappings = null;
		for(int i = 0; i < items; i++)
		{
			KeyBindsList.Entry entry = keybindsPurger$keyBindsList.children().get(i);
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
