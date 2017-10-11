package de.squiray.dailytodo.util.logging;

import timber.log.Timber;

public class CrashLogging implements Thread.UncaughtExceptionHandler {

	public static void setup() {
		Thread.setDefaultUncaughtExceptionHandler( //
				new CrashLogging(Thread.getDefaultUncaughtExceptionHandler()));
	}

	private final Thread.UncaughtExceptionHandler systemUncaughtExceptionHandler;

	private CrashLogging(Thread.UncaughtExceptionHandler systemUncaughtExceptionHandler) {
		this.systemUncaughtExceptionHandler = systemUncaughtExceptionHandler;
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		Timber.e(e, "App crashed");
		systemUncaughtExceptionHandler.uncaughtException(t, e);
	}
}
