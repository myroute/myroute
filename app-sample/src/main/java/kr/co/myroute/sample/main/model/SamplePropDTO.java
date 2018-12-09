package kr.co.myroute.sample.main.model;

import android.content.Intent;

import com.google.auto.value.AutoValue;

/**
 * Created on 2018. 10. 26.
 */
@AutoValue
public abstract class SamplePropDTO {
	public abstract String getTitle();
	public abstract Intent getIntent();

	public static SamplePropDTO create(String newTitle, Intent newIntent) {
		return new AutoValue_SamplePropDTO(newTitle, newIntent);
	}

}
