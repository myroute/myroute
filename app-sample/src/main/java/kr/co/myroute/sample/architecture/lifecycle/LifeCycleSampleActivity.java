package kr.co.myroute.sample.architecture.lifecycle;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import kr.co.myroute.sample.R;

/**
 * Created on 2018. 11. 22.
 */
public class LifeCycleSampleActivity extends AppCompatActivity {
	@BindView(R.id.lifecycle_layout)
	CoordinatorLayout mLifeCycleLayout;
	@BindView(R.id.lifecycle_button1)
	Button mSample1Button;
	@BindView(R.id.lifecycle_button2)
	Button mSample2Button;
	@BindView(R.id.lifecycle_button3)
	Button mSample3Button;
	@BindView(R.id.lifecycle_button4)
	Button mSample4Button;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_lifecycle);
		ButterKnife.bind(this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		Snackbar.make(mLifeCycleLayout, "onNewIntent Message!!", Snackbar.LENGTH_LONG)
			.show();
	}

	@OnClick({ R.id.lifecycle_button1 })
	public void onSample1ButtonClick(View view) {
		try {
			Intent intent = new Intent();
			intent.setData(Uri.parse("sample://pending"));
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			pendingIntent.send();
		} catch (PendingIntent.CanceledException e) {
			e.printStackTrace();
		}
	}


}
