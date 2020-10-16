package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;


public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		
		double maxlon = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlon - minlon)); 

		return ystep;
		
	}

	public void showRouteMap(int ybase) {
		double minlong = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlati = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		
		for (int i=0; i < gpspoints.length; i++) {
			double a = gpspoints[i].getLongitude();
			double b = gpspoints[i].getLatitude();
			setColor(251,51,0);
			fillCircle((int)((a - minlong)*xstep())+50,ybase - (int)((b - minlati)*ystep()),3);
			drawCircle((int)((a - minlong)*xstep())+50,ybase - (int)((b - minlati)*ystep()),3);
			
		}
		
		for (int i=0; i < gpspoints.length-1; i++) {
			double a = gpspoints[i].getLongitude();
			double b = gpspoints[i].getLatitude();
			double c = gpspoints[i+1].getLongitude();
			double d = gpspoints[i+1].getLatitude();
			setColor(0, 0, 255);
			drawLine((int)((a - minlong)*xstep())+50,ybase - (int)((b - minlati)*ystep()),(int)((c - minlong)*xstep())+50,ybase - (int)((d - minlati)*ystep()));
			
		}
		double a = gpspoints[gpspoints.length-1].getLongitude();
		double b = gpspoints[gpspoints.length-1].getLatitude();
		setColor(0,0,0);
		drawCircle((int)((a - minlong)*xstep())+50,ybase - (int)((b - minlati)*ystep()),4);
		setColor(66, 245, 212);
		fillCircle((int)((a - minlong)*xstep())+50,ybase - (int)((b - minlati)*ystep()),4);
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		String a = "Total time:         " + GPSUtils.formatTime(gpscomputer.totalTime());
		String b = "Total distance:  " + GPSUtils.formatDouble(gpscomputer.totalDistance()/1000) + " km";
		String c = "Total Elevation: " + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m";
		String d = "Total Elevation: " + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " km/t";
		String e = "Maxspeed:        " + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t";
		String f = "Average Speed:   " + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t";
		String g = "Energy:          " + GPSUtils.formatDouble(gpscomputer.kcal(80, gpscomputer.totalTime(), gpscomputer.averageSpeed()/3.6)) + " kcal";
		
		drawString(a,TEXTDISTANCE,20);
		drawString(b,TEXTDISTANCE,20+(TEXTDISTANCE*1));
		drawString(c,TEXTDISTANCE,20+(TEXTDISTANCE*2));
		drawString(d,TEXTDISTANCE,20+(TEXTDISTANCE*3));
		drawString(e,TEXTDISTANCE,20+(TEXTDISTANCE*4));
		drawString(f,TEXTDISTANCE,20+(TEXTDISTANCE*5));
		drawString(g,TEXTDISTANCE,20+(TEXTDISTANCE*6));
		
		drawRectangle(10, 5, 240, 150);
	}

}
