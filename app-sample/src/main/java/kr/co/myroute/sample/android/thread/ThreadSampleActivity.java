package kr.co.myroute.sample.android.thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

import kr.co.myroute.sample.R;

/**
 * Created on 2018. 11. 14.
 */
public class ThreadSampleActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_coordinator_layout);
		ButterKnife.bind(this);
	}
}