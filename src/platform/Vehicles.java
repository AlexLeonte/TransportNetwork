package platform;
/**
 * Created by awesome on 17.11.2014.
 */

public class Vehicles implements Comparable<Vehicles> {
    private String name;
    private String type;
    private float speed;

    Vehicles(String pname, String ptype){
        this.name = pname;
        this.type = ptype;

        if(this.type.equals("bus")|| this.type.equals("Bus")){
            this.speed = 50;
        }
        else{
            if(this.type.equals("Tram") || this.type.equals("tram")){
                this.speed = 25;
            }
            else{
                System.out.println("ERROR : TYPE UNKNOWN" + this.type);
            }
        }

    }

    public String getType() {
        return type;
    }

    public float getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return " Vehicle{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", speed=" + speed +
                '}';
    }

    @Override
    public int compareTo(Vehicles o) {

        int x = this.name.compareTo(o.getName());
        return x;
    }
}