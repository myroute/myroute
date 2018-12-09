package kr.co.myroute.sample.main.presenter;

import android.support.annotation.NonNull;

import kr.co.myroute.architecture.mvp.MvpBasePresenterModel;
import kr.co.myroute.sample.main.model.SampleModel;
import kr.co.myroute.sample.main.model.SamplePropDTO;
import kr.co.myroute.sample.main.model.interactor.SampleInteractor;
import kr.co.myroute.sample.main.view.SampleActivityMvpView;

public class SampleActivityPresenter extends MvpBasePresenterModel<SampleActivityMvpView, SampleModel> {
	private SampleInteractor sampleInteractor;

	public SampleActivityPresenter(@NonNull SampleInteractor sampleInteractor) {
		this.sampleInteractor = sampleInteractor;
	}

	@Override
	protected SampleModel createModel() {
		return SampleModel.builder().setSampleList(sampleInteractor.getSampleList()).build();
	}

	@Override
	public void bindView(@NonNull SampleActivityMvpView view) {
		super.bindView(view);

		view().setSampleList(model().getSampleList());
	}

	@Override
	public void unbindView() {
		super.unbindView();
	}

	@Override
	protected void updateView() {

	}

	public void onItemClick(int position) {
		SamplePropDTO samplePropDTO = model().getSampleList().get(position);
		view().moveToSampleItem(samplePropDTO.getIntent());
	}
}
