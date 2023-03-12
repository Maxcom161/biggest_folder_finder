public class TestThread extends Thread {

    private int numberThread;

    public TestThread(int numberThread){
        this.numberThread = numberThread;
    }

    @Override
    public void run() {
        for(;;) {
            System.out.println(numberThread);
        }
    }
}
