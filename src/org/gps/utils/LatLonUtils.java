package org.gps.utils;



/**
 * 
 * @author ahmdalitaha
 * @since 19-6-2009
 * Utility class the provide basic latitude/longitude operations
 */
public abstract class LatLonUtils {
	/**
	 * <b>calculateDMSFormat</b><br>
	 * called to get the degree minute second representation of latitude/longitude  point 
	 * @param pt latitude/longitude  point
	 * @return degree minute second representation
	 */
	public static DMSPoint calculateDMSFormat(LatLonPoint pt)
	{
		boolean latNorth = true;
		boolean lngEast = true;
		
		if(pt.getLatitude()< 0)
			latNorth = false;
		if(pt.getLongitude() < 0)
			lngEast = false;
		
		int latDegree = (int)Math.abs(pt.getLatitude());
		int lngDegree = (int)Math.abs(pt.getLongitude());

		double tempLatitude  = Math.abs(pt.getLatitude());
		double tempLongitude = Math.abs(pt.getLongitude());
		
		double ddLatRemainder  = (tempLatitude  - (int)tempLatitude) *60;
		int latMinute = (int)ddLatRemainder;
		double ddLongRemainder   = (tempLongitude - (int)tempLongitude) *60;
		int lngMinute = (int)ddLongRemainder;
		
		double ddLatMinRemainder = (ddLatRemainder - (int)Math.abs(ddLatRemainder)) * 60;
		double latSeconds = (ddLatMinRemainder);
		double ddLongMinRemainder  = (ddLongRemainder - (int)Math.abs(ddLongRemainder)) * 60;
		double lngSeconds = (ddLongMinRemainder);
		
		DMSPt latDMS = new DMSPt(latDegree,latMinute,latSeconds,latNorth);
		DMSPt lngDMS = new DMSPt(lngDegree,lngMinute,lngSeconds,lngEast);
		return new DMSPoint(latDMS,lngDMS);
	}
	
	/**
	 * <b>calculateDDFormat</b><br>
	 * called to get the latitude/longitude representation of degree minute second point  
	 * @param pt degree minute second point
	 * @return latitude/longitude representation
	 */
	public static LatLonPoint calculateDDFormat(DMSPoint pt)
	{
		double ddLatVal = pt.getLatDMSPt().getDegree()*1 + pt.getLatDMSPt().getMinute()/60.0 + pt.getLatDMSPt().getSecond()/3600.0;
		if(!pt.getLatDMSPt().isEastOrNorth())
			ddLatVal = ddLatVal * -1;
		
		// find decimal longitude
		double ddLongVal = pt.getLngDMSPt().getDegree()*1 + pt.getLngDMSPt().getMinute()/60.0 + pt.getLngDMSPt().getSecond()/3600.0;
		if(!pt.getLngDMSPt().isEastOrNorth())
		ddLongVal = ddLongVal* -1;
		
		// round down a bit
		
		ddLatVal  = Math.round(ddLatVal * 100000) / 100000.0;
		ddLongVal = Math.round(ddLongVal * 100000) / 100000.0;
		
		return new LatLonPoint(ddLatVal,ddLongVal); 
	}
	/**
	 * <b>getSlope</b><br>
	 * called to get the slope between two latitude/longitude  points 
	 * @param start the starting latitude/longitude  point
	 * @param end the end latitude/longitude  point
	 * @return slope between two points
	 */
	public static double getSlope(LatLonPoint start,LatLonPoint end)
	{
		double lat1 = start.getLatitude() * Math.PI / 180;
		double lat2 = end.getLatitude() * Math.PI / 180;
		double dLon = (end.getLongitude()-start.getLongitude()) * Math.PI / 180;
		 
		double y = Math.sin(dLon) * Math.cos(lat2);
		double x = Math.cos(lat1)*Math.sin(lat2) -
		          Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
		double brng = ((Math.atan2(y, x) * 180 /Math.PI) + 360) % 360; 
		return brng;
		
	}
	/**
	 * <b>getPointAtDistance</b><br>
	 * called to get a latitude/longitude  point apart from a certain point by certain distance in certain direction
	 * @param pt : starting point 
	 * @param slope : slope between the two points
	 * @param distance : the distance between two points
	 * @return
	 */
	public static LatLonPoint getPointAtDistance(LatLonPoint pt,double slope,double distance)
	{
		double lat1 = pt.getLatitude() * Math.PI/ 180;
		double lon1 = pt.getLongitude() * Math.PI/ 180;
		slope = slope * Math.PI/ 180;
		int R = 6371000;
		
		double lat2 = Math.asin( Math.sin(lat1)*Math.cos(distance/R) + 
                Math.cos(lat1)*Math.sin(distance/R)*Math.cos(slope) );
		double lon2 = lon1 + Math.atan2(Math.sin(slope)*Math.sin(distance/R)*Math.cos(lat1), 
                       Math.cos(distance/R)-Math.sin(lat1)*Math.sin(lat2));
		lon2 = (lon2+Math.PI)%(2*Math.PI) - Math.PI; 
		return new LatLonPoint(lat2 * 180 /Math.PI,lon2 * 180 /Math.PI);
	}
	/**
	 * <b>getQuickEstimate</b><br>
	 * called to get the distance between two latitude/longitude points 
	 * @param startLocation : starting latitude/longitude  points
	 * @param endLocation : end latitude/longitude points
	 * @return distance between the two points in meters
	 */
	public static double getQuickEstimate(LatLonPoint startLocation,LatLonPoint endLocation)
	{
		double sin1 = Math.sin(startLocation.getLatitude()/180*Math.PI);
		double sin2 = Math.sin(endLocation.getLatitude()/180*Math.PI);
		double cos1 = Math.cos(startLocation.getLatitude()/180*Math.PI);
		double cos2 = Math.cos(endLocation.getLatitude()/180*Math.PI);
		double cos3 = Math.cos(startLocation.getLongitude()/180*Math.PI - endLocation.getLongitude() / 180 * Math.PI);
		double acos = (Math.acos(sin1 * sin2 + cos1 * cos2 * cos3)*180*60/Math.PI);
		return acos*1.852*1000;
	}
	/**
	 * <b>getHaversineDistance</b><br>
	 * called to get the distance between two latitude/longitude points using Haversine method 
	 * @param startLocation : starting latitude/longitude  points
	 * @param endLocation : end latitude/longitude points
	 * @return distance between the two points in meters
	 */
	public static double  getHaversineDistance(LatLonPoint startLocation,LatLonPoint endLocation) {
		return Haversine_Distance(startLocation.getLatitude(), startLocation.getLongitude(), endLocation.getLatitude(), endLocation.getLongitude());
	}
	/**
	 * <b>getVincentyDistance</b><br>
	 * called to get the distance between two latitude/longitude points using Vincenty method
	 * @param startLocation : starting latitude/longitude  points
	 * @param endLocation : end latitude/longitude points
	 * @return distance between the two points in meters
	 */
	public static double getVincentyDistance(LatLonPoint startLocation,LatLonPoint endLocation) {
		return Vincenty_Distance(startLocation.getLatitude(), startLocation.getLongitude(), endLocation.getLatitude(), endLocation.getLongitude());
	}
	/**
	 * <b>Haversine_Distance</b><br>
	 * called to get the distance between two latitude/longitude points using Haversine method
	 * @param lat1 : latitude of the starting point
	 * @param lon1 : longitude of the starting point
	 * @param lat2 : latitude of the end point
	 * @param lon2 : longitude of the end point
	 * @return @return distance between the two points in meters
	 */
	private static  double Haversine_Distance(double lat1,double lon1,double lat2,double lon2) {
		// http://www.movable-type.co.uk/scripts/LatLong.html
		if (Math.abs(lat1) > 90 || Math.abs(lon1) > 180 || Math.abs(lat2) > 90 || Math.abs(lon2) > 180) { return -1; }
		lat1 = deg2rad(lat1); lon1 = deg2rad(lon1);
		lat2 = deg2rad(lat2); lon2 = deg2rad(lon2);
		double dlat = lat2-lat1; // delta
		double dlon = lon2-lon1; // delta
		double re = 6378137; // equatorial radius
		double rp = 6356752; // polar radius
		double r45 = re * Math.sqrt( (1 + ( (rp*rp-re*re)/(re*re) ) * (Math.sin(45)*Math.sin(45)) ) ); // from http://www.newton.dep.anl.gov/askasci/gen99/gen99915.htm
		double a = ( Math.sin(dlat/2) * Math.sin(dlat/2) ) + ( Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon/2) * Math.sin(dlon/2) );
		double c = 2 * Math.atan( Math.sqrt(a)/Math.sqrt(1-a) );
		double d_ellipse = r45 * c;
		//---this part can be used to get distance in feet and miles
		/*if (us) {
			double dist = d_ellipse / 1609.344;
			if (dist < 1) {
				return (Math.round(5280 * 1 * dist) / 1) + ' ft';
			} else {
				return (Math.round(100 * dist) / 100) + ' mi';
			}
		}
		//---this part can be used to get distance in meters or Kilometers 
		else {*/
			/*double dist = d_ellipse / 1000.0;
			if (dist < 1) {
				System.out.println("meters");
				return (Math.round(1000.0 * 1.0 * dist) / 1) ;
			} else {
				System.out.println("K meters");
				return (Math.round(100.0 * dist) / 100.0);
			}
		}*/
		double dist = d_ellipse / 1000.0;
		return (Math.round(1000.0 * 1.0 * dist) / 1) ;
	}
	/**
	 * <b>Vincenty_Distance</b><br>
	 * called to get the distance between two latitude/longitude points using Vincenty method
	 * @param lat1 : latitude of the starting point
	 * @param lon1 : longitude of the starting point
	 * @param lat2 : latitude of the end point
	 * @param lon2 : longitude of the end point
	 * @return distance between the two points in meters
	 */
	private static double  Vincenty_Distance(double lat1,double lon1,double lat2,double lon2) {
		// http://www.movable-type.co.uk/scripts/LatLongVincenty.html
		if (Math.abs(lat1) > 90 || Math.abs(lon1) > 180 || Math.abs(lat2) > 90 || Math.abs(lon2) > 180) { return -1; }
		if (lat1 == lat2 && lon1 == lon2) { return '0'; }
		
		lat1 = deg2rad(lat1); lon1 = deg2rad(lon1);
		lat2 = deg2rad(lat2); lon2 = deg2rad(lon2);

		double a = 6378137, b = 6356752.3142, f = 1/298.257223563;
		double L = lon2 - lon1;
		double U1 = Math.atan((1-f) * Math.tan(lat1));
		double U2 = Math.atan((1-f) * Math.tan(lat2));
		double sinU1 = Math.sin(U1), cosU1 = Math.cos(U1);
		double sinU2 = Math.sin(U2), cosU2 = Math.cos(U2);
		double lambda = L, lambdaP = 2*Math.PI;
		int iterLimit = 20;
		double sinLambda =0.0,cosLambda =0.0;
		double sinSigma =0.0,cosSigma =0.0; 
		double sigma =0.0 , alpha =0.0 ;
		double cosSqAlpha =0.0, cos2SigmaM =0.0;
		double C = 0.0;
		while (Math.abs(lambda-lambdaP) > 1e-12 && --iterLimit > 0) {
			sinLambda = Math.sin(lambda); 
			cosLambda = Math.cos(lambda);
			sinSigma = Math.sqrt((cosU2*sinLambda) * (cosU2*sinLambda) + 
			  (cosU1*sinU2-sinU1*cosU2*cosLambda) * (cosU1*sinU2-sinU1*cosU2*cosLambda));
			cosSigma = sinU1*sinU2 + cosU1*cosU2*cosLambda;
			sigma = Math.atan2(sinSigma, cosSigma);
			alpha = Math.asin(cosU1 * cosU2 * sinLambda / sinSigma);
			cosSqAlpha = Math.cos(alpha) * Math.cos(alpha);
			cos2SigmaM = cosSigma - 2*sinU1*sinU2/cosSqAlpha;
			C = f/16*cosSqAlpha*(4+f*(4-3*cosSqAlpha));
			lambdaP = lambda;
			lambda = L + (1-C) * f * Math.sin(alpha) * (sigma + C*sinSigma*(cos2SigmaM+C*cosSigma*(-1+2*cos2SigmaM*cos2SigmaM)));
		}
		if (iterLimit==0) { return (-2); }  // formula failed to converge
		double uSq = cosSqAlpha*(a*a-b*b)/(b*b);
		double A = 1 + uSq/16384*(4096+uSq*(-768+uSq*(320-175*uSq)));
		double B = uSq/1024 * (256+uSq*(-128+uSq*(74-47*uSq)));
		double deltaSigma = B*sinSigma*(cos2SigmaM+B/4*(cosSigma*(-1+2*cos2SigmaM*cos2SigmaM) - B/6*cos2SigmaM*(-3+4*sinSigma*sinSigma)*(-3+4*cos2SigmaM*cos2SigmaM)));
		double s = b*A*(sigma-deltaSigma);
		//---this part can be used to get distance in feet and miles
		/*if (us) {
			var dist = s / 1609.344;
			if (dist < 0.2) {
				return (Math.round(5280 * 1 * dist) / 1) + ' ft';
			} else {
				return (Math.round(1000 * dist) / 1000) + ' mi';
			}
		}
		//---this part can be used to get distance in meters or Kilometers
		 else {
			double dist = s / 1000.0;
			if (dist < 1) {
				System.out.println("meters");
				return (Math.round(1000.0 * 1 * dist) / 1);
			} else {
				System.out.println("k meters");
				return (Math.round(1000.0 * dist) / 1000.0);
			}
		}*/
		double dist = s / 1000.0;
		return (Math.round(1000.0 * 1 * dist) / 1);
	}
	/**
	 * <b>deg2rad</b><br>
	 * called to convert certain angle from degree representation to radian representation
	 * @param deg : angle in degrees
	 * @return : angle in radian 
	 */
	private static double deg2rad (double deg) {
		return (deg * Math.PI/180);
	}
	

}
