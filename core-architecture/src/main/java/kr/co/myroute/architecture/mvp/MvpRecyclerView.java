package kr.co.myroute.architecture.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import kr.co.myroute.architecture.mvp.internal.MvpDelegate;
import kr.co.myroute.architecture.mvp.internal.MvpDelegateCallback;
import kr.co.myroute.architecture.mvp.internal.MvpDelegateImpl;
import timber.log.Timber;

public abstract class MvpRecyclerView<V extends MvpView, P extends MvpPresenter<V>> extends RecyclerView implements MvpDelegateCallback<V, P> {
	private static final String TAG = MvpRecyclerView.class.getSimpleName();

	protected MvpDelegate mvpDelegate;
	protected P presenter;

	public MvpRecyclerView(Context context) {
		super(context);
		getMvpDelegate().onCreate();
	}

	public MvpRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getMvpDelegate().onCreate();
	}

	public MvpRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
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
		if (isInEditMode()) {
			return;
		}
		getMvpDelegate().onAttached();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (isInEditMode()) {
			return;
		}
		getMvpDelegate().onDestroy();
	}

	@Override
	public P getPresenter() {
		if (presenter != null && !presenter.isBound()) {
			Timber.tag(TAG).e("Call order issue. Your presenter is called but not bound to the view yet");
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