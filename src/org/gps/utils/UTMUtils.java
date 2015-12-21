package org.gps.utils;


/**
 * 
 * @author ahmdalitaha
 * @since 19-6-2009
 * Utility class the provide basic UTM operations
 */
public abstract class UTMUtils
{

	/**
	 * <b>LLtoUTM</b><br>
	 * called to convert a latitude/longitude point to UTM point
	 * @param i indicates which Ellipsoid does the point lies in.
	 * @param f indicates which latitude of the point.
	 * @param f1 indicates which longitude of the point.
	 * @return utm point
	 */
    public static UTMPoint LLtoUTM(int i, double f, double f1)
    {
        Ellipsoid ellipsoid = ReferenceEllipsoids.getEllipsoid(i);
        double d = f;
        double d1 = f1;
        double d2 = ellipsoid.radius;
        double d3 = ellipsoid.eccsq;
        double d4 = 0.99960000000000004D;
        double d12 = (d1 + 180D) - (double)((int)((d1 + 180D) / 360D) * 360) - 180D;
        double d13 = d * 0.017453292519943295D;
        double d14 = d12 * 0.017453292519943295D;
        int j = (int)((d12 + 180D) / 6D) + 1;
        if(d >= 56D && d < 64D && d12 >= 3D && d12 < 12D)
            j = 32;
        if(d >= 72D && d < 84D)
            if(d12 >= 0.0D && d12 < 9D)
                j = 31;
            else
            if(d12 >= 9D && d12 < 21D)
                j = 33;
            else
            if(d12 >= 21D && d12 < 33D)
                j = 35;
            else
            if(d12 >= 33D && d12 < 42D)
                j = 37;
        double d5 = ((j - 1) * 6 - 180) + 3;
        double d15 = d5 * 0.017453292519943295D;
        double d6 = d3 / (1.0D - d3);
        double d7 = d2 / Math.sqrt(1.0D - d3 * Math.sin(d13) * Math.sin(d13));
        double d8 = Math.tan(d13) * Math.tan(d13);
        double d9 = d6 * Math.cos(d13) * Math.cos(d13);
        double d10 = Math.cos(d13) * (d14 - d15);
        double d11 = d2 * ((((1.0D - d3 / 4D - (3D * d3 * d3) / 64D - (5D * d3 * d3 * d3) / 256D) * d13 - ((3D * d3) / 8D + (3D * d3 * d3) / 32D + (45D * d3 * d3 * d3) / 1024D) * Math.sin(2D * d13)) + ((15D * d3 * d3) / 256D + (45D * d3 * d3 * d3) / 1024D) * Math.sin(4D * d13)) - ((35D * d3 * d3 * d3) / 3072D) * Math.sin(6D * d13));
        float f2 = (float)(d4 * d7 * (d10 + (((1.0D - d8) + d9) * d10 * d10 * d10) / 6D + ((((5D - 18D * d8) + d8 * d8 + 72D * d9) - 58D * d6) * d10 * d10 * d10 * d10 * d10) / 120D) + 500000D);
        float f3 = (float)(d4 * (d11 + d7 * Math.tan(d13) * ((d10 * d10) / 2D + (((5D - d8) + 9D * d9 + 4D * d9 * d9) * d10 * d10 * d10 * d10) / 24D + ((((61D - 58D * d8) + d8 * d8 + 600D * d9) - 330D * d6) * d10 * d10 * d10 * d10 * d10 * d10) / 720D)));
        if(d < 0.0D)
            f3 += 1E+007F;
        return new UTMPoint(f3, f2, j, UTMLetterDesignator(d));
    }
    
    private static char UTMLetterDesignator(double d)
    {
        char c = 'Z';
        if(84D >= d && d >= 72D)
            c = 'X';
        else
        if(72D > d && d >= 64D)
            c = 'W';
        else
        if(64D > d && d >= 56D)
            c = 'V';
        else
        if(56D > d && d >= 48D)
            c = 'U';
        else
        if(48D > d && d >= 40D)
            c = 'T';
        else
        if(40D > d && d >= 32D)
            c = 'S';
        else
        if(32D > d && d >= 24D)
            c = 'R';
        else
        if(24D > d && d >= 16D)
            c = 'Q';
        else
        if(16D > d && d >= 8D)
            c = 'P';
        else
        if(8D > d && d >= 0.0D)
            c = 'N';
        else
        if(0.0D > d && d >= -8D)
            c = 'M';
        else
        if(-8D > d && d >= -16D)
            c = 'L';
        else
        if(-16D > d && d >= -24D)
            c = 'K';
        else
        if(-24D > d && d >= -32D)
            c = 'J';
        else
        if(-32D > d && d >= -40D)
            c = 'H';
        else
        if(-40D > d && d >= -48D)
            c = 'G';
        else
        if(-48D > d && d >= -56D)
            c = 'F';
        else
        if(-56D > d && d >= -64D)
            c = 'E';
        else
        if(-64D > d && d >= -72D)
            c = 'D';
        else
        if(-72D > d && d >= -80D)
            c = 'C';
        return c;
    }
    /**
     * <b>UTMtoLL</b><br>
     * called to convert a UTM point into latitude and longitude point
     * @param i indicates which Ellipsoid does the point lies in.
     * @param utmpoint UTM point to convert
     * @return latitude and longitude point
     */
    public static LatLonPoint UTMtoLL(int i, UTMPoint utmpoint)
    {
        return UTMtoLL(i, utmpoint.getNorthing(), utmpoint.getEasting(), utmpoint.getZoneNumber(), utmpoint.getZoneLetter());
    }
    
    public static LatLonPoint UTMtoLL(int i, float f, float f1, String s)
    {
        if(s == null || s.equals(""))
            return null;
        int j = 1;
        char c = 'N';
        int k = s.length() - 1;
        if(k > 0)
        {
            c = s.charAt(k);
            if(!Character.isLetter(c))
            {
                c = 'N';
                k++;
            }
        }
        try
        {
            j = Integer.parseInt(s.substring(0, k));
        }
        catch(NumberFormatException numberformatexception)
        {
            return null;
        }
        return UTMtoLL(i, f, f1, j, c);
    }

    public static LatLonPoint UTMtoLL(int i, double f, double f1, int j, boolean flag)
    {
        return UTMtoLL(i, f, f1, j, flag ? 'N' : 'M');
    }

    public static LatLonPoint UTMtoLL(int i, double f, double f1, int j, char c)
    {
        if(j < 0 || j > 60)
            return null;
        double d = 0.99960000000000004D;
        Ellipsoid ellipsoid = ReferenceEllipsoids.getEllipsoid(i);
        double d1 = ellipsoid.radius;
        double d2 = ellipsoid.eccsq;
        double d4 = (1.0D - Math.sqrt(1.0D - d2)) / (1.0D + Math.sqrt(1.0D - d2));
        double d15 = (double)f1 - 500000D;
        double d16 = f;
        boolean flag;
        if(c >= 'N')
        {
            flag = true;
        } else
        {
            boolean flag1 = false;
            d16 -= 10000000D;
        }
        double d11 = ((j - 1) * 6 - 180) + 3;
        double d3 = d2 / (1.0D - d2);
        double d10 = d16 / d;
        double d12 = d10 / (d1 * (1.0D - d2 / 4D - (3D * d2 * d2) / 64D - (5D * d2 * d2 * d2) / 256D));
        double d14 = d12 + ((3D * d4) / 2D - (27D * d4 * d4 * d4) / 32D) * Math.sin(2D * d12) + ((21D * d4 * d4) / 16D - (55D * d4 * d4 * d4 * d4) / 32D) * Math.sin(4D * d12) + ((151D * d4 * d4 * d4) / 96D) * Math.sin(6D * d12);
        double d13 = d14 * 57.295779513082323D;
        double d5 = d1 / Math.sqrt(1.0D - d2 * Math.sin(d14) * Math.sin(d14));
        double d6 = Math.tan(d14) * Math.tan(d14);
        double d7 = d3 * Math.cos(d14) * Math.cos(d14);
        double d8 = (d1 * (1.0D - d2)) / Math.pow(1.0D - d2 * Math.sin(d14) * Math.sin(d14), 1.5D);
        double d9 = d15 / (d5 * d);
        double d17 = d14 - ((d5 * Math.tan(d14)) / d8) * (((d9 * d9) / 2D - (((5D + 3D * d6 + 10D * d7) - 4D * d7 * d7 - 9D * d3) * d9 * d9 * d9 * d9) / 24D) + (((61D + 90D * d6 + 298D * d7 + 45D * d6 * d6) - 252D * d3 - 3D * d7 * d7) * d9 * d9 * d9 * d9 * d9 * d9) / 720D);
        d17 *= 57.295779513082323D;
        double d18 = ((d9 - ((1.0D + 2D * d6 + d7) * d9 * d9 * d9) / 6D) + (((((5D - 2D * d7) + 28D * d6) - 3D * d7 * d7) + 8D * d3 + 24D * d6 * d6) * d9 * d9 * d9 * d9 * d9) / 120D) / Math.cos(d14);
        d18 = d11 + d18 * 57.295779513082323D;
        return new LatLonPoint((float)d17, (float)d18);
    }

    static final double deg2rad = 0.017453292519943295D;
    static final double rad2deg = 57.295779513082323D;
}
