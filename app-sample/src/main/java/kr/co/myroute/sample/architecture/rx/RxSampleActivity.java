package kr.co.myroute.sample.architecture.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import kr.co.myroute.sample.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2018. 11. 22.
 */
public class RxSampleActivity extends AppCompatActivity {
	@BindView(R.id.rx_layout)
	CoordinatorLayout mRxLayout;
	@BindView(R.id.rx_button1)
	Button mSample1Button;
	@BindView(R.id.rx_button2)
	Button mSample2Button;
	@BindView(R.id.rx_button3)
	Button mSample3Button;
	@BindView(R.id.rx_button4)
	Button mSample4Button;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_rx);
		ButterKnife.bind(this);
	}

	@OnClick({ R.id.rx_button1 })
	public void onSample1ButtonClick(View view) {
		Observable<String> simpleObservable = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				subscriber.onNext("Hello RxAndroid!!");
				subscriber.onCompleted();
			}
		});

		simpleObservable.subscribe(new Subscriber<String>() {
			@Override
			public void onCompleted() {
				Log.e("nachaos", "onCompleted!");
			}

			@Override
			public void onError(Throwable e) {
				Log.e("nachaos", "onError!");
			}

			@Override
			public void onNext(String string) {
				Snackbar.make(mRxLayout, string, Snackbar.LENGTH_LONG)
					.setAction("RETRY", (View v2) -> {
					})
					.show();
			}
		});
	}

	@OnClick({ R.id.rx_button2 })
	public void onSample2ButtonClick(View view) {
		Observable<Integer> observable = Observable.create(subscriber -> {
			for (Integer i : new Integer[] { 1, 2, 3, 4, 5 }) {
				Log.e("nachaos", Thread.currentThread().getName() + " : onNext " + i);
				subscriber.onNext(i);
			}
		});

//		observable.subscribe(new Subscriber<Integer>() {
//			@Override
//			public void onCompleted() {
//
//			}
//
//			@Override
//			public void onError(Throwable e) {
//				Log.e("nachaos", "onError:" + Thread.currentThread().getName() + ", " + e.getMessage());
//			}
//
//			@Override
//			public void onNext(Integer integer) {
//				Log.e("nachaos", "onNext:" + integer);
//			}
//		});

		observable
			.subscribeOn(Schedulers.computation())
			.observeOn(Schedulers.newThread())
			.concatMap(integer -> {
				Integer newinteger = integer * 10;
				Log.e("nachaos", Thread.currentThread().getName() + " : concatMap value :" + newinteger);
				return Observable.just(newinteger);
			})
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
			integer -> Log.e("nachaos", "value: " + integer),
			e -> Log.e("nachaos", Thread.currentThread().getName() + " : onErrorHandler")
		);
	}

	@OnClick({ R.id.rx_button3 })
	public void onSample3ButtonClick(View view) {

	}

	@OnClick({ R.id.rx_button4 })
	public void onSample4ButtonClick(View view) {

	}
}
