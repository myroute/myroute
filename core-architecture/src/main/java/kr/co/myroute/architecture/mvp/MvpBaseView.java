package kr.co.myroute.architecture.mvp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import kr.co.myroute.architecture.mvp.internal.MvpDelegate;
import kr.co.myroute.architecture.mvp.internal.MvpDelegateCallback;
import kr.co.myroute.architecture.mvp.internal.MvpDelegateImpl;
import timber.log.Timber;

/**
 * A View that uses a {@link MvpPresenter} to implement a Model-View-Presenter
 * architecture.
 *
 */
public abstract class MvpBaseView<V extends MvpView, P extends MvpPresenter<V>> extends View implements MvpDelegateCallback<V, P> {
	private static final String TAG = MvpBaseView.class.getSimpleName();

	protected MvpDelegate mvpDelegate;
	protected P presenter;

	public MvpBaseView(Context context) {
		super(context);
		getMvpDelegate().onCreate();
	}

	public MvpBaseView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		getMvpDelegate().onCreate();
	}

	public MvpBaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		getMvpDelegate().onCreate();
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public MvpBaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		getMvpDelegate().onCreate();
	}

	/**
	 * Creates a new presenter instance. This method will be called from
	 * {@link #onAttachedToWindow()}
	 *
	 * @return The {@link MvpPresenter} for this view
	 */
	@NonNull
	public abstract P createPresenter();

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		getMvpDelegate().onAttached();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		getMvpDelegate().onDestroy();
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

	/**
	 * Get the mvp delegate. This is internally used for creating presenter, attaching and detaching
	 * view from presenter.
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
}