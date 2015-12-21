package org.gps.utils;
/**
 * 
 * @author ahmdalitaha
 * @since 19-6-2009
 * Used to encapsulate representation of single coordinate in degree, minutes and seconds
 */
public class DMSPt {
	public DMSPt (int latDegree, int latMinute, double latSecond,
			boolean isEastOrNorth) {
		super();
		this.degree = latDegree;
		this.minute = latMinute;
		this.second = latSecond;
		this.isEastOrNorth = isEastOrNorth;
	}
	/**
	 * int degree<br>
	 * indicates the degree of the coordinate
	 */
	private int degree;
	/**
	 * int minute<br>
	 * indicates the minute of the coordinate
	 */
	private int minute;
	/**
	 * double second<br>
	 * indicates the second of the coordinate
	 */
	private double second;
	
	/**
	 * boolean isEastOrNorth<br>
	 * indicate whether the coordinate is in the East or North
	 */
	private boolean isEastOrNorth;

	/**
	 * <b>getDegree</b><br>
	 * called to get the Degree representation of coordinate
	 * @return Degree representation of coordinate
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * <b>getMinute</b><br>
	 * called to get the Minute representation of coordinate
	 * @return Minute representation of coordinate
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * <b>getSecond</b><br>
	 * called to get the Second representation of coordinate
	 * @return Second representation of coordinate
	 */
	public double getSecond() {
		return second;
	}
	/**
	 * <b>isEastOrNorth</b><br>
	 * called to know if coordinate is in the East or north or not
	 * @return true if coordinate is in the east or north else return false
	 */
	public boolean isEastOrNorth() {
		return isEastOrNorth;
	}
}
