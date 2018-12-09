package kr.co.myroute.architecture.mvp;

import android.support.annotation.NonNull;

public abstract class MvpBasePresenterModel<V, M> extends MvpBasePresenter<V> {
	private M model;

	public void setModel(M model) {
		this.model = model;
	}

	@NonNull
	public M model() {
		if (model != null) {
			return model;
		} else {
			model = createModel();
			return model;
		}
	}

	protected abstract M createModel();
}
