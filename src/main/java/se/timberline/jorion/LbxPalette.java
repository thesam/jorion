package se.timberline.jorion;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class LbxPalette {
	private final Map<Integer, Color> palette;

	public LbxPalette(Map<Integer, Color> palette) {
		this.palette = palette;
	}

	public static LbxPalette createFrom(BinaryBlob blob) {
		Map<Integer, Color> palette = new HashMap<Integer, Color>();

		for (int index = 0; index < 0x100; index++) {
			float color1 = blob.readUInt8();
			float color2 = blob.readUInt8();
			float color3 = blob.readUInt8();
			color1 /= 0x3F;
			color2 /= 0x3F;
			color3 /= 0x3F;
			System.err.printf("%x: %d %d %d\n",index,(int)(color1*255),(int)(color2*255),(int)(color3*255));
			palette.put(index, new Color(color1, color2, color3));
		}
		// Each color value is only 6 bits!
//		boolean odd = false;
//		do {
//			byte0 = byte2;
//			byte1 = blob.readUInt8();
//			byte2 = blob.readUInt8();
//			if (byte0 != -1 && byte1 != -1 && byte2 != -1) {
//				float color1 = 0;
//				float color2 = 0;
//				float color3 = 0;
//				if (!odd) {
//					color1 = byte0 >> 2;
//					color2 = ((byte0 & 0x03) << 4) | ((byte1 & 0xF0) >> 4);
//					color3 = ((byte1 & 0x0F) << 2) | ((byte2 & 0x60) >> 6);
//				} else {
//					color1 = byte0 & 0x3f;
//					color2 = ((byte1 & 0xfc) >> 2);
//					color3 = ((byte1 & 0x03) << 4) | ((byte2 & 0xF0) >> 4);
//				}
//				color1 /= 0x3F;
//				color2 /= 0x3F;
//				color3 /= 0x3F;
//				System.err.printf("%x: %d %d %d\n",index,(int)(color1*255),(int)(color2*255),(int)(color3*255));
//				palette.put(index, new Color(color1, color2, color3));
//				index++;
//				odd = !odd;
//			}
//			// nextByte = color3;
//
//		} while (byte2 != -1);

//		 palette.put(0x20, new Color(32, 48, 81));
//		 palette.put(0x28, new Color(36, 56, 101));
//		 palette.put(0x30, new Color(36, 60, 125));
//		 palette.put(0x31, new Color(209, 101, 28));
//		 palette.put(0x32, new Color(211, 97, 28));
//		 palette.put(0x33, new Color(203, 93, 28));
//		 palette.put(0x35, new Color(190, 89, 24));
//		 palette.put(0x38, new Color(174, 81, 24));
//		 palette.put(0x3D, new Color(138, 60, 16));
//		 palette.put(0x3E, new Color(125, 56, 16));
//		 palette.put(0x44, new Color(85, 36, 8));
//		 palette.put(0xBF, new Color(97, 73, 0));
//		 palette.put(0xC0, new Color(150, 113, 0));
//		 palette.put(0xC1, new Color(203, 150, 0));
//		 palette.put(0xED, new Color(170, 121, 77));

		return new LbxPalette(palette);
	}

	public Color getColor(int index) {
		Color color = null;
		if (palette.containsKey(index)) {
			color = palette.get(index);
		} else {
			color = Color.GREEN;
		}
		return color;
	}
}
