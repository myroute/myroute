package kr.co.myroute.architecture.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import kr.co.myroute.architecture.mvp.internal.NoOp;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A {@link MvpPresenter} implementation that implements the <a href="https://en.wikipedia.org/wiki/Null_Object_pattern">null
 * object pattern</a> for the attached mvp view. So whenever the view gets detached from this
 * presenter by calling{@link #unbindView()}, a new "null object" view gets dynamically
 * instantiated by using reflections and set as the current
 * view (instead of null). The new "null object" view simply does nothing. This avoids
 * NullPointerExceptions and checks like {@code if (getView() != null)}
 *
 * @param <V> The type of the {@link MvpView}
 */
public abstract class MvpBasePresenter<V> implements MvpPresenter<V> {
	@Nullable
	private WeakReference<V> view;
	@Nullable
	private V nullView;
	private boolean isBound;

	@Override
	public void bindView(@NonNull V view) {
		this.view = new WeakReference<>(view);
		isBound = true;
	}

	protected abstract void updateView();

	@Override
	public void unbindView() {
		if (view != null) {
			view.clear();
			view = null;
		}
		isBound = false;
	}

	@Override
	public boolean isBound() {
		return isBound;
	}

	/**
	 * Scans the interface inheritance hierarchy and checks if on the root is MvpView.class
	 *
	 * @param klass The leaf interface where to begin to scan
	 * @return true if subtype of MvpView, otherwise false
	 */
	private boolean isSubTypeOfMvpView(@Nullable Class<?> klass) {
		if (klass == null) {
			return false;
		}
		if (klass.equals(MvpView.class)) {
			return true;
		}
		Class[] superInterfaces = klass.getInterfaces();
		for (int i = 0; i < superInterfaces.length; i++) {
			if (isSubTypeOfMvpView(superInterfaces[0])) {
				return true;
			}
		}
		return false;
	}

	@UiThread
	@NonNull
	protected V view() {
		if (view != null) {
			V realView = view.get();
			if (realView != null) {
				return realView;
			}
		}

		return getNullView();
	}

	@NonNull
	private V getNullView() {
		if (nullView == null) {
			try {
				// Scan the inheritance hierarchy until we reached BasePresenter
				Class<V> viewClass = null;
				Class<?> currentClass = getClass();

				while (viewClass == null) {
					Type genericSuperType = currentClass.getGenericSuperclass();
					while (!(genericSuperType instanceof ParameterizedType)) {
						// Scan inheritance tree until we find ParameterizedType which is probably a MvpSubclass
						currentClass = currentClass.getSuperclass();
						genericSuperType = currentClass.getGenericSuperclass();
					}

					Type[] types = ((ParameterizedType) genericSuperType).getActualTypeArguments();
					for (Type type : types) {
						Class<?> genericType = (Class<?>) type;
						if (genericType.isInterface() && isSubTypeOfMvpView(genericType)) {
							//noinspection unchecked
							viewClass = (Class<V>) genericType;
							break;
						}
					}

					// Continue with next class in inheritance hierachy (see genericSuperType assignment at start of while loop)
					currentClass = currentClass.getSuperclass();
				}
				nullView = NoOp.of(viewClass);
			} catch (Throwable t) {    //NOSONAR Both exceptions and errors have to be caught.
				throw new IllegalArgumentException(
					"The generic type <V extends MvpView> must be the first generic type argument of class "
						+ getClass().getSimpleName()
						+ " (per convention). Otherwise we can't determine which type of View this"
						+ " Presenter coordinates.", t);
			}
		}
		return nullView;
	}
}
