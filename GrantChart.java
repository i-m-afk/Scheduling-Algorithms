
/**
 *
 * @author risha
 */
import java.util.*;

public class GrantChart {

    private ArrayList<Part> parts;
    private SortedSet<Process> processes;
    private int time;
    private int totalWaitingTime;

    public GrantChart() {
        parts = new ArrayList<>();
        processes = new TreeSet<>(new IdComparator());
        time = 0;
    }

    public GrantChart(Process[] processes) {
        parts = new ArrayList<>();
        this.processes = new TreeSet<>(new IdComparator());
        time = processes[0].getArrivingTime();
        for (Process process : processes) {
            if (process.getArrivingTime() < time) {
                time = process.getArrivingTime();
            }
        }
    }

    public void schedule(Process process) {
        int last = parts.size() - 1;
        if (last >= 0 && parts.get(last).getProcess() != process) {
            parts.get(last).complete(time);
        }
        if (last == -1 || parts.get(last).getProcess() != process) {
            parts.add(new Part(process, time));
        } else {
            parts.get(last).complete(time + 1);
        }
        time++;
    }

    public void passTime(int length) {
        time += length - 1;
        parts.get(parts.size() - 1).complete(time);
    }

    public boolean isProcessStarted(Process process) {
        for (Part part : parts) {
            if (part.getProcess() == process) {
                return true;
            }
        }
        return false;
    }

    public void calculateWaitingTime() {
        totalWaitingTime = 0;
        for (Part part : parts) {
            processes.add(part.getProcess());
        }
        for (Process process : processes) {
            Part previous = null;
            int begin;
            int waitingTime = 0;
            for (Part part : parts) {
                if (part.getProcess() == process) {
                    if (previous == null) {
                        previous = part;
                        begin = part.getStart();
                        part.getProcess().setStartingTime(begin);
                        waitingTime = part.getProcess().getStartingTime() - part.getProcess().getArrivingTime();
                    } else {
                        waitingTime += part.getStart() - previous.getEnd();
                        previous = part;
                    }
                }
            }
            process.setWaitingTime(waitingTime);
            totalWaitingTime += waitingTime;
        }
    }

    public void print() {
        System.out.println("\nGrant Chart");
        System.out.println("-----------");
        for (Part part : parts) {
            System.out.printf("P%-3d ", part.getProcess().getId());
        }
        System.out.println();
        int i = 0;
        for (Part part : parts) {
            System.out.printf("%-3d  ", part.getStart());
            if (i == parts.size() - 1) {
                System.out.printf("%-3d  ", part.getEnd());
            }
            i++;
        }
        System.out.println();
    }

    public double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public int getTime() {
        return time;
    }
}
