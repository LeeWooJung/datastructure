import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class RoundRobinScheduler {
    private Queue<Process> processQueue;
    private int timeQuantum; // 시간 할당 량.

    public RoundRobinScheduler(int timeQuantum) {
        this.processQueue = new LinkedList<>();
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(Process process) {
        processQueue.add(process);
    }

    /*
     * Round Robin alogorithm
     * - 선점 스케줄링 알고리즘.
     * - FIFO Queue 순서대로 실행.
     * - 시간 할당량(timeQuantum)만큼만 실행 가능.
     * - 시간 할당량을 다 채우면, Queue의 마지막에 offer.
     * - CPU를 독점하지 않음, 대화형 운영체제에 유용
     * - 시간 할당량이 너무 크면 FCFS 스케줄링과 같아짐.
     * - 시간 할당량이 너무 작으면 문맥 교환(Context Switching)에 따른 오버헤드 증가.
     */

    public void execute() {
        int time = 0;
        List<Process> completedProcesses = new LinkedList<>();

        while(!processQueue.isEmpty()) {
            Process process = processQueue.poll();
            int remainingTime = process.getRemainingTime();

            int waitingTime = 0;

            if(remainingTime > timeQuantum) {
                time += timeQuantum;
                waitingTime = timeQuantum;
                remainingTime -= timeQuantum;
                process.setRemainingTime(remainingTime);
                System.out.println("Process " + process.getPid() + " executed for " + timeQuantum + " units. Remaining burst time: " + remainingTime);
            } else {
                time += remainingTime;
                waitingTime = remainingTime;
                System.out.println("Process " + process.getPid() + " executed for " + remainingTime + " units. Process completed.");
                remainingTime = 0;
                process.setCompletionTime(time);
                process.setRemainingTime(remainingTime);
            }

            // update waiting time of process in Queue 
            for(Process p: processQueue) {
                p.setWaitingTime(p.getWaitingTime() + waitingTime);
            }

            if(remainingTime > 0) {
                processQueue.add(process);
            } else {
                completedProcesses.add(process);
            }
        }

        System.out.println("Total time: " + time + " units");

        calculateAndPrintTimes(completedProcesses);
    }

    private void calculateAndPrintTimes(List<Process> completedProcesses) {

        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int count = completedProcesses.size();

        for(Process process: completedProcesses) {
            int turnaroundTime = process.getCompletionTime() - process.getStartTime();
            process.setTurnaroundTime(turnaroundTime);
            totalTurnaroundTime += turnaroundTime;

            int waitingTime = turnaroundTime - process.getBurstTime();
            process.setWaitingTime(waitingTime);
            totalWaitingTime += waitingTime;

            System.out.println("Process " + process.getPid());
            System.out.println("Start Time = " + process.getStartTime() + ", Completed Time: " + process.getCompletionTime() + ": Waiting Time = " + waitingTime + ", Turnaround Time = " + turnaroundTime);
        }

        double avgWaitingTime = (double) totalWaitingTime / count;
        double avgTurnaroundTime = (double) totalTurnaroundTime / count;

        System.out.println("Average Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}
