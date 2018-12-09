package kr.co.myroute.sample.design.drawerlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import butterknife.BindView;
import butterknife.ButterKnife;

import kr.co.myroute.sample.R;

/**
 * Created on 2018. 10. 26.
 */
public class DrawerLayoutSampleActivity extends AppCompatActivity {
	@BindView(R.id.drawer_layout)
	DrawerLayout mDrawerLayout;
	@BindView(R.id.toolbar)
	Toolbar mToolbar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_toolbar);
		ButterKnife.bind(this);

		setSupportActionBar(mToolbar);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
			mDrawerLayout, mToolbar, R.string.app_name, R.string.button_click);
		mDrawerLayout.addDrawerListener(toggle);
		toggle.syncState();
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			mDrawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_toolbar, menu);

		return true;
	}

	/*private Boolean getToggle(@NonNull View view) {
		Boolean toggle = view.getTag(R.id.TOGGLE_ID) != null ? (Boolean) view.getTag(R.id.TOGGLE_ID) : false;
		return toggle;
	}

	private void setToggle(@NonNull View view, Boolean value) {
		view.setTag(R.id.TOGGLE_ID, value);
	}

	@OnClick(R.id.toolbar_button1)
	void onToolbarButton1Click(View view) {
	}

	@OnClick(R.id.toolbar_button2)
	void onToolbarButton2Click(View view) {
		boolean toggle = getToggle(view);

		if (toggle) {
			setToggle(view, false);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		} else {
			setToggle(view, true);

			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_background);
		}
	}

	@OnClick(R.id.toolbar_button3)
	void onToolbarButton3Click(View view) {

	}

	@OnClick(R.id.toolbar_button4)
	void onToolbarButton4Click(View view) {

	}*/
}