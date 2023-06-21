
/**
 *
 * @author rishav
 */
import java.util.TreeSet;

public class Priority extends Algorithm {

    private GrantChart grantChart;

    public Priority(Process[] processes) {
        super(processes);
    }

    @Override
    protected void sort() {
        if (sortedProcesses == null) {
            sortedProcesses = new TreeSet<>(new PriorityComparator());
        } else {
            sortedProcesses.clear();
        }
        for (Process process : processes) {
            if (process.getArrivingTime() <= time
                    && !grantChart.isProcessStarted(process)) {
                sortedProcesses.add(process);
            }
        }
    }

    @Override
    protected void apply() {
        grantChart = new GrantChart(processes);
        time = 0;
        totalWaitingTime = 0;
        averageWaitingTime = 0;

        sort();
        do {
            grantChart.schedule(sortedProcesses.first());
            grantChart.passTime(sortedProcesses.first().getBurstTime());
            time = grantChart.getTime();
            sort();
        } while (sortedProcesses.size() > 0);
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
        System.out.println(Process.getHeader(true));

        for (Process process : processes) {
            System.out.println(process.toStringWithPriority());
        }

        System.out.printf("%s Average waiting time: %.2f\n",
                getName(), averageWaitingTime);
    }
}
