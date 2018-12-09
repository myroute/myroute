package kr.co.myroute.architecture.lifecycle;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import kr.co.myroute.architecture.mvp.MvpFragmentActivity;
import kr.co.myroute.architecture.mvp.MvpPresenter;
import kr.co.myroute.architecture.mvp.MvpView;

public abstract class LifeCycleMvpFragmentActivity<V extends MvpView, P extends MvpPresenter<V> & LifecycleObserver> extends MvpFragmentActivity<V, P>
	implements LifecycleOwner {
	@NonNull
	@Override
	public P createPresenter() {
		P presenter = createLifeCyclePresenter();
		getLifecycle().addObserver(presenter);

		return presenter;
	}

	@NonNull
	public abstract P createLifeCyclePresenter();
}
