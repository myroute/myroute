package kr.co.myroute.sample.design.drawerlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
	@BindView(R.id.navigation_view)
	NavigationView mNavigationView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_drawer_layout);
		ButterKnife.bind(this);

		setSupportActionBar(mToolbar);

//		ActionBar actionBar = getSupportActionBar();

		//		if (actionBar != null) {
		//			actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_add);
		//			actionBar.setDisplayHomeAsUpEnabled(true);
		//		}

		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
		mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();

		mNavigationView.setNavigationItemSelectedListener((MenuItem menuItem) -> {
			menuItem.setChecked(true);
			mDrawerLayout.closeDrawers();
			return true;
		});
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			mDrawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//		MenuInflater menuInflater = getMenuInflater();
	//		menuInflater.inflate(R.menu.menu_toolbar, menu);
	//
	//		return true;
	//	}

	//	@Override
	//	public boolean onOptionsItemSelected(MenuItem item) {
	//		// Handle action bar item clicks here. The action bar will
	//		// automatically handle clicks on the Home/Up button, so long
	//		// as you specify a parent activity in AndroidManifest.xml.
	//		int id = item.getItemId();
	//
	//		switch (id) {
	//			case android.R.id.home:
	//				mDrawerLayout.openDrawer(GravityCompat.START);
	//				return true;
	//			case R.id.action_settings:
	//				return true;
	//		}
	//
	//		return super.onOptionsItemSelected(item);
	//	}
}