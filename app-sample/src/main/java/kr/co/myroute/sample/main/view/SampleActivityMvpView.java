package kr.co.myroute.sample.main.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import kr.co.myroute.architecture.mvp.MvpView;
import kr.co.myroute.sample.main.model.SamplePropDTO;

import java.util.List;

/**
 * Created by Coupang on 2018. 10. 25.
 */
public interface SampleActivityMvpView extends MvpView, View.OnClickListener {
	void setSampleList(@NonNull List<SamplePropDTO> sampleList);

	void moveToSampleItem(@NonNull Intent intent);
}
