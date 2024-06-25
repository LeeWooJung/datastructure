public class Process {

    private int pid;
    private int burstTime; // 실행에 필요한 시간
    private int remainingTime; // 실행까지 남은 시간
    private int waitingTime; // ready Queue에서 Process가 실행되기를 기다리는 시간.
    private int startTime; // process 실행 요청 시간
    private int completionTime; // Process 완료 시간.
    private int turnaroundTime; // completionTime - startTime(완료까지 걸리는 시간)

    public Process(int pid, int startTime, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.startTime = startTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.completionTime = 0;
    }

    // getter
    public int getPid() {
        return pid;
    }
    
    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    // setter
    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }
}
