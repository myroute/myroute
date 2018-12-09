package kr.co.myroute.architecture.mvp.internal;

import kr.co.myroute.architecture.mvp.MvpPresenter;
import kr.co.myroute.architecture.mvp.MvpView;

/**
 * This is just the internal implementation for the delegate. Don't use it by your own.
 *
 * @param <V> The type of {@link MvpView}
 * @param <P> The type of {@link MvpPresenter}
 */
class MvpInternalDelegate<V extends MvpView, P extends MvpPresenter<V>> {

	private MvpDelegateCallback<V, P> delegateCallback;

	MvpInternalDelegate(MvpDelegateCallback<V, P> delegateCallback) {

		if (delegateCallback == null) {
			throw new NullPointerException("MvpDelegateCallback is null!");
		}

		this.delegateCallback = delegateCallback;
	}

	/**
	 * Called  to create the presenter (if no other one already exists)
	 */
	void createPresenter() {
		P presenter = delegateCallback.getPresenter();
		if (presenter == null) {
			presenter = delegateCallback.createPresenter();
		}
		delegateCallback.setPresenter(presenter);
	}

	/**
	 * Attaches the view to the presenter
	 */
	void attachView() {
		getPresenter().bindView(delegateCallback.getMvpView());
	}

	/**
	 * Called to detach the view from presenter
	 */
	void detachView() {
		getPresenter().unbindView();
	}

	private P getPresenter() {
		P presenter = delegateCallback.getPresenter();
		if (presenter == null) {
			throw new NullPointerException("Presenter returned from getPresenter() is null");
		}
		return presenter;
	}
}