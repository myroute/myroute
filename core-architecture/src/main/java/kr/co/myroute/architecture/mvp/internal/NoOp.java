/*
 *  Copyright 2015. Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package kr.co.myroute.architecture.mvp.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Proxy.newProxyInstance;
import static java.util.Collections.unmodifiableMap;

/**
 * Dynamically proxy to generate a new object instance for a given class by using reflections
 *
 * @author Jens Dirller
 * @since 1.2.0
 */
public final class NoOp {
	private static final Map<Class<?>, Object> DEFAULTS =
		unmodifiableMap(createMap());

	private static final InvocationHandler DEFAULT_VALUE = new DefaultValueInvocationHandler();

	private NoOp() {
		// no instances
	}

	private static Map<Class<?>, Object> createMap() {
		Map<Class<?>, Object> map = new HashMap<>();

		map.put(Boolean.TYPE, false);
		map.put(Byte.TYPE, (byte) 0);
		map.put(Character.TYPE, '\000');
		map.put(Double.TYPE, 0.0d);
		map.put(Float.TYPE, 0.0f);
		map.put(Integer.TYPE, 0);
		map.put(Long.TYPE, 0L);
		map.put(Short.TYPE, (short) 0);

		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> T of(Class<T> interfaceClass) {
		return (T) newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
			DEFAULT_VALUE);
	}

	private static class DefaultValueInvocationHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return defaultValue(method.getReturnType());
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T defaultValue(Class<T> type) {
		return (T) DEFAULTS.get(type);
	}
}