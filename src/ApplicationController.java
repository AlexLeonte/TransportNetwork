import java.util.List;
import features.Functionality;
import platform.Station;
import java.util.Scanner;
import features.InputDevice;
/**
 * Created by awesome on 22.11.2014.
 */

 class ApplicationController {

    ApplicationController(){
    }

    void startProcessing() {
        Functionality f = new Functionality();
        InputDevice inputDevice = new InputDevice(f.returnStations());
        int o;
        while(true){
            o = inputDevice.getOperation();

            switch (o){
                case 1: System.out.println(f.printAllConections());
                		//graph.allStations[0].allSchedule[0].printThisSchedule();
                        break;
                case 2:Scanner scanner = new Scanner(System.in);
                    int isource;
                    int itarget;
                    int oway;  //possibility small way / fast way / cheap way.
                    System.out.println("Type index of  current station :");
                    isource = (scanner.nextInt());
                    System.out.println("Type index of target station :");
                    itarget  = scanner.nextInt();
                    oway = inputDevice.typePath();

                    f.computePaths(isource, oway); //coumpute paths by oway = { 0 - distance / 1 - time / 2 - money}
                    List<Station> path = f.getShortestPathTo(itarget);


                    f.printConnections(path);
                    break;
                case 3:
                	Scanner sc = new Scanner(System.in);
                	String source,target;
                	System.out.println("Type your station`s name");
                	source = (sc.nextLine());
                	System.out.println("Type your target station");
                	target = (sc.nextLine());
                	
                	System.out.println("Working... \n am procesul 2 pesntru aceasta functie - insa cand am creat"
                			+ "procesul 2 , incapeau toate statiile pe screen\n");
                	
                	
                    break;
                case 5: System.out.println("ByeBye");
                        System.exit(0);
            }
        }
    }


    public static void main(String[] args){
        ApplicationController applicationController = new ApplicationController();
        applicationController.startProcessing();
    }
}
