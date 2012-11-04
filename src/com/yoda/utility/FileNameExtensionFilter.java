package com.yoda.utility;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameExtensionFilter implements FilenameFilter {
	String ext;

	public FileNameExtensionFilter(String ext) {
		this.ext = "." + ext;
	}

	public boolean accept(File dir, String name) {
		return name.endsWith(ext);
	}
}