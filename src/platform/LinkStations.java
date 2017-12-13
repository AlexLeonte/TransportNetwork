package platform;

/**
 * Created by awesome on 17.11.2014.
 */
import java.math.BigDecimal;

public class LinkStations {   //create e connection between 2 Stations .
    private int stationNum;  //for example this connection is between the current station and adjLists[stationNum]
    public LinkStations next;
    private float distance;
    private float time;
    private int vehicleindex;  //vehicle index
    private float price;
    
    public LinkStations(int pstationNum, LinkStations pconnection, float pdistance, int pvehicleindex, float vehiclespeed, float pprice, float pslowing){

        this.stationNum = pstationNum;
        this.next = pconnection;                     //without this.
        this.distance = pdistance;
        this.vehicleindex = pvehicleindex;
        this.price = pprice;

      setTime(vehiclespeed,pslowing);
    }

   /*aceasta functie calculeaza timpul deplasarii mijlocului de transport intre doua statii 
    * distance / speed = timp  ------------ la care am adaugat un parametru "slowing" care are rolu de a micosra timpu - 
    * acesta este si el citit din fisier . (daca slowing = 1 timpul nu se schimba )*/

   public void setTime(float speed,float slowing){
       float aux= distance/speed;
       aux = aux + (aux - (aux/slowing));

       BigDecimal bd = new BigDecimal(aux);
       bd = bd.setScale(2 , BigDecimal.ROUND_HALF_UP);

       this.time =bd.floatValue();
    }


    public int getStationNum() {
        return stationNum;
    }

    public int getVehicleIndex() {
        return vehicleindex;
    }

    public float getTime() {
        return time;
    }

    public float getDistance() {
        return this.distance;
    }

    public float getPrice() {
        return price;
    }

    public String toString(){
        return " distance="+getDistance()+ " time="+getTime()+ " price="+getPrice()+" ";
    }

}