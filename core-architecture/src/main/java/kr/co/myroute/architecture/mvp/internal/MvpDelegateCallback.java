package kr.co.myroute.architecture.mvp.internal;

import android.support.annotation.NonNull;

import kr.co.myroute.architecture.mvp.MvpPresenter;
import kr.co.myroute.architecture.mvp.MvpView;

/**
 * The MvpDelegate callback that will be called from  {@link
 * MvpDelegate}. This interface must be implemented by all
 * Fragment and Activity or android.view.View that you want to support mvp.
 *
 * @param <V> The type of {@link MvpView}
 * @param <P> The type of {@link MvpPresenter}
 */
public interface MvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

	/**
	 * Creates the presenter instance
	 *
	 * @return the created presenter instance
	 */
	@NonNull
	P createPresenter();

	/**
	 * Get the presenter. If null is returned, then a internally a new presenter instance gets
	 * created
	 * by calling {@link #createPresenter()}
	 *
	 * @return the presenter instance. can be null.
	 */
	P getPresenter();

	/**
	 * Sets the presenter instance
	 *
	 * @param presenter The presenter instance
	 */
	void setPresenter(P presenter);

	/**
	 * Get the MvpView for the presenter
	 *
	 * @return The view associated with the presenter
	 */
	V getMvpView();
}