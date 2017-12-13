package platform;
/**
 * Created by awesome on 17.11.2014.
 */

import java.util.List;
import java.util.Date;

 public class Station {
    private  String name;
    public float minDistance = Float.MAX_VALUE;
    public LinkStations adjList;   // this list contains all connections between this station and another one.
    public Station previous;
    public float lat;
    public float lon;
    Schedule[] allSchedule;
    
    public Station(String pname, LinkStations transporteri){
        this.name = pname;
        this.adjList = transporteri;
        
   
    }
    
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public float getMinDistance() {
        return minDistance;
    }
    
    public void setSchedule(int scheduleLength){
    	allSchedule = new Schedule[scheduleLength];
    }
    
    public void addSchedule(List<Date> plist, int indexvehicle){
    	allSchedule[indexvehicle]=new Schedule(plist,indexvehicle);
    }

 }