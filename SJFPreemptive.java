
/**
 *
 * @author risha
 */
import java.util.ArrayList;
import java.util.Arrays;

public class SJFPreemptive extends SJF {

    public SJFPreemptive(Process[] processes) {
        super(processes);
    }

    @Override
    protected void apply() {
        time = 0;
        totalWaitingTime = 0;
        totalWaitingTime = 0;
        ArrayList<Process> list = new ArrayList<>(Arrays.asList(processes));
        GrantChart grantChart = new GrantChart();

        while (!list.isEmpty()) {
            Process process = nextProcess(list, time);
            grantChart.schedule(process);
            process.remainingBurstTime--;
            if (process.remainingBurstTime == 0) {
                list.remove(process);
            }
            time++;
        }
        grantChart.calculateWaitingTime();
        grantChart.print();
        averageWaitingTime = grantChart.getTotalWaitingTime() / processes.length;
    }

    protected Process nextProcess(ArrayList<Process> list, int time) {
        Process process = list.get(0);
        for (int i = 1; i < list.size(); ++i) {
            Process currentProcess = list.get(i);
            if ((currentProcess.getArrivingTime() <= time && currentProcess.remainingBurstTime < process.remainingBurstTime)
                    || (currentProcess.getArrivingTime() <= time && process.getArrivingTime() > time)) {
                process = currentProcess;
            }
        }
        return process;
    }

    @Override
    protected String getName() {
        return "SJF Preemptive";
    }
}
