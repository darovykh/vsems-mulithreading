import java.io.IOException;

public class Main {

    static class MyThread extends Thread {
        private String date;
        private String type;

        public MyThread(String date, String type) {
            this.date = date;
            this.type = type;
        }

        @Override
        public void run(){
            LogsProcessor lp = new LogsProcessor(date, type);
            try {
                lp.writeLogsWithContentToFile("logs.txt", lp.getType() + lp.getDate() + ".log");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String [] args) throws IOException {
        long consistentTime = 0;
        System.out.println("Run in consistent way:");
        LogsProcessor lp1 = new LogsProcessor("2019-10-13", "ERROR");
        LogsProcessor lp2 = new LogsProcessor("2019-10-14", "ERROR");
        LogsProcessor lp3 = new LogsProcessor("2019-10-15", "ERROR");
        LogsProcessor lp4 = new LogsProcessor("2019-10-16", "ERROR");
        LogsProcessor lp5 = new LogsProcessor("2019-10-17", "ERROR");
        consistentTime += lp1.writeLogsWithContentToFile("logs.txt", lp1.getType() + lp1.getDate() + ".log");
        consistentTime += lp2.writeLogsWithContentToFile("logs.txt", lp2.getType() + lp2.getDate() + ".log");
        consistentTime += lp3.writeLogsWithContentToFile("logs.txt", lp3.getType() + lp3.getDate() + ".log");
        consistentTime += lp4.writeLogsWithContentToFile("logs.txt", lp4.getType() + lp4.getDate() + ".log");
        consistentTime += lp5.writeLogsWithContentToFile("logs.txt", lp5.getType() + lp5.getDate() + ".log");
        System.out.println("General consistent way execution time is: " + consistentTime);
        System.out.println("---------------------------------------------------------------------");

        System.out.println("Run in parallel way: ");
        new MyThread("2019-10-13", "ERROR").start();
        new MyThread("2019-10-14", "ERROR").start();
        new MyThread("2019-10-15", "ERROR").start();
        new MyThread("2019-10-16", "ERROR").start();
        new MyThread("2019-10-17", "ERROR").start();
    }
}
