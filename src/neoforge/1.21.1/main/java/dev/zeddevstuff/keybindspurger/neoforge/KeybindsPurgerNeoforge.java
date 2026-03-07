package dev.zeddevstuff.keybindspurger.neoforge;

import com.mojang.logging.LogUtils;
import dev.zeddevstuff.keybindspurger.common.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Constants.MOD_ID)
public class KeybindsPurgerNeoforge
{
	private final Logger LOGGER = LogUtils.getLogger();
	public KeybindsPurgerNeoforge(IEventBus modEventBus)
	{
		LOGGER.info("Initializing Keybinds Purger for NeoForge");
	}
}
