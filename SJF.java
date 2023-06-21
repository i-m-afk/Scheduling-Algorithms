
/**
 *
 * @author risha
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class SJF extends Algorithm {

    public SJF(Process[] processes) {
        super(processes);
    }

    @Override
    protected void sort() {
        sortedProcesses = new TreeSet<>(new ShortestJobComparator());
        sortedProcesses.addAll(Arrays.asList(processes));
    }

    @Override
    protected void apply() {
        List<Process> list = new ArrayList<>(Arrays.asList(processes));
        GrantChart grantChart = new GrantChart();
        time = 0;
        totalWaitingTime = 0;
        averageWaitingTime = 0;
        Process process = null;

        while (!list.isEmpty()) {
            process = nextProcess(list, time);
            grantChart.schedule(process);
            grantChart.passTime(process.getBurstTime());
            list.remove(process);
            time++;
        }
        grantChart.calculateWaitingTime();
        grantChart.print();
        averageWaitingTime = grantChart.getTotalWaitingTime() / processes.length;
    }

    private Process nextProcess(List<Process> list, int time) {
        Process process = list.get(0);
        for (int i = 1; i < list.size(); ++i) {
            Process currentProcess = list.get(i);
            if ((currentProcess.getArrivingTime() <= time && currentProcess.getBurstTime() < process.getBurstTime())
                    || (currentProcess.getArrivingTime() <= time && process.getArrivingTime() > time)) {
                process = currentProcess;
            }
        }
        return process;
    }

    @Override
    public void printResult() {
        if (time == 0) {
            apply();
        }
        System.out.println();
        System.out.println(Process.getHeader());

        for (Process process : processes) {
            System.out.println(process);
        }

        System.out.printf("%s Average waiting time: %.2f\n", getName(), averageWaitingTime);
    }
}
