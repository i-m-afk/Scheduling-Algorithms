
/**
 *
 * @author rishav
 */
import java.util.Comparator;

public class PriorityComparator implements Comparator<Process> {

    @Override
    public int compare(Process o1, Process o2) {
        int diff = o2.getPriority() - o1.getPriority();
        if (diff == 0) {
            diff = o1.getArrivingTime() - o2.getArrivingTime(); // Lower arrival time has higher priority
        }
        if (diff == 0) {
            diff = o1.getId() - o2.getId(); // Compare by ID if priorities and arrival times are equal
        }
        return diff;
    }
}
