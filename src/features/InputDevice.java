package features;

/**
 * Created by awesome on 23.11.2014.
 */
import java.util.Scanner;



public class InputDevice {
    Scanner in = new Scanner(System.in);
    String stations;
    private int option;

   public InputDevice(String allStations){
        this.stations = allStations;
    }

   public int getOperation(){
        showMenu();
        System.out.println("Your option is: ");
        option = in.nextByte();
        
        return option;
    }

    void showMenu(){
        System.out.println(this.stations + "\n");
        System.out.println("Choose 1-5");
        System.out.print(" 1.Show all connections \n 2.Show the shortest path between: \n 3.Show the shortest path //incomplet \n5.Exit \n");
    }

   public int typePath(){
        int optiune;
        System.out.println("0.Small distance / 1.Faster way / 2.Cheap way");
        System.out.println("Choose one : ");
        optiune = in.nextInt();

        return optiune;
    }
    
}