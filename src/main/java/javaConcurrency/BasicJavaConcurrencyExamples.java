package javaConcurrency;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;


/**
 * This class contains basic Java concurrency features like Threads, Runnables, Executors, Schedulers, fork-joins etc
 * with some basic info about each of them.
 *
 * @TODO Add implementation of RecursiveAction
 */
public class BasicJavaConcurrencyExamples {

    class simpleThread extends Thread {
    }

    class simpleThread2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 300; i++) {
                System.out.println("simpleThread2, iteration: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class simpleThread3 implements Callable<String> {

        @Override
        public String call() {
            for (int i = 0; i < 300; i++) {
                System.out.println("simpleThread3, iteration: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "I am done.";
        }
    }

    /**
     * Calling different types of thread executors
     */
    public static void example1() {
        // run Thread class
        System.out.println("Starting Thread instance");
        (new Thread(new BasicJavaConcurrencyExamples().new simpleThread(), "Thread1")).start();
        System.out.println("Finished 1st task.");

        System.out.println("Starting Runnable instance by invoking EXECUTE() command ");
        // run Runnable interface
        Executors.newSingleThreadExecutor().execute(new BasicJavaConcurrencyExamples().new simpleThread2());
        System.out.println("Finished 2nd task.");

        System.out.println("Starting Runnable instance by invoking SUBMIT() command");
        // run Runnable interface - it will run task and proceed
        Executors.newSingleThreadExecutor().submit(new BasicJavaConcurrencyExamples().new simpleThread2());

        //run Callable interface - it will return sample value
        Future<String> result = Executors.newSingleThreadExecutor().submit(new BasicJavaConcurrencyExamples().new simpleThread3());

        try {
            String status = result.get(5, TimeUnit.SECONDS);
            System.out.println("Finished. Status is " + status);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
            System.out.println("Nie dziala. :)");
        }

        // no need to create class, anonymous class can be used
        Future<String> anResult = Executors.newSingleThreadExecutor().submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Anonymous callable. Iteration: " + i);
            }
            return "I am done";
        });

        try {
            String status2 = anResult.get(5, TimeUnit.SECONDS);
            System.out.println("Finished anonymous callable. Status: " + status2);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            System.out.println("Nie dziala. :)");
        }
    }

    /**
     * Sample RecursiveAction
     */
    class forkjoin extends RecursiveAction {

        private int start;
        private int end;
        private Double[] weights;

        public forkjoin(Double[] weights, int start, int end) {
            this.start = start;
            this.end = end;
            this.weights = weights;
        }

        @Override
        protected void compute() {

            // if number of elements are small - do the real work
            // in this case - assign random weights to elements in array
            if (end - start <= 3) {
                for (int i = start; i < end; i++) {
                    weights[i] = (double) new Random().nextInt(100);
                    System.out.println("Weight: " + i);
                }
                // otherwise, split task and run them independently
            } else {
                int middle = (start + end) >>> 1;
                System.out.println("Start=" + start + ",middle=" + middle + ",end" + end);
                invokeAll(new forkjoin(weights, start, middle),
                        new forkjoin(weights, middle, end));
            }
        }
    }

    public static void example2_forkjoins() {
        Double[] weights = new Double[10];

        // create new task
        ForkJoinTask<?> task = new BasicJavaConcurrencyExamples().new forkjoin(weights, 0, weights.length);

        // create new execution pool
        ForkJoinPool pool = new ForkJoinPool();

        // send task to pool to be invoked, thanks to it all tasks are running in separater thread
        // and are executed within particular thread pool, means you are able to send multiple tasks
        // to the same pool e.g. you can add ForkJoinTask and run Runnable task
        // execution pool has also some fields where you can determine how much resources you
        // are going to use
        pool.invoke(task);
        pool.submit(() -> System.out.println("Sneaky thread task.")); // line should be visible somewhere in the middle during the execution
        System.out.println("Parallelism method:" + pool.getParallelism()); // 4 in my PC
        System.out.println("Pool size: " + pool.getPoolSize()); // 4 in my PC

        Arrays.asList(weights).forEach(d -> System.out.println(d.intValue() + " "));
    }

    public static void main(String... args) {
        example1();
        example2_forkjoins();
    }

}