package dev.zeddevstuff.keybindspurger.fabric;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;

public class KeybindsPurgerFabric implements ClientModInitializer
{
	private final Logger LOGGER = LogUtils.getLogger();
	@Override
	public void onInitializeClient()
	{
		LOGGER.info("Initializing KeybindsPurger for Fabric");
		for(String mod : dev.zeddevstuff.keybindspurger.common.Utils.getMods())
		{
			LOGGER.info("Found mod: " + mod);
		}
	}
}
