package se.timberline.jorion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BinaryFileReader {

	private InputStream stream;
	private InputStreamFactory streamFactory;

	public BinaryFileReader(InputStreamFactory streamFactory) {
		this.streamFactory = streamFactory;
	}

	public int readUInt8() throws IOException {
		if (stream == null) {
			resetStream();
		}
		return stream.read();
	}

	private void resetStream() throws IOException {
		stream = streamFactory.createInstance();
	}

	public int readUInt16() throws IOException {
		int firstInt = readUInt8();
		int secondInt = readUInt8();
		if (firstInt == -1 || secondInt == -1) {
			return -1;
		}
		return (secondInt << 8) | firstInt;
	}

	public int readUInt32() throws IOException {
		int firstInt = readUInt16();
		int secondInt = readUInt16();
		if (firstInt == -1 || secondInt == -1) {
			return -1;
		}
		return (secondInt << 16) | firstInt;
	}

	public static BinaryFileReader createFromFile(File file)
			throws FileNotFoundException {
		return new BinaryFileReader(new InputStreamFactory(file));
	}

	public void seek(int offset) throws IOException {
		resetStream();
		stream.skip(offset);
	}

	public String readString() throws IOException {
		StringBuilder output = new StringBuilder();
		int nextByte = 1;
		while (nextByte > 0) {
			nextByte = readUInt8();
			if (nextByte > 0) {
				output.append((char) nextByte);
			}
		}
		return output.toString();
	}
}
