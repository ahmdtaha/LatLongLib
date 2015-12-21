package org.gps.utils;

/**
 * 
 * @author ahmdalitaha
 * @since 19-6-2009
 * Used to encapsulate latitude/longitude in degree, minutes and seconds
 */
public class DMSPoint {
	/**
	 * DMSPt latDMSPt<br>
	 * indicates the Latitude of point
	 */
	private DMSPt latDMSPt;
	/**
	 * DMSPt lngDMSPt
	 * indicates the longitude of point
	 */
	private DMSPt lngDMSPt;
	/**
	 * <b>Constructor</b><br>
	 * Create Lat/long point in the degree,minute representation and set the value of Latitude and longitude of the point 
	 * @param latDMSPt Latitude  of the point
	 * @param lngDMSPt longitude of the point
	 */
	public DMSPoint(DMSPt latDMSPt, DMSPt lngDMSPt) {
		super();
		this.latDMSPt = latDMSPt;
		this.lngDMSPt = lngDMSPt;
	}
	/**
	 * <b>getLatDMSPt</b><br>
	 * called to get the Latitude of the point
	 * @return Latitude of the point
	 */
	public DMSPt getLatDMSPt() {
		return latDMSPt;
	}
	/**
	 * <b>getLngDMSPt</b><br>
	 * called to get the longitude of the point
	 * @return longitude of the point
	 */
	public DMSPt getLngDMSPt() {
		return lngDMSPt;
	}
}
