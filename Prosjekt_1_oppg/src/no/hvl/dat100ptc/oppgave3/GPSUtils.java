package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double lattab [] = new double [gpspoints.length];
		
		for (int i = 0; i<gpspoints.length; i++) {
			lattab[i] = gpspoints[i].getLatitude();
		}
		return lattab;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double longtab [] = new double [gpspoints.length];
		
		for (int i = 0; i<gpspoints.length; i++) {
			longtab[i] = gpspoints[i].getLongitude();
		}
		return longtab;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = toRadians(gpspoint1.getLatitude());
		latitude2 = toRadians(gpspoint2.getLatitude());
		longitude1 = toRadians(gpspoint1.getLongitude());
		longitude2 = toRadians(gpspoint2.getLongitude());
		
		double phi1 = (latitude2 - latitude1);
		double lam = (longitude1 - longitude2);
		
		double a = pow((sin(phi1/2)),2) + cos(latitude1)*cos(latitude2)*pow(sin(lam/2),2);
		double c = 2 * atan2(sqrt(a),sqrt(1-a));
		d = R*c;
		
		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		double d = distance(gpspoint1,gpspoint2);
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = (d/secs)*3.6;
		
		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		
		int hh,mm,ss;
		
		hh = (secs / 3600);
		mm = (secs % 3600) / 60;
		ss = secs % 60;
		
		//String HH = ((hh < 10) ? "0" : "") + hh;
	    //String MM = ((mm < 10) ? "0" : "") + mm;
	    //String SS = ((ss < 10) ? "0" : "") + ss;
		
		String tid = String.format("%02d:%02d:%02d", hh,mm,ss);
		
		timestr = String.format("%10s", tid);
		
		//går ann å få i same steg
		
		return timestr;
		

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		//String str;
		//double a = Math.round(d * 100.0) / 100.0;
		
		//str = a + "";
				
		//String fyll = String.format("%10s", str);
		
		String str = String.format("%10.2f",d);
		
		return str;
		
	}
}
