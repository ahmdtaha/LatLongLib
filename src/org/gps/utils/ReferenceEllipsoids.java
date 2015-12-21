package org.gps.utils;


public class ReferenceEllipsoids
{

    private ReferenceEllipsoids()
    {
    }

    public static Ellipsoid getEllipsoid(int i)
    {
        if(i < 0 || i > 22)
            return null;
        else
            return ellipsoids[i];
    }

    public static Ellipsoid[] getEllipsoids()
    {
        return ellipsoids;
    }

    public static final int AIRY = 0;
    public static final int AUSTRALIAN_NATIONAL = 1;
    public static final int BESSEL_1841 = 2;
    public static final int BESSEL_1841_NAMIBIA = 3;
    public static final int CLARKE_1866 = 4;
    public static final int CLARKE_1880 = 5;
    public static final int EVEREST = 6;
    public static final int FISHER_1960_MERCURY = 7;
    public static final int FISHER_1968 = 8;
    public static final int GRS_1967 = 9;
    public static final int GRS_1980 = 10;
    public static final int HELMERT_1906 = 11;
    public static final int HOUGH = 12;
    public static final int INTERNATIONAL = 13;
    public static final int KRASSOVSKY = 14;
    public static final int MODIFIED_AIRY = 15;
    public static final int MODIFIED_EVEREST = 16;
    public static final int MODIFIED_FISCHER_1960 = 17;
    public static final int SOUTH_AMERICAN_1969 = 18;
    public static final int WGS_60 = 19;
    public static final int WGS_66 = 20;
    public static final int WGS_72 = 21;
    public static final int WGS_84 = 22;
    private static Ellipsoid ellipsoids[] = {
        new Ellipsoid("Airy", 6377563D, 0.00667054D), new Ellipsoid("Australian National", 6378160D, 0.0066945420000000004D), new Ellipsoid("Bessel 1841", 6377397D, 0.0066743719999999996D), new Ellipsoid("Bessel 1841 (Nambia)", 6377484D, 0.0066743719999999996D), new Ellipsoid("Clarke 1866", 6378206D, 0.0067686580000000003D), new Ellipsoid("Clarke 1880", 6378249D, 0.0068035109999999999D), new Ellipsoid("Everest", 6377276D, 0.0066378469999999997D), new Ellipsoid("Fischer 1960 (Mercury) ", 6378166D, 0.0066934220000000001D), new Ellipsoid("Fischer 1968", 6378150D, 0.0066934220000000001D), new Ellipsoid("GRS 1967", 6378160D, 0.0066946050000000002D), 
        new Ellipsoid("GRS 1980", 6378137D, 0.0066943799999999998D), new Ellipsoid("Helmert 1906", 6378200D, 0.0066934220000000001D), new Ellipsoid("Hough", 6378270D, 0.00672267D), new Ellipsoid("International", 6378388D, 0.00672267D), new Ellipsoid("Krassovsky", 6378245D, 0.0066934220000000001D), new Ellipsoid("Modified Airy", 6377340D, 0.00667054D), new Ellipsoid("Modified Everest", 6377304D, 0.0066378469999999997D), new Ellipsoid("Modified Fischer 1960", 6378155D, 0.0066934220000000001D), new Ellipsoid("South American 1969", 6378160D, 0.0066945420000000004D), new Ellipsoid("WGS 60", 6378165D, 0.0066934220000000001D), 
        new Ellipsoid("WGS 66", 6378145D, 0.0066945420000000004D), new Ellipsoid("WGS-72", 6378135D, 0.0066943180000000003D), new Ellipsoid("WGS-84", 6378137D, 0.0066943799999999998D)
    };

}
