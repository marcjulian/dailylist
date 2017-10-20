package de.squiray.dailylist.util.logging;

import timber.log.Timber;

public class DebugLogger extends Timber.DebugTree {

	@Override
	protected void log(int priority, String tag, String message, Throwable t) {
		if (t != null) {
			message = message + "\nErrorCode: " + GeneratedErrorCode.of(t);
		}
		super.log(priority, tag, message, t);
	}

}
