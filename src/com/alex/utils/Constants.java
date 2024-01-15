package com.alex.utils;

import java.awt.*;

public final class Constants {

	private Constants() {

	}

	public static final int NO_COMPRESSION = 0;
	public static final int BZIP2_COMPRESSION = 1;
	public static final int GZIP_COMPRESSION = 2;
	public static final int LZMA_COMPRESSION = 3;
	public static final int MAX_VALID_ARCHIVE_LENGTH = 0x3b9aca00;
	public static final int INTERFACE_DEFINITIONS_INDEX = 3;
	public static final int MAPS_INDEX = 5;
	public static final int MODELS_INDEX = 7;
	public static final int SPRITES_INDEX = 8;
	public static final int INDEXED_IMAGES_INDEX = 8;
	public static final int OBJECTS_DEFINITIONS_INDEX = 18;
	public static final int ITEM_DEFINITIONS_INDEX = 19;
	public static final int LOADER_IMAGES_INDEX = 32;
	public static final int LOADER_INDEXED_IMAGES_INDEX = 34;
	public static final int CLIENT_BUILD = 876;
	public static final boolean ENCRYPTED_CACHE = true;
	public static final int LEFT_SCROLLPANE_WIDTH = 229;
	public static final int RIGHT_SCROLLPANE_WIDTH = 300;
	public static final int CONTENT_PADDING = 10;
	public static final int SEARCHBOX_WIDTH = 68;
	public static final int SEARCHBOX_HEIGHT = 32;
	public static final int BUTTON_WIDTH = 115;
	public static final int BUTTON_HEIGHT = 32;
	public static final int BUTTON_HEIGHT_SMALL = 24;
	public static final int BUTTON_WIDTH_SMALL = 75;

	public static final int DEFAULT_EDITOR_WIDTH = 1366;
	public static final int DEFAULT_EDITOR_HEIGHT = 768;
	public static final int TOPROW_HEIGHT = CONTENT_PADDING + SEARCHBOX_HEIGHT;
	public static final int PREMADE_COMP_WIDTH = LEFT_SCROLLPANE_WIDTH;
	public static final int PREMADE_COMP_HEIGHT = 112;
	public static final int INFO_FIELD_WIDTH = 300;
	public static final int INFO_FIELD_HEIGHT = BUTTON_HEIGHT_SMALL;
	public static final Color BG_FILL_COLOR = Color.lightGray;
	public static final int VIEWPORT_WIDTH = DEFAULT_EDITOR_WIDTH - LEFT_SCROLLPANE_WIDTH - RIGHT_SCROLLPANE_WIDTH;
	public static final int VIEWPORT_HEIGHT = 503;

}