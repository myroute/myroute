package kr.co.myroute.architecture.mvp.internal;

import android.support.annotation.NonNull;

import kr.co.myroute.architecture.mvp.MvpPresenter;
import kr.co.myroute.architecture.mvp.MvpView;

public class MvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
	implements MvpDelegate {

	protected MvpDelegateCallback<V, P> delegateCallback;
	protected MvpInternalDelegate<V, P> internalDelegate;

	public MvpDelegateImpl(@NonNull MvpDelegateCallback<V, P> delegateCallback) {
		this.delegateCallback = delegateCallback;
	}

	protected MvpInternalDelegate<V, P> getInternalDelegate() {
		if (internalDelegate == null) {
			internalDelegate = new MvpInternalDelegate<>(delegateCallback);
		}
		return internalDelegate;
	}

	@Override
	public void onCreate() {
		getInternalDelegate().createPresenter();
	}

	@Override
	public void onAttached() {
		getInternalDelegate().attachView();
	}

	@Override
	public void onDestroy() {
		getInternalDelegate().detachView();
	}
}