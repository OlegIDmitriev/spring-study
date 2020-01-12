package intercept;

public class WorkerBean {
    public void doSomeWotk(int noOfTimes) {
        for (int x=0; x<noOfTimes; x++) {
            work();
        }
    }

    private void work() {
        System.out.print("");
    }
}
