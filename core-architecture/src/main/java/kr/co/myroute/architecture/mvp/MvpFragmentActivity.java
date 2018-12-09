package kr.co.myroute.architecture.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import kr.co.myroute.architecture.mvp.internal.MvpDelegate;
import kr.co.myroute.architecture.mvp.internal.MvpDelegateCallback;
import kr.co.myroute.architecture.mvp.internal.MvpDelegateImpl;
import timber.log.Timber;

/**
 * An Activity that uses a {@link MvpPresenter} to implement a Model-View-Presenter
 * architecture.
 *
 */
public abstract class MvpFragmentActivity<V extends MvpView, P extends MvpPresenter<V>> extends FragmentActivity
	implements MvpDelegateCallback<V, P>, MvpView {
	private static final String TAG = MvpFragmentActivity.class.getSimpleName();

	protected MvpDelegate mvpDelegate;
	protected P presenter;

	/**
	 * Creates a new presenter instance. This method will be called from
	 * {@link #onCreate(Bundle)}
	 *
	 * @return The {@link MvpPresenter} for this view
	 */
	@NonNull
	public abstract P createPresenter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onPreAttached();
		getMvpDelegate().onCreate();
		getMvpDelegate().onAttached();
	}

	/**
	 * Allow sub class to do some initialization before attaching to presenter
	 * This is the first place to run user specific logic for MvpFragmentActivity,
	 * which is called right after super.onCreate(Bundle)
	 */
	protected void onPreAttached() {
		// hooking method
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		getMvpDelegate().onDestroy();
	}

	/**
	 * Get the mvp delegate. This is internally used for creating presenter, attaching and detaching
	 * view from presenter.
	 *
	 * <p><b>Please note that only one instance of mvp delegate should be used per Activity
	 * instance</b>.
	 * </p>
	 *
	 * <p>
	 * Only override this method if you really know what you are doing.
	 * </p>
	 *
	 * @return {@link MvpDelegate}
	 */
	@NonNull
	protected MvpDelegate getMvpDelegate() {
		if (mvpDelegate == null) {
			mvpDelegate = new MvpDelegateImpl(this);
		}

		return mvpDelegate;
	}

	@Override
	public P getPresenter() {
		if (presenter != null && !presenter.isBound()) {
			Timber.e(TAG, "Call order issue. Your presenter is called but not bound to the view yet");
		}
		return presenter;
	}

	@Override
	public void setPresenter(@NonNull P presenter) {
		this.presenter = presenter;
	}

	@NonNull
	@Override
	public V getMvpView() {
		return (V) this;
	}
}