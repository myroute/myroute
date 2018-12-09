package kr.co.myroute.sample.main.model.interactor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import kr.co.myroute.sample.architecture.layout.GlobalLayoutSampleActivity;
import kr.co.myroute.sample.architecture.lifecycle.LifeCycleSampleActivity;
import kr.co.myroute.sample.architecture.rx.RxSampleActivity;
import kr.co.myroute.sample.design.coordinatelayout.CoordinatorLayoutSampleActivity;
import kr.co.myroute.sample.design.drawerlayout.DrawerLayoutSampleActivity;
import kr.co.myroute.sample.design.snackbar.SnackbarSampleActivity;
import kr.co.myroute.sample.design.toolbar.ToolbarSampleActivity;
import kr.co.myroute.sample.library.autovalue.AutoValueSampleActivity;
import kr.co.myroute.sample.main.model.SamplePropDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018. 10. 26.
 */
public class LocalSampleInteractor implements SampleInteractor {
	@NonNull
	private Context context;
	@NonNull
	private List<SamplePropDTO> sampleList = new ArrayList<>();

	public LocalSampleInteractor(Context context) {
		this.context = context;

		initSampleList();
	}

	private void initSampleList() {
		sampleList.clear();

		// Architecture
		sampleList.add(SamplePropDTO.create("Rx", new Intent(context, RxSampleActivity.class)));
		sampleList.add(SamplePropDTO.create("Lifecycle", new Intent(context, LifeCycleSampleActivity.class)));
		sampleList.add(SamplePropDTO.create("GlobalLayout", new Intent(context, GlobalLayoutSampleActivity.class)));

		// Design
		sampleList.add(SamplePropDTO.create("Toobar", new Intent(context, ToolbarSampleActivity.class)));
		sampleList.add(SamplePropDTO.create("DrawerLayout", new Intent(context, DrawerLayoutSampleActivity.class)));
		sampleList.add(SamplePropDTO.create("Snackbar", new Intent(context, SnackbarSampleActivity.class)));
		sampleList.add(SamplePropDTO.create("CoordinatorLayout", new Intent(context, CoordinatorLayoutSampleActivity.class)));

		// Library
		sampleList.add(SamplePropDTO.create("AutoValue", new Intent(context, AutoValueSampleActivity.class)));
	}

	@NonNull
	@Override
	public List<SamplePropDTO> getSampleList() {
		return sampleList;
	}
}
