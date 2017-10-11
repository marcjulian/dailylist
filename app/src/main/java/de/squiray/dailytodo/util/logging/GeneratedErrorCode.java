package de.squiray.dailytodo.util.logging;

class GeneratedErrorCode {

	private static final int A_PRIME = Integer.MAX_VALUE;

	public static String of(Throwable e) {
		return format(originCode(rootCause(e))) + ':' + format(traceCode(e));
	}

	private static Throwable rootCause(Throwable e) {
		if (e.getCause() == null) {
			return e;
		} else {
			return e.getCause();
		}
	}

	private static String format(int value) {
		value = (value & 0xfffff) ^ (value >>> 20);
		value = value | 0x100000;
		return Integer.toString(value, 32).substring(1).toUpperCase();
	}

	private static int traceCode(Throwable e) {
		int result = 0xa6989b1a;
		while (e != null) {
			result = result * A_PRIME + originCode(e);
			for (StackTraceElement element : e.getStackTrace()) {
				result = result * A_PRIME + element.getClassName().hashCode();
				result = result * A_PRIME + element.getMethodName().hashCode();
			}
			e = e.getCause();
		}
		return result;
	}

	private static int originCode(Throwable e) {
		return originCode(e, e.getStackTrace());
	}

	private static int originCode(Throwable e, StackTraceElement[] stack) {
		int result = 0x6c528c4a;
		result = result * A_PRIME + e.getClass().getName().hashCode();
		if (stack.length > 0) {
			result = result * A_PRIME + stack[0].getClassName().hashCode();
			result = result * A_PRIME + stack[0].getMethodName().hashCode();
		}
		return result;
	}

}
