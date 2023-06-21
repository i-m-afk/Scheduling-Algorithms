
/**
 *
 * @author risha
 */
import java.util.ArrayList;

public class RoundRobin extends FCFS {

    private int quantum;

    public RoundRobin(Process[] processes, int quantum) {
        super(processes);
        this.quantum = quantum;
    }

    @Override
    protected void apply() {
        GrantChart grantChart = new GrantChart();
        ArrayList<Process> list;
        time = 0;
        totalWaitingTime = 0;
        averageWaitingTime = 0;
        sort();
        list = new ArrayList<>(sortedProcesses);

        int index = 0;
        while (list.size() > 0) {
            Process process = list.get(index);
            grantChart.schedule(process);
            int processed = Math.min(process.remainingBurstTime, quantum);
            grantChart.passTime(processed);
            process.remainingBurstTime -= processed;
            if (process.remainingBurstTime == 0) {
                list.remove(index);
                if (list.size() > 0) {
                    index = index % list.size();
                }
            } else {
                index = (index + 1) % list.size();
            }
        }
        grantChart.calculateWaitingTime();
        grantChart.print();
        averageWaitingTime = grantChart.getTotalWaitingTime() / processes.length;
    }
}
