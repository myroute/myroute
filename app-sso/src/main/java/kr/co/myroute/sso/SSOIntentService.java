package kr.co.myroute.sso;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created on 2018. 11. 1.
 */
public class SSOIntentService extends IntentService {
	/**
	 * Creates an IntentService.  Invoked by your subclass's constructor.
	 *
	 * @param name Used to name the worker thread, important only for debugging.
	 */
	public SSOIntentService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {

	}
}
