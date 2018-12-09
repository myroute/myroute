package kr.co.myroute.sso;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created on 2018. 11. 1.
 */
public class SSOService extends Service {
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		Log.e("nachaos", "SSOService");
		return null;
	}
}
