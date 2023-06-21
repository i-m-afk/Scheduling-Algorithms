
/**
 *
 * @author risha
 */
import java.util.Arrays;
import java.util.TreeSet;

public class FCFS extends Algorithm {

    public FCFS(Process[] processes) {
        super(processes);
    }

    @Override
    protected void sort() {
        sortedProcesses = new TreeSet<>(new ArriveTimeComparator());
        sortedProcesses.addAll(Arrays.asList(processes));
    }

    @Override
    protected void apply() {
        GrantChart grantChart = new GrantChart();
        time = 0;
        totalWaitingTime = 0;
        averageWaitingTime = 0;
        sort();

        for (Process process : sortedProcesses) {
            grantChart.schedule(process);
            grantChart.passTime(process.getBurstTime());
        }
        grantChart.calculateWaitingTime();
        grantChart.print();
        averageWaitingTime = grantChart.getTotalWaitingTime() / processes.length;
    }

    @Override
    public void printResult() {
        if (sortedProcesses == null) {
            apply();
        }
        System.out.println();
        System.out.println(Process.getHeader());

        for (Process process : sortedProcesses) {
            System.out.println(process);
        }

        System.out.printf("%s Average waiting time: %.2f\n", getName(), averageWaitingTime);
    }
}
