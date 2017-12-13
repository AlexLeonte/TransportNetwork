package platform;

import java.util.Comparator;

public class stationComparableByDistance implements Comparator<Station> {
//comparator folosit pentru cautarea celui mai scurt drum in functie de distance/time/price in priority queue.
    @Override
    public int compare(Station o1, Station o2) {
        return Float.compare(o1.minDistance, o2.minDistance);
    }

}