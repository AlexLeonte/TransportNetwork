package platform;

/**
 * Created by awesome on 17.11.2014.
 */

 public class Graph{

    public  Station[] allStations;   //this array contains all stations example Complex, Centru. int indexForName(String name) .
    public  Vehicles[] allVehicles;   //this array contains all machines from our platform example Expres 2, Tram 4 , ...

    public Graph() {
   
    	//mysal server pe calculator am 3 table 1 cu statii 2 cu linii si al 3 ...cel de la dumneavoastra care 
    	//l-am folosit sa construiesc conexiunile .
        String host = "jdbc:mysql://127.0.0.1:3306/transport"; 
        String uName = "root";
        String uPass = "password";  //yea, this is my password :)) 
        DBConnect dbc = new DBConnect();
        allStations = new Station[579];

        dbc.constructArrayStations(allStations,host,uName,uPass); // RED ALL STATIONS 
        allVehicles = new Vehicles[50];
        
        dbc.constructArrayLines(allVehicles, host, uName, uPass); //READ ALL LINES/VEHICLES = Bus, Tram 
        
     

        dbc.constructConnections(allStations,allVehicles,host, uName, uPass);
                 
        
    }


}