package kr.co.myroute.sample.main.util;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created on 2018. 11. 19.
 */
public class CompatUtils {
	private CompatUtils() {
		throw new IllegalAccessError("CompatUtils class");
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static void setBackgroundDrawable(View view, Drawable background) {
		if (VersionUtils.hasJellyBean()) {
			view.setBackground(background);
		} else {
			view.setBackgroundDrawable(background);
		}
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
		if (VersionUtils.hasJellyBean()) {
			view.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
		} else {
			view.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
		}
	}
}
