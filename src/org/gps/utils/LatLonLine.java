package org.gps.utils;
/**
 * 
 * @author ahmdalitaha
 * @since 19-6-2009
 * Used to represent a line through two latitude and longitude points
 */
public class LatLonLine {
	public LatLonLine(LatLonPoint start, LatLonPoint end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	/**
	 * LatLonPoint start<br>
	 * indicates the starting point of the line
	 */
	private LatLonPoint start;
	/**
	 * LatLonPoint end<br>
	 * indicates the end point of the line
	 */
	private LatLonPoint end;
	
	/**
	 * <b>getStart</b><br>
	 * called to get the starting point of the line
	 * @return starting point of the line
	 */
	public LatLonPoint getStart() {
		return start;
	}
	/**
	 * <b>setStart</b><br>
	 * called to set the starting point of the line
	 * @param start: starting point of the line
	 */
	public void setStart(LatLonPoint start) {
		this.start = start;
	}
	/**
	 * <b>getEnd</b><br>
	 * called to get the ending point of the line
	 * @return ending point of the line
	 */
	public LatLonPoint getEnd() {
		return end;
	}
	/**
	 * <b>setEnd</b><br>
	 * called to set the ending point of the line
	 * @param end : ending point of the line
	 */
	public void setEnd(LatLonPoint end) {
		this.end = end;
	}
}
