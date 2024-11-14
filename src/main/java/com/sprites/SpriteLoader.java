package com.sprites;

import com.alex.store.Index;
import com.alex.utils.Constants;
import com.rs.cache.Cache;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class SpriteLoader {

    public static HashMap<Integer, SpriteArchive> spriteCache = new HashMap<>();

    public static int getNumSprites() {
        try {
            Index spriteIndex = getSpriteIndex();
            if (spriteIndex == null) {
                System.out.println("Failed to retrieve sprite index.");
                return 0;
            }
            return spriteIndex.getLastArchiveId();
        } catch (Exception e) {
            System.out.println("Error retrieving the number of sprites: " + e.getMessage());
            return 0;
        }
    }

    public static SpriteArchive getArchive(int archive) {
        if (spriteCache.containsKey(archive)) {
            return spriteCache.get(archive);
        }

        try {
            Index idx = getSpriteIndex();
            byte[] spriteData = idx.getFile(archive);

            if (spriteData == null) {
                System.out.println("No data found for archive ID: " + archive);
                return null;
            }

            ByteBuffer spriteBuff = ByteBuffer.wrap(spriteData);
            SpriteArchive s = SpriteArchive.decode(spriteBuff);

            if (s == null) {
                System.out.println("Failed to decode data for archive ID: " + archive);
                return null;
            }

            spriteCache.put(archive, s);
            return s;
        } catch (Exception e) {
            System.out.println("Error loading archive ID: " + archive + " - " + e.getMessage());
            return null;
        }
    }

    public static BufferedImage getSprite(int archive) {
        return getSprite(archive, 0);
    }

    public static BufferedImage getSprite(int archive, int fileIndex) {
        try {
            SpriteArchive arch = getArchive(archive);

            if (arch == null) {
                System.out.println("No archive found with ID: " + archive);
                return null;
            }

            BufferedImage sprite = arch.getSprite(fileIndex);

            if (sprite == null) {
                System.out.println("No sprite found with file index " + fileIndex + " in archive ID: " + archive);
            }

            return sprite;
        } catch (Exception e) {
            System.out.println("Error retrieving sprite with ID: " + archive + " and file index " + fileIndex + " - " + e.getMessage());
            return null;
        }
    }

    public static Index getSpriteIndex() {
        try {
            Index[] indexes = Cache.STORE.getIndexes();
            if (indexes == null || indexes.length <= Constants.SPRITES_INDEX) {
                System.out.println("Index number " + Constants.SPRITES_INDEX + " does not exist in the indexes array.");
                return null;
            }
            return indexes[Constants.SPRITES_INDEX];
        } catch (Exception e) {
            System.out.println("Error retrieving sprite index: " + e.getMessage());
            return null;
        }
    }
}
