public class Main {
    public static void main(String[] args) throws Exception {

        RoundRobinScheduler scheduler = new RoundRobinScheduler(3);

        scheduler.addProcess(new Process(1, 0, 10));
        scheduler.addProcess(new Process(2, 0, 4));
        scheduler.addProcess(new Process(3, 0, 7));
        scheduler.addProcess(new Process(4, 0, 6));

        scheduler.execute();

    }
}
