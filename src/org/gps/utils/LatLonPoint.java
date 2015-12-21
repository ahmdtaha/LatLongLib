package org.gps.utils;

/**
 * 
 * @author ahmdalitaha
 * @since 19-6-2009
 * Used to represent a point through latitude and longitude.
 */
public class LatLonPoint {
	/**
	 * double latitude<br>
	 * indicates the latitude of the point 
	 */
	private double latitude;
	/**
	 * double longitude<br>
	 * indicates the longitude of the point
	 */
	private double longitude;
	
	public LatLonPoint(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}
	/**
	 * <b>getLatitude</b><br>
	 * called to get the Latitude of the point 
	 * @return Latitude of the point
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * <b>setLatitude</b><br>
	 * called to set the Latitude of the point
	 * @param latitude : Latitude of the point
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * <b>getLongitude</b><br>
	 * called to get the Longitude of the point
	 * @return Longitude of the point
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * <b>setLongitude</b><br>
	 * called to set the Longitude of the point
	 * @param longitude : Longitude of the point
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
