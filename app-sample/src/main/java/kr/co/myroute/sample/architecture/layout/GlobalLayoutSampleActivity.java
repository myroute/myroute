package kr.co.myroute.sample.architecture.layout;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

import kr.co.myroute.sample.R;
import kr.co.myroute.sample.main.util.CompatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018. 11. 19.
 */
public class GlobalLayoutSampleActivity extends AppCompatActivity {

	@BindView(R.id.global_layout) CoordinatorLayout globalLayout;
	@BindView(R.id.lv_listview) ListView listView;

	List<String> menuList = new ArrayList<>();
	ArrayAdapter adapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_global_layout);
		ButterKnife.bind(this);

		//		globalLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
		////			@Override
		////			public void onGlobalLayout() {
		////				Log.println(Log.ERROR, "nachaos", "nachaos onGlobalLayout");
		////				CompatUtils.removeOnGlobalLayoutListener(globalLayout, this);
		////			}
		////		});

		for (int i = 1; i < 20; i++) {
			menuList.add("TEST" + i);
		}

		adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, menuList);
		listView.setAdapter(adapter);

		listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				Log.e("nachaos", "nachaos listView");
				CompatUtils.removeOnGlobalLayoutListener(listView, this);
			}
		});

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				addMoreMenu(3);
			}
		}, 3000);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				addMoreMenu(5);
			}
		}, 5000);
	}

	private void addMoreMenu(int index) {
		menuList.add(index, "NEW ITEM");
		adapter.notifyDataSetChanged();
	}
}
