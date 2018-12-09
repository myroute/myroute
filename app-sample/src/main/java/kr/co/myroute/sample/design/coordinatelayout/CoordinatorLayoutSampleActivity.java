package kr.co.myroute.sample.design.coordinatelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

import kr.co.myroute.sample.R;

/**
 * Created on 2018. 10. 26.
 */
public class CoordinatorLayoutSampleActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_coordinator_layout);
		ButterKnife.bind(this);
	}
}