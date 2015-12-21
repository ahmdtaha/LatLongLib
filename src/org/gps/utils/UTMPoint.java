package org.gps.utils;


/**
 * 
 * @author ahmdalitah
 * @since 19-6-2009
 * used to represent the point using the Universal Transverse Mercator coordinate system
 */
public class UTMPoint
{

    public UTMPoint(double north, double east, int zoneNumber, char zoneLetter)
    {
        northing = north;
        easting = east;
        zone_number = zoneNumber;
        zone_letter = zoneLetter;
    }

    public String toString()
    {
        return (new StringBuilder()).append("UTMPoint[northing=").append(northing).append(",eastin=").append(easting).append(",zone_number=").append(zone_number).append(" zone_letter=").append(zone_letter).append("]").toString();
    }
    /**
     * double northing<br>
     * indicate where point is with reference to north point
     */
    private double northing;
    /**
     * double easting<br>
     * indicate where point is with reference to east point
     */
    private double easting;
    /**
     * int zone_number<br>
     * indicate the zone number of the point 
     */
    private int zone_number;
    /**
     * char zone_letter<br>
     * indicate the zone letter of the point 
     */
    private char zone_letter;
    
    /**
     * <b>getNorthing</b><bt>
     * called to get the value of point with reference to north
     * @return
     */
	public double getNorthing() {
		return northing;
	}
	/**
	 * <b>getEasting</b><bt>
	 * called to get the value of point with reference to east
	 * @return
	 */
	public double getEasting() {
		return easting;
	}
	/**
	 * <b>getZoneNumber</b><bt>
	 * called to get the Zone number of the point
	 * @return Zone number of the point
	 */
	public int getZoneNumber() {
		return zone_number;
	}
	/**
	 * <b>getZoneLetter</b><bt>
	 * called to get the Zone letter of the point
	 * @return Zone letter of the point
	 */
	public char getZoneLetter() {
		return zone_letter;
	}
}
