package dev.zeddevstuff.keybindspurger.common;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MixinManager implements IMixinConfigPlugin
{
	private boolean controllingDetected = false;
	@Override
	public void onLoad(String s)
	{
		List<String> mods = Utils.getMods();
		controllingDetected = mods.contains("controlling");
	}

	@Override
	public String getRefMapperConfig()
	{
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
	{
		boolean apply = true;
		if(controllingDetected) {
			apply = mixinClassName.toLowerCase(Locale.ROOT).startsWith("dev.zeddevstuff.keybindspurger.mixin.cc") || mixinClassName.toLowerCase(Locale.ROOT).startsWith("dev.zeddevstuff.keybindspurger.mixin.icc");
		} else {
			apply = !mixinClassName.toLowerCase(Locale.ROOT).startsWith("dev.zeddevstuff.keybindspurger.mixin.cc") && !mixinClassName.toLowerCase(Locale.ROOT).startsWith("dev.zeddevstuff.keybindspurger.mixin.icc");
		}
		return apply;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets)
	{

	}

	@Override
	public List<String> getMixins() { return List.of(); }

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
	{

	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
	{

	}
}
