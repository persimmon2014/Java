import java. util. *;
import java. text. *;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Task implements Runnable {
    long n;
    String id;
    private long fib(long n) {
        if (n == 0)
            return 0L;
        if (n == 1)
            return 1L;
        return fib(n - 1) + fib(n - 2) ;
    }
    public Task(long n, String id) {
        this. n = n;
        this. id = id;
    }
    public void run( ) {
        Date d = new Date( );
        DateFormat df = new SimpleDateFormat("HH: mm: ss: SSS") ;
        long startTime = System. currentTimeMillis( );
        d. setTime(startTime) ;
        System. out. println("Starting task " + id + " at " + df. format(d) ) ;
        fib(n) ;
        long endTime = System. currentTimeMillis( );
        d. setTime(endTime) ;
        System. out. println("Ending task " + id + " at " +  df. format(d) + " after " + (endTime - startTime) + " milliseconds") ;
    }
}


public class a8p1 {
    public static void main(String[ ] args) {
            int nThreads = 1;
            BufferedReader keyboard;
            InputStreamReader reader;
            reader = new InputStreamReader(System.in); 
            keyboard  = new BufferedReader(reader);
            String InputText = ""; 
            try {
                System.out.print("Input Number of Threads = ");
                InputText = keyboard.readLine( );
            } catch (IOException e) {
                System.err.println("Problem with input stream");
                e.printStackTrace();
            }
            nThreads = Integer.parseInt(InputText);
            long n = 0;
            try {
                System.out.print("Input factorial (n) = ");
                InputText = keyboard.readLine( );
            } catch (IOException e) {
                System.err.println("Problem with input stream");
                e.printStackTrace();
            }
            n = Long.parseLong(InputText);
            Thread t[] = new Thread[nThreads]; 
            
                for (int i = 0; i < t.length; i++) {
                            t[i] = new Thread((Runnable)new Task(n, "Task " + i));
                            t[i].setPriority((i % 10) + 1);
                            t[i].start();
                        }
                        for (int i = 0; i < t.length; i++) {
                            try {
                                t[i].join();
                            } catch (InterruptedException ie) {}
        }
            }
        }
