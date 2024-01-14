package com.sprites;

import com.alex.store.Index;
import com.alex.utils.Constants;
import com.rs.cache.Cache;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class SpriteLoader {
    public static HashMap<Integer, SpriteArchive> spriteCache = new HashMap<>();

    public static int getNumSprites()
    {
        Index spriteIndex = getSpriteIndex();
        return spriteIndex.getLastArchiveId();
    }

    public static SpriteArchive getArchive (int archive)
    {
        if (spriteCache.containsKey(archive))
        {
            return spriteCache.get(archive);
        }

        Index idx = getSpriteIndex();
        byte[] spriteData = idx.getFile(archive);

        if (spriteData == null)
            return null;

        ByteBuffer spriteBuff = ByteBuffer.wrap(spriteData);
        SpriteArchive s = SpriteArchive.decode(spriteBuff);

        if (s == null)
            return null;

        spriteCache.put(archive, s);
        return s;
    }

    public static BufferedImage getSprite (int archive)
    {
        return getSprite(archive, 0);
    }

    public static BufferedImage getSprite (int archive, int fileIndex)
    {
        SpriteArchive arch = getArchive(archive);

        if (arch == null)
            return null;

        return arch.getSprite(fileIndex);
    }

    public static Index getSpriteIndex()
    {
        return Cache.STORE.getIndexes()[Constants.SPRITES_INDEX];
    }
}
