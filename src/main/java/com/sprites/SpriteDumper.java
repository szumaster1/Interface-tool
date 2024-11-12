package com.sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.logging.LogFactory;

import properties.PropertyValues;

public class SpriteDumper {

	private static final Logger logger = LogFactory.createLogger(SpriteDumper.class.getName());

	public static void dump() {
		File directory = new File(PropertyValues.dump_path);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		int size = SpriteLoader.getNumSprites();
		for (int i = 0; i < size; i++) {
			SpriteArchive spra = SpriteLoader.getArchive(i);
			if (spra == null) continue;

			for (int frame = 0; frame < spra.size(); frame++) {
				File file = new File(PropertyValues.dump_path, i + "_" + frame + ".png");
				BufferedImage image = spra.getSprite(frame);
				try {
					ImageIO.write(image, "png", file);
				} catch (Exception e) {
					logger.log(Level.SEVERE,"Could not dump sprite "+i+" error ->"+e.getMessage());
					continue;
				}
			}

			double progress = (double) (i + 1) / size * 100;

			// Log progress
			logger.info((i + 1) + " out of " + size + " " + Math.round(progress) + "%");
		}

		System.exit(0);
	}
}
