import java.util.concurrent.*;

public class ThreadPoolDemo {

    Semaphore semaphore1 = new Semaphore(1);
    Semaphore semaphore2 = new Semaphore(0);
    int n = 10;

    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(10);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                3,
                6,
                60,
                TimeUnit.SECONDS,
                blockingQueue,
                new ThreadPoolExecutor.AbortPolicy());

        PrintBar pb = new PrintBar();
        PrintFoo pf = new PrintFoo();
        ThreadPoolDemo tpd = new ThreadPoolDemo();


        threadPool.execute(new Thread(){
            @Override
            public void run() {
                try {
                    tpd.foo(pb);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadPool.execute(new Thread(){
            @Override
            public void run() {
                try {
                    tpd.bar(pf);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadPool.shutdown();
    }


    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphore1.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphore2.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphore2.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphore1.release();
        }
    }
}

class PrintBar implements Runnable{
    @Override
    public void run() {
        System.out.print("Bar");
    }
}

class PrintFoo implements Runnable{
    @Override
    public void run() {
        System.out.print("Foo");
    }
}
