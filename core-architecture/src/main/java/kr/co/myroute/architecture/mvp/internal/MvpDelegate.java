package kr.co.myroute.architecture.mvp.internal;

public interface MvpDelegate {
	/**
	 * This method must be called from activity/fragment onCreate or view constructor.
	 * This method internally creates the presenter.
	 */
	void onCreate();

	/**
	 * This method must be called from Activity onCreate(), fragment onCreateView() or view onAttachToWindow().
	 * This method internally attaches the view to the presenter
	 */
	void onAttached();

	/**
	 * This method must be called from Activity/fragment onDestroy() or view onDetachedFromWindow().
	 * This method internally detaches the view from presenter
	 */
	void onDestroy();
}