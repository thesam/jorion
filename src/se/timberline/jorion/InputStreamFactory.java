package se.timberline.jorion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamFactory {
	private File file;

	public InputStreamFactory(File file) {
		this.file = file;
	}

	public InputStream createInstance() throws IOException {
		return new FileInputStream(file);
	}
}
