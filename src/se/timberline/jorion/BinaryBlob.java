package se.timberline.jorion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class BinaryBlob {

	private List<Integer> content;
	private int currentOffset = 0;

	public BinaryBlob(List<Integer> content) {
		this.content = content;
	}

	public static BinaryBlob createFromFile(File file)
			throws IOException {
		FileInputStream fis = new FileInputStream(file);
		List<Integer> content = new LinkedList<Integer>();
		int nextByte = -1;
		do {
			nextByte = fis.read();
			if (nextByte != -1) {
				content.add(nextByte);
			}
		} while (nextByte != -1);
		
		return new BinaryBlob(content);
	}

	public Integer readUInt8() {
		return content.get(currentOffset++);
	}

	public Integer readUInt16() {
		int firstInt = readUInt8();
		int secondInt = readUInt8();
		if (firstInt == -1 || secondInt == -1) {
			return -1;
		}
		return (secondInt << 8) | firstInt;
	}

	public Integer readUInt32() {
		int firstInt = readUInt16();
		int secondInt = readUInt16();
		if (firstInt == -1 || secondInt == -1) {
			return -1;
		}
		return (secondInt << 16) | firstInt;
	}

	public void seek(int offset) {
		currentOffset = offset;
	}

	public String readString(int length) throws IOException {
		StringBuilder output = new StringBuilder();
		int nextByte = 1;
		for (int i = 0; i < length; i++) {
			nextByte = readUInt8();
			output.append((char) nextByte);
		}
		return output.toString();
	}

	public List<Integer> toList() {
		return content;
	}

	public BinaryBlob subBlob(Integer offset, int size) {
		return new BinaryBlob(content.subList(offset, offset+size));
	}

	public BinaryBlob duplicate() {
		BinaryBlob binaryBlob = new BinaryBlob(content);
		binaryBlob.seek(currentOffset);
		return binaryBlob;
	}
}
