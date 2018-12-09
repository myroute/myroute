package kr.co.myroute.sample.architecture.sso;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

import kr.co.myroute.sample.R;

/**
 * Created on 2018. 11. 1.
 */
public class SSOActivity extends AppCompatActivity {
	@BindView(R.id.snackbar_button)
	Button ssoButton;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sso);
		ButterKnife.bind(this);

		ssoButton.setOnClickListener((View v) -> {
			Intent intent = new Intent();
//			intent.setAction(Intent.ACTION_MAIN);
//			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setComponent(new ComponentName("kr.co.myroute.sso", "kr.co.myroute.sso.SSOService"));

			SSOActivity.this.startService(intent);
		});
	}
}
