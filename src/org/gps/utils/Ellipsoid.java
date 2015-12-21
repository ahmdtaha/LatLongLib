package org.gps.utils;


/**
 * 
 * @author ahmdalitaha
 * @since 19-6-2008
 * Used to encapsulate representation of an Ellipsoid 
 */
public class Ellipsoid
{
	/**
	 * <b>Constructor</b><br>
	 * called to create an {@link Ellipsoid} and set it's parameters  
	 * @param s name of Ellipsoid
	 * @param d radius of Ellipsoid
	 * @param d1 eccsq of Ellipsoid which is the square of this ellipsoid's eccentricity.
	 */
    public Ellipsoid(String s, double d, double d1)
    {
        name = s;
        radius = d;
        eccsq = d1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Ellipsoid[radius=").append(radius).append(",eccsq=").append(eccsq).append("]").toString();
    }

    public String name;
    public double radius;
    public double eccsq;
}
