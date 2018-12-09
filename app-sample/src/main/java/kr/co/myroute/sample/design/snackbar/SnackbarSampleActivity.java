package kr.co.myroute.sample.design.snackbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

import kr.co.myroute.sample.R;

/**
 * Created on 2018. 10. 26.
 */
public class SnackbarSampleActivity extends AppCompatActivity {
	@BindView(R.id.snackbar_layout)
	CoordinatorLayout snackbarLayout;
	@BindView(R.id.snackbar_button)
	Button snackbarButton;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_snackbar);
		ButterKnife.bind(this);

		snackbarButton.setOnClickListener((View v) -> {
			Snackbar.make(snackbarLayout, "Snackbar Message!!", Snackbar.LENGTH_LONG)
				.setAction("RETRY", (View v2) -> {
				})
				.show();

		});
	}
}