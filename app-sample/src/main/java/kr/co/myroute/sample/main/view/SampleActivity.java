package kr.co.myroute.sample.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import kr.co.myroute.architecture.mvp.MvpActivity;
import kr.co.myroute.sample.R;
import kr.co.myroute.sample.main.model.SamplePropDTO;
import kr.co.myroute.sample.main.model.interactor.LocalSampleInteractor;
import kr.co.myroute.sample.main.presenter.SampleActivityPresenter;

import java.util.List;

public class SampleActivity extends MvpActivity<SampleActivityMvpView, SampleActivityPresenter> implements SampleActivityMvpView {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.fab)
	FloatingActionButton fab;
	@BindView(R.id.sample_recycler_view)
	RecyclerView recyclerView;
	@BindView(R.id.bottom_sheet)
	LinearLayout bottomSheetLayout;

	@NonNull
	@Override
	public SampleActivityPresenter createPresenter() {
		return new SampleActivityPresenter(new LocalSampleInteractor(this));
	}

	@Override
	protected void onPreAttached() {
		super.onPreAttached();

		setContentView(R.layout.activity_sample);
		ButterKnife.bind(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setSupportActionBar(toolbar);
		fab.setOnClickListener((View view) -> {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();
			}
		);

		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		recyclerView.addItemDecoration(new DividerItemDecoration(this, new LinearLayoutManager(this).getOrientation()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setSampleList(@NonNull List<SamplePropDTO> sampleList) {
		SampleViewAdapter viewAdapter = new SampleViewAdapter(sampleList, this);
		recyclerView.setAdapter(viewAdapter);
	}

	@Override
	public void moveToSampleItem(@NonNull Intent intent) {
		startActivity(intent);
	}

	@Override
	public void onClick(View view) {
		int position = (int) view.getTag();
		getPresenter().onItemClick(position);
	}

	private class SampleViewAdapter extends RecyclerView.Adapter<SampleViewHolder> {
		private List<SamplePropDTO> itemList;
		private View.OnClickListener clickListener;

		public SampleViewAdapter(@NonNull List<SamplePropDTO> itemList, @NonNull View.OnClickListener clickListener) {
			this.itemList = itemList;
			this.clickListener = clickListener;
		}

		@NonNull
		@Override
		public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false);
			return new SampleViewHolder(view, clickListener);
		}

		@Override
		public void onBindViewHolder(@NonNull SampleViewHolder holder, int position) {
			holder.bind(itemList.get(position), position);
		}

		@Override
		public int getItemCount() {
			return itemList.size();
		}
	}

	class SampleViewHolder extends RecyclerView.ViewHolder {
		private View.OnClickListener clickListener;

		@BindView(R.id.sample_layout)
		RelativeLayout sampleLayout;
		@BindView(R.id.sample_title)
		TextView sampleTitle;

		public SampleViewHolder(final View itemView, View.OnClickListener clickListener) {
			super(itemView);
			this.clickListener = clickListener;

			ButterKnife.bind(this, itemView);
		}

		public void bind(@NonNull final SamplePropDTO samplePropDTO, int position) {
			sampleTitle.setText(samplePropDTO.getTitle());

			if (clickListener != null) {
				sampleLayout.setTag(position);
				sampleLayout.setOnClickListener(clickListener);
			}
		}
	}
}
