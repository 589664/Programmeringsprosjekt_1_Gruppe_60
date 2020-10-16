package no.hvl.dat100ptc.oppgave4;

import java.util.Arrays;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;
		
			for (int i = 0; i < gpspoints.length-1; i++) {
			distance += GPSUtils.distance(gpspoints[i],gpspoints[i+1]);
			}
			
		
		return distance;

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		for (int i=0; i < gpspoints.length-1; i++) {
			if(gpspoints[i+1].getElevation() > gpspoints[i].getElevation()) {
				elevation += gpspoints[i+1].getElevation() - gpspoints[i].getElevation();
			} 
		}
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		int time = 0;
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			time += gpspoints[i+1].getTime() - gpspoints[i].getTime();
			}
		
		return time;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		double speeds[] = new double [gpspoints.length-1];
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			speeds [i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			}
		
		return speeds;
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			maxspeed = GPSUtils.findMax(speeds());
			
			}
		return maxspeed;
	}

	public double averageSpeed() {

		double average = 0;
		double a = totalDistance();
		int b = totalTime();
		average = a/b*3.6;
		
		return average;
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;
		
		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
		double convert = secs;
		double timer = convert/3600;
		
		if (speedmph < 10) {
			met = 4;
		}
		else if (speedmph >= 10 && speedmph < 12) {
			met = 6;
		}
		else if (speedmph >= 12 && speedmph < 14) {
			met = 8;
		}
		else if (speedmph >= 14 && speedmph < 16) {
			met = 10;
		}
		else if (speedmph >= 16 && speedmph < 20) {
			met = 12;
		}
		else if (speedmph >= 20) {
			met = 16;
		}
		
		kcal = met * weight * timer;
		
		return kcal;
		
	}

	public double totalKcal(double weight) {

		double totalkcal;
		
		totalkcal = kcal(weight,totalTime(),averageSpeed()/3.6);
		
		return totalkcal;
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		
				
		System.out.println("==============================================");
		System.out.println("Total time     :   " + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance :" + GPSUtils.formatDouble(totalDistance()/1000) + " km");
		System.out.println("Total elevation:" + GPSUtils.formatDouble(totalElevation()) + " m");
		System.out.println("Max speed      :" + GPSUtils.formatDouble(maxSpeed()) + " km/t");
		System.out.println("Average speed: :" + GPSUtils.formatDouble(averageSpeed()) + " km/t");
		System.out.println("Energy         :" + GPSUtils.formatDouble(kcal(WEIGHT,totalTime(),averageSpeed()/3.6)) + " kcal");
		System.out.println(Arrays.toString(climbs()));
		System.out.println(maxClimb());
	}
	
	public double[] climbs() {
	
		double[] climbs = new double [gpspoints.length-1];

		for (int i=0; i < gpspoints.length-1; i++) {
			
			if(gpspoints[i+1].getElevation() > gpspoints[i].getElevation()) {
			double a = (gpspoints[i+1].getElevation()-gpspoints[i].getElevation()); //hogdeforskjell aka motst�ande katet
			double b = GPSUtils.distance(gpspoints[i+1],gpspoints[i]); // distanse aka hostliggande katet
			double c = Math.atan(a/b); // finne grader
			double d = Math.toRadians(c); // til radianer
			double e = Math.tan(c)*100;  // til prosent
			String m = String.format("%.2f", e);
			double k = Double.parseDouble(m);
			climbs [i] = k;
				}
			
			} 
		
		return climbs;

	}
	
	public double maxClimb() {
		double maxClimb = 0;
		
			maxClimb = GPSUtils.findMax(climbs());
			
		return maxClimb;
	}
}
