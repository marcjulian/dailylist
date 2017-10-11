package de.squiray.dailytodo.util.logging;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.OutputStream;

class SizeMeasuringOutputStream extends OutputStream {

	private volatile long size = 0L;

	private final OutputStream delegate;

	public SizeMeasuringOutputStream(OutputStream delegate) {
		this.delegate = delegate;
	}

	public long size() {
		return size;
	}

	@Override
	public void write(int b) throws IOException {
		delegate.write(b);
		size++;
	}

	@Override
	public void write(@NonNull byte[] b) throws IOException {
		delegate.write(b);
		size += b.length;
	}

	@Override
	public void write(@NonNull byte[] b, int off, int len) throws IOException {
		delegate.write(b, off, len);
		size += len;
	}

	@Override
	public void flush() throws IOException {
		delegate.flush();
	}

	@Override
	public void close() throws IOException {
		delegate.close();
	}

}
