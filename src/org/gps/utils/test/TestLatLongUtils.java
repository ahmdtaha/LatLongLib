package org.gps.utils.test;

import org.gps.utils.DMSPoint;

import org.gps.utils.LatLonPoint;
import org.gps.utils.LatLonUtils;

public class TestLatLongUtils {
	public static void main(String[] args) {
		testDistance();
		testSlope();
		testPointAtDistance();
		testConversions();
	}
	
	private static void testConversions() {
		System.out.println();
		System.out.println("========================");
		System.out.println("Start Testing Conversions");
		LatLonPoint startPoint = new LatLonPoint(42.90045, -75.71592);
		DMSPoint dmsPoint = LatLonUtils.calculateDMSFormat(startPoint);
		System.out.print(dmsPoint.getLatDMSPt().getDegree()+"."+dmsPoint.getLatDMSPt().getMinute()+"*"+dmsPoint.getLatDMSPt().getSecond());
		System.out.print(","+dmsPoint.getLngDMSPt().getDegree()+"."+dmsPoint.getLngDMSPt().getMinute()+"*"+dmsPoint.getLngDMSPt().getSecond());
		System.out.println();
		
		LatLonPoint initialPoint = LatLonUtils.calculateDDFormat(dmsPoint);
		System.out.println(initialPoint.getLatitude()+","+initialPoint.getLongitude());
		System.out.println("end Testing Conversions");
		System.out.println("========================");
	}

	private static void testPointAtDistance() {
		System.out.println();
		System.out.println("========================");
		System.out.println("Start Testing point at distance");
		LatLonPoint targetPoint = LatLonUtils.getPointAtDistance(new LatLonPoint(42.90045, -75.71592),45,100);
		System.out.println(targetPoint.getLatitude()+","+targetPoint.getLongitude());
		LatLonPoint startPoint = LatLonUtils.getPointAtDistance(targetPoint,180+45,100);
		System.out.println(startPoint.getLatitude()+","+startPoint.getLongitude());
		System.out.println("end Testing point at distance");
		System.out.println("========================");
		
	}

	private static void testSlope() {
		System.out.println();
		System.out.println("========================");
		System.out.println("Start Testing Slope");
		System.out.println(LatLonUtils.getSlope(new LatLonPoint(42.90045, -75.71592), new LatLonPoint(42.903375, -75.695351)));
		System.out.println(LatLonUtils.getSlope(new LatLonPoint(42.90045, -75.71592), new LatLonPoint(42.894825, -75.695351)));
		System.out.println("end Testing Slope");
		System.out.println("========================");
	}

	private static void testDistance()
	{
		System.out.println();
		System.out.println("========================");
		System.out.println("Start Testing Distance");
		System.out.println(LatLonUtils.getHaversineDistance(new LatLonPoint(42.90045, -75.71592), new LatLonPoint(42.903375, -75.695351)));
		System.out.println(LatLonUtils.getVincentyDistance(new LatLonPoint(42.90045, -75.71592), new LatLonPoint(42.903375, -75.695351)));
		System.out.println(LatLonUtils.getQuickEstimate(new LatLonPoint(42.90045, -75.71592), new LatLonPoint(42.903375, -75.695351)));
		System.out.println("end Testing Distance");
		System.out.println("========================");
	}
}
