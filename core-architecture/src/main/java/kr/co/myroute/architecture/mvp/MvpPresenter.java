package kr.co.myroute.architecture.mvp;

import android.support.annotation.UiThread;

public interface MvpPresenter<V> {
	/**
	 * Set or bind the view to this presenter
	 */
	@UiThread
	void bindView(V view);

	/**
	 * Will be called if the view has been destroyed. Typically this method will be invoked from
	 * <code>Activity.onDestroy()</code> or <code>Fragment.onDestroyView()</code>
	 */
	@UiThread
	void unbindView();

	boolean isBound();
}