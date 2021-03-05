package org.tiaa.spring.common.web.error.replay;

public class HTTPRequestTheadLocalHandler {
	public static final ThreadLocal<RequestWrapper> userThreadLocal = new ThreadLocal();

	public static void set(RequestWrapper user) {
		userThreadLocal.set(user);
	}

	public static void unset() {
		userThreadLocal.remove();
	}

	public static RequestWrapper get() {
		return userThreadLocal.get();
	}
}
