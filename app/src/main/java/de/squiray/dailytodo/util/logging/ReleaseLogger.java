package de.squiray.dailytodo.util.logging;

import android.content.Context;
import android.util.Log;

import timber.log.Timber;

public class ReleaseLogger extends Timber.Tree {

	private static final int LOG_LEVEL_WHEN_DEBUG_IS_DISABLED = Log.INFO;

	private final char[] PRIORITY_NAMES = { //
			'?', //
			'?', //
			'V', //
			'D', //
			'I', //
			'W', //
			'E', //
			'A' //
	};

	private static volatile boolean debugMode;

	private final LogRotator log;

	public ReleaseLogger(Context context) {
		//debugMode = new SharedPreferencesHandler(context).debugMode();
		this.log = new LogRotator(context);
	}

	public static void updateDebugMode(boolean debugMode) {
		ReleaseLogger.debugMode = debugMode;
		Timber.tag("Logging").i(debugMode ? "Debug mode enabled" : "Debug mode disabled");
	}

	@Override
	protected boolean isLoggable(String tag, int priority) {
		return debugMode || (priority >= LOG_LEVEL_WHEN_DEBUG_IS_DISABLED);
	}

	@Override
	protected void log(int priority, String tag, String message, Throwable throwable) {
		if (tag == null) {
			tag = "App";
		}
		StringBuilder line = new StringBuilder();
		line //
				.append(PRIORITY_NAMES[validPriority(priority)]).append('\t') //
				.append(FormattedTime.now()).append('\t') //
				.append(tag).append('\t') //
				.append(message);
		if (throwable != null) {
			line //
					.append("\nErrorCode: ") //
					.append(GeneratedErrorCode.of(throwable));
		}
		log.log(line.toString());
	}

	private int validPriority(int priority) {
		if (priority >= 1 && priority <= 7) {
			return priority;
		}
		return 0;
	}
}
