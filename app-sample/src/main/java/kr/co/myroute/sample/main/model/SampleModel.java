package kr.co.myroute.sample.main.model;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class SampleModel {
	public abstract List<SamplePropDTO> getSampleList();

	public static Builder builder() {
		return new AutoValue_SampleModel.Builder();
	}

	@AutoValue.Builder public abstract static class Builder {
		public abstract Builder setSampleList(List<SamplePropDTO> newSampleList);

		public abstract SampleModel build();
	}
}
