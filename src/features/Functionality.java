package features;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import platform.LinkStations;
import platform.Graph;
import platform.Station;
import platform.stationComparableByDistance;

//this class represent all features of platform class.

public class Functionality  {
	
public Graph g;

public Functionality(){
	 g = new Graph();
	
}

//functie care printeaza toate legaturile intre toate statiile 
        public String printAllConections(){
        String totgraful="";
        System.out.println();
        
        for(int s = 0; s<g.allStations.length ; s++){
            for(LinkStations  connection = g.allStations[s].adjList ; connection != null; connection = connection.next ){
                totgraful = totgraful + (g.allStations[s].getName()+" --> " + g.allStations[connection.getStationNum()].getName()
                        + connection.toString()+ " " + g.allVehicles[connection.getVehicleIndex()].toString()+"\n");
            }
        }
            return totgraful;
    }
//o functie care returneaza un fel de meniu - folosita in input device.
    public String returnStations(){
        String answer="";

        for(int i = 0 ; i < g.allStations.length ; i++){
            answer = answer + i + "." +  g.allStations[i].getName() + "  ";
        }
        return answer;
    }

//calculeaza cele mai scurte drumuri in functie de : int way - 0 - distance / 1 - time / 2- price.
    public void computePaths(int source, int way){  // source = index : allStations[source] = station current .
        g.allStations[source].minDistance = 0;    //distance from source to source
        PriorityQueue<Station> stationQueue = new PriorityQueue<Station>(10, new stationComparableByDistance());
        stationQueue.add(g.allStations[source]);

        switch(way){
            case 0 :  while(!stationQueue.isEmpty()) {
                Station currentStation = stationQueue.poll();
                //visiting each connection(EDGE) existing in u
                for (LinkStations connect = currentStation.adjList; connect != null; connect = connect.next) {
                    float distance = connect.getDistance();
                    float distaceRroughCurrentStation = currentStation.minDistance + distance;

                    if (distaceRroughCurrentStation < g.allStations[connect.getStationNum()].minDistance) {
                        // stationQueue.remove(allStations[connect.stationNum]);
                        g.allStations[connect.getStationNum()].minDistance = distaceRroughCurrentStation;
                        g.allStations[connect.getStationNum()].previous = currentStation;
                        stationQueue.add(g.allStations[connect.getStationNum()]);
                    }
                }
            }
                break;

            case 1 :  while(!stationQueue.isEmpty()) {
                Station currentStation = stationQueue.poll();
                //visiting each connection(EDGE) existing in u
                for (LinkStations connect = currentStation.adjList; connect != null; connect = connect.next) {
                    float time = connect.getTime();
                    float timeTroughCurrentStation = currentStation.minDistance + time;

                    if (timeTroughCurrentStation < g.allStations[connect.getStationNum()].minDistance) {
                        // stationQueue.remove(allStations[connect.stationNum]);
                        g.allStations[connect.getStationNum()].minDistance = timeTroughCurrentStation;
                        g.allStations[connect.getStationNum()].previous = currentStation;
                        stationQueue.add(g.allStations[connect.getStationNum()]);
                    }
                }
            }
                break;
            case 2 : while(!stationQueue.isEmpty()) {
                Station currentStation = stationQueue.poll();
                //visiting each connection(EDGE) existing in u
                for (LinkStations connect = currentStation.adjList; connect != null; connect = connect.next) {
                    float price = connect.getPrice();
                    float priceTroughCurrentStation = currentStation.minDistance + price;

                    if (priceTroughCurrentStation < g.allStations[connect.getStationNum()].minDistance) {
                        // stationQueue.remove(allStations[connect.stationNum]);
                        g.allStations[connect.getStationNum()].minDistance = priceTroughCurrentStation;
                        g.allStations[connect.getStationNum()].previous = currentStation;
                        stationQueue.add(g.allStations[connect.getStationNum()]);
                    }
                }
            }
        }
    }
    
    
    public List<Station> getShortestPathTo(int target){

        List<Station> path = new ArrayList<Station>();
        for(Station station = g.allStations[target]; station != null ; station =  station.previous){
            path.add(station);
        }
        Collections.reverse(path);
        return path;
    }
    
    public int indexForStation(String name){
        for(int s=0; s<g.allStations.length; s++){
            if(g.allStations[s].getName().equals(name)){
                return s;
            }
        }
        return -1;
    }
   

//printeaza traseul obtinut in urma filtrelor . 
    public void printConnections(List<Station> pathStation){
    	int filtru =999;
        for(Station s : pathStation){
        	for(LinkStations connection = s.adjList ; connection != null ; connection = connection.next){
                if(pathStation.contains(g.allStations[connection.getStationNum()])){
                	if(filtru != connection.getStationNum()){
                		System.out.println("" + s.getName() + connection.toString() + g.allVehicles[connection.getVehicleIndex()].toString() + " --> "
                					+g.allStations[connection.getStationNum()].getName());
                	}
                }
            }
        	filtru = indexForStation(s.getName());
        }
    }	
}