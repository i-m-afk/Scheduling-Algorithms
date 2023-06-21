
/**
 *
 * @author risha
 */
import java.util.SortedSet;

public abstract class Algorithm {

    protected Process[] processes;
    protected SortedSet<Process> sortedProcesses;
    protected double totalWaitingTime;
    protected double averageWaitingTime;
    protected int time;

    public Algorithm(Process[] processes) {
        this.processes = processes;
        for (Process process : processes) {
            process.reset();
        }
        time = 0;
    }

    protected abstract void sort();

    /**
     * calculate waiting time algorithm
     */
    protected abstract void apply();

    public abstract void printResult();

    protected String getName() {
        return getClass().getSimpleName();
    }
}
