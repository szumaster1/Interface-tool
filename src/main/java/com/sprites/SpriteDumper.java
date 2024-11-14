package com.sprites;

import com.logging.LogFactory;
import properties.PropertyValues;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpriteDumper {

	private static final Logger logger = LogFactory.createLogger(SpriteDumper.class.getName());

	// ANSI
	private static final String CYAN = "\u001B[36m";

	public static void dump() {
		String path = PropertyValues.sprite_path != null && !PropertyValues.sprite_path.isEmpty()
				? PropertyValues.sprite_path
				: "data/export/sprites";

		File directory = new File(path);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		int size = SpriteLoader.getNumSprites();
		int exportedCount = 0;
		int missingCount = 0;

		for (int i = 0; i < size; i++) {
			SpriteArchive spra = SpriteLoader.getArchive(i);
			if (spra == null) {
				missingCount++;
				continue;
			}

			for (int frame = 0; frame < spra.size(); frame++) {
				BufferedImage image = spra.getSprite(frame);
				if (image == null) {
					missingCount++;
					continue;
				}

				File file = new File(path, i + "_" + frame + ".png");
				try {
					ImageIO.write(image, "png", file);
					exportedCount++;
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Could not dump sprite " + i + " frame " + frame + " error -> " + e.getMessage());
					missingCount++;
				}
			}

			double progress = (double) (i + 1) / size * 100;
			logger.info(CYAN + (i + 1) + " out of " + size + " sprites exported (" + Math.round(progress) + "%)");
		}

		logger.info("Export finished.");
		logger.info("Total sprites exported: " + exportedCount);
		logger.info("Total missing: " + missingCount);
	}
}
