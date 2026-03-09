package dev.zeddevstuff.keybindspurger.common;

import net.msrandom.multiplatform.annotations.Expect;

import java.util.List;


public class Utils
{
	@Expect
	public static List<String> getMods();

	@Expect
	public static void clearKeyMapping(Object keyMapping);
	@Expect
	public static void resetKeyMapping(Object keyMapping);
}
