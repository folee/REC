package com.yoda.utility;

public abstract class DIUtility {
	
	public static String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
	public static String formatSecondsAsDuration(long milliseconds) {
	    long seconds = milliseconds / 1000;
		long hour = seconds / 60 / 60;
	    long min = (seconds / 60) - (hour * 60);
	    long sec = seconds - (min * 60) - (hour * 60 * 60);

	    if (hour > 0)
	    {
	    	return (hour < 10 ? "0" : "") + hour + ":" + 
	    			(min < 10 ? "0" : "") + min + ":" +
	    			(sec < 10 ? "0" : "") + sec;
	    }
	    else
	    {
	    	return 	(min < 10 ? "0" : "") + min + ":" +
	    			(sec < 10 ? "0" : "") + sec;
	    }
	}

}
