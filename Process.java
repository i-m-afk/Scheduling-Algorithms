
/**
 *
 * @author risha
 */
public class Process {

    private int id;
    private int burstTime;
    private int arrivingTime;
    private int startingTime;
    private int waitingTime;
    private int priority;
    int remainingBurstTime;

    public Process(int id, int burstTime, int arrivingTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivingTime = arrivingTime;
        remainingBurstTime = burstTime;
        waitingTime = -1;
        priority = 0;
    }

    public int getId() {
        return id;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getArrivingTime() {
        return arrivingTime;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getWaitingTime() {
        if (waitingTime < 0) {
            return startingTime - arrivingTime;
        }
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public static String getHeader() {
        return getHeader(false);
    }

    public static String getHeader(boolean withPriority) {
        return "Process\tBurst\tArriving\tStarting\tWaiting" + (withPriority ? "\tPriority" : "");
    }

    @Override
    public String toString() {
        return String.format("P%-6d\t%-5d\t%-8d\t%-8d\t%-6d", id, burstTime, arrivingTime, startingTime, getWaitingTime());
    }

    public String toStringWithPriority() {
        return String.format("%s\t%-5d", this, priority);
    }

    public void reset() {
        remainingBurstTime = burstTime;
        startingTime = -1;
        waitingTime = -1;
    }
}
