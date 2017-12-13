package platform;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;
import java.sql.SQLException;  
import com.mysql.jdbc.Statement;
  //Aceasta clasa creeaza 1.vector cu obicete - allVehicles 2.vector cu obiecte allStations 3.creeaza conexiuni/legaturi itnre statt
public class DBConnect {
	
	
	public int constructArrayStations(Station[] allStations,String host, String uName, String uPass){
	//i have no idea now how to use constructArray function to construct array stations and array lines/vehicles with same code
	   //so i will use 2 functions ...with only different parameter : array 1.Station and another 2.Vehicles/Lines
		try{
			
	        Connection con = DriverManager.getConnection( host, uName, uPass );
	        Statement stmt = (Statement) con.createStatement();
	       // String sql = "SELECT MAX(StationID) FROM stations";
	        //ResultSet rs = stmt.executeQuery(sql);
	        //rs.next();
	        //String id = rs.getString("MAX(StationID)");
	        //allStations = new Station[Integer.parseInt(id)];
	        String sql = "SELECT * FROM stations";
	        ResultSet rs = stmt.executeQuery(sql);
	
	      
	        int s=0; // index 
	        while (rs.next()) {
				allStations[s] = new Station(rs.getString("StationName"),null);
		//		System.out.println("Station "+allStations[s].getName());
				s=s+1;
	        }
	         }
	         catch(SQLException err){
	        System.out.println(err.getMessage());
	    }
	    return 0;
	}
	
	public int constructArrayLines(Vehicles[] allVehicles,String host, String uName, String uPass){
		//i have no idea now how to use constructArray function to construct array stations and array lines/vehicles with same code
		   //so i will use 2 functions ...with only different parameter : array 1.Station and another 2.Vehicles/Lines
			try{
		        
		        Connection con = DriverManager.getConnection( host, uName, uPass );
		        Statement stmt = (Statement) con.createStatement();
		     //   String sql = "SELECT MAX(LineID) FROM vehicles";
		       // ResultSet rs = stmt.executeQuery(sql);
		        //rs.next();
		        //String id = rs.getString("MAX(LineID)");
		        //allVehicles = new Vehicles[Integer.parseInt(id)];

		       String sql = "SELECT * FROM vehicles";
		       ResultSet rs = stmt.executeQuery(sql);
		
		        int v=0; // index 
		        while (rs.next()) {
					allVehicles[v] = new Vehicles(rs.getString("LineName"),rs.getString("Type"));
				//	System.out.println("Vehicle "+ allVehicles[v].getName());
					v=v+1;
		        }
		         }
		         catch(SQLException err){
		        System.out.println(err.getMessage());
		    }
		    return 0;
		}
	
	public int constructConnections(Station[] allStations,Vehicles[] allVehicles,String host, String uName, String uPass){
			
try{
	        
	        Connection con = DriverManager.getConnection( host, uName, uPass );
	        Statement stmt = (Statement) con.createStatement();
	     
	        
	        String sql = "SELECT * FROM connections";
	        ResultSet rs = stmt.executeQuery(sql);
	        int s1,s2,v;
	        float distance = 15;
            float price = 10;
            float slowing = 1;
            float lat1=0;
            float lng1=0;
            float lat2 =0;
            float lng2 = 0;
            
            
            rs.next();            
            int LineID = rs.getInt("LineID");
            rs.beforeFirst();
            int c =0 ;
            while (rs.next()) {
				c++;
            	if(LineID != rs.getInt("LineID")){
            		LineID = rs.getInt("LineID");
            		continue;
            	}
            	lat1 = rs.getFloat("Lat"); 
            	lng1 = rs.getFloat("Lng");
            	s1 = indexForStations(rs.getString("RawStationName"),allStations);
            	if(rs.next()){
            		s2 = indexForStations(rs.getString("RawStationName"),allStations);
    				v = indexForVehicles(rs.getString("LineName"),allVehicles);
    				lat2 = rs.getFloat("Lat"); 
                	lng2 = rs.getFloat("Lng");
             	   	distance =  distFrom(lat1,lng1,lat2,lng2) ;

    	            allStations[s1].adjList =   new LinkStations(s2,allStations[s1].adjList,distance,v,allVehicles[v].getSpeed(),price,slowing);
    	            LineID = rs.getInt("LineID");
    	            
                	}

            	
				if(c == 500)
					break;
	        } 
	         }
	         catch(SQLException err){
	        System.out.println(err.getMessage());
	         }
	
				return 0;
			
		}
	//functie care preia un string si il cauta in vectorul de obiecte cu statii dupa care returneaza indicele
	   public int indexForStations(String name,Station[] allStations){
	        for(int s=0; s<allStations.length; s++){
	            if(allStations[s].getName().equals(name)){
	                return s;
	            }
	        }
	        return -1;
	    }
	    	
	//functie care preia un string -> cauta in vector stringul respectiv si intoarce indicele acestuia . (vectorul este compus din obiecte de tip vehicle)
	    int indexForVehicles(String pname,Vehicles[] allVehicles){
	        for(int v=0; v<allVehicles.length; v++){
	            if(allVehicles[v].getName().equals(pname)){
	                return v;
	            }
	        }
	        return -1;
	    }
	    
	    public  float distFrom(float lat1, float lng1, float lat2, float lng2) {
	        double earthRadius = 6371; //kilometers
	        double dLat = Math.toRadians(lat2-lat1);
	        double dLng = Math.toRadians(lng2-lng1);
	        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	                   Math.sin(dLng/2) * Math.sin(dLng/2);
	        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	        float dist = (float) (earthRadius * c);

	        return dist;
	        }
	    
	    
	    
}
 