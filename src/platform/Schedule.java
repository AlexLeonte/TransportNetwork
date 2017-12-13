package platform;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
public class Schedule {
	
	List<Date> orar = new ArrayList<Date>();
	int indexForVehicle;
	
	Schedule(List<Date> porar, int pindexForVehicle){
		for(Date x : porar){
			orar.add(x);
		}
		this.indexForVehicle = pindexForVehicle;
	}
	
	public Date nextTime(Date actual){
		Date answer = new Date();
		for(Date x : orar){
			if(x.after(actual)){
				continue;
			}
			else{
				answer = new Date(x.getTime());
			}
		}
	return answer;
	}
	
	public void printThisSchedule(){
		for(Date d : orar){
			System.out.println(d);
		}
	}
	
	
	

}