package dev.zeddevstuff.keybindspurger.mixin;

import com.google.common.reflect.ClassPath;
import dev.zeddevstuff.keybindspurger.Keybindspurger;
import dev.zeddevstuff.keybindspurger.ModListGetter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MixinManager implements IMixinConfigPlugin
{
    private List<String> mixins = new ArrayList<>();
    private boolean controllingDetected = false;
    private static boolean neoForgeDetected = false;
    public static boolean isNeoForge()
    {
        return neoForgeDetected;
    }
    @Override
    public void onLoad(String s)
    {
        List<String> mods = ModListGetter.getModList();
        neoForgeDetected = mods.contains("neoforge");
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
        boolean apply = true;
        if(controllingDetected) {
            apply = mixinClassName.toLowerCase().startsWith("dev.zeddevstuff.keybindspurger.mixin.cc") || mixinClassName.toLowerCase().startsWith("dev.zeddevstuff.keybindspurger.mixin.icc");
        } else {
            apply = !mixinClassName.toLowerCase().startsWith("dev.zeddevstuff.keybindspurger.mixin.cc") || !mixinClassName.toLowerCase().startsWith("dev.zeddevstuff.keybindspurger.mixin.icc");
        }
        if(mixinClassName.equals("dev.zeddevstuff.keybindspurger.mixin.IKeyBindsListAccessor") ||
            mixinClassName.equals("dev.zeddevstuff.keybindspurger.mixin.IScreenAccessor") ||
            mixinClassName.equals("dev.zeddevstuff.keybindspurger.mixin.IOptionsSubScreenAccessor"))
            apply = true;
        if(apply)
            Keybindspurger.LOGGER.info("Applying mixin: " + mixinClassName);
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
