
/**
 *
 * @author risha
 */
public class Main {

    public static void main(String[] args) {
        int[] bt = { 5, 3, 8, 6 }; // Burst time
        int[] at = { 0, 1, 2, 3 }; // Arrival time
        int[] pp = { 1, 2, 1, 3 }; // Process priority

        Process[] processes = new Process[bt.length];

        // Create and initialize processes with their attributes
        for (int i = 0; i < processes.length; i++) {
            processes[i] = new Process(i + 1, bt[i], at[i]) {
            };
            processes[i].setPriority(pp[i]);
        }

        // Run scheduling algorithms and print the results
        FCFS fcfs = new FCFS(processes);
        fcfs.printResult();

        SJF sjf = new SJF(processes);
        sjf.printResult();

        SJFPreemptive sjfPreemptive = new SJFPreemptive(processes);
        sjfPreemptive.printResult();

        RoundRobin roundRobin = new RoundRobin(processes, 2); // Time quantum: 2
        roundRobin.printResult();

        Priority priority = new Priority(processes);
        priority.printResult();
    }
}
