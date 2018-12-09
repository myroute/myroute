package kr.co.myroute.sample.main.util;

import android.os.Build;

/**
 * Created on 2018. 11. 19.
 */
public class VersionUtils {
	public static boolean hasJellyBean() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
	}

	public static boolean hasJellyBeanMR1() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
	}

	public static boolean hasJellyBeanMR2() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
	}

	public static boolean hasKitKatWATCH() { return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH; }

	public static boolean hasKitKat() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
	}

	public static boolean hasLollipop() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}

	public static boolean hasLollipopMR() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
	}

	public static boolean hasMarshmallow() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
	}

	public static boolean hasNougat() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
	}

	public static boolean hasNougatMR() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
	}

	public static boolean hasOreo() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
	}
}
