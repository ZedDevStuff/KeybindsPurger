package dev.zeddevstuff.keybindspurger.mixin;

import dev.zeddevstuff.keybindspurger.Keybindspurger;
import dev.zeddevstuff.keybindspurger.ModListGetter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MixinManager implements IMixinConfigPlugin
{
    private boolean controllingDetected = false;
    @Override
    public void onLoad(String s)
    {
        boolean isNeoForge = false;
        List<String> mods = ModListGetter.getModList();
        controllingDetected = mods.contains("controlling");
        Keybindspurger.LOGGER.info("Controlling detected: " + controllingDetected);

    }

    @Override
    public String getRefMapperConfig()
    {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        boolean apply;
        if(controllingDetected) {
            apply = mixinClassName.startsWith("dev.zeddevstuff.keybindspurger.mixin.CC");
        } else {
            apply = !mixinClassName.startsWith("dev.zeddevstuff.keybindspurger.mixin.CC");
        }
        if(apply) Keybindspurger.LOGGER.info("Applying mixin: " + mixinClassName);
        return apply;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets)
    {

    }

    @Override
    public List<String> getMixins()
    {
        return List.of();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {

    }
}
