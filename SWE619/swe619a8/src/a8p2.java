import java. util. *;
import java. text. *;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;


public class a8p2 {
    public static void main(String[] args){
    
    int nTasks = 1;
    BufferedReader keyboard;
    InputStreamReader reader;
    reader = new InputStreamReader(System.in); 
    keyboard  = new BufferedReader(reader);
    String InputText = ""; 
    try {
        System.out.print("Input Number of Tasks = ");
        InputText = keyboard.readLine( );
    } catch (IOException e) {
        System.err.println("Problem with input stream");
        e.printStackTrace();
    }
    nTasks = Integer.parseInt(InputText);
    
    long n = 0;
    try {
        System.out.print("Input factorial (n) = ");
        InputText = keyboard.readLine( );
    } catch (IOException e) {
        System.err.println("Problem with input stream");
        e.printStackTrace();
    }
    n = Long.parseLong(InputText);
    
    int tpSize =0;
        try {
            System.out.print("Input Thread Pool Size = ");
            InputText = keyboard.readLine( );
        } catch (IOException e) {
            System.err.println("Problem with input stream");
            e.printStackTrace();
        }
        tpSize = Integer.parseInt(InputText);
    
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                    tpSize, tpSize, 50000L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>( ));

        Task[] tasks = new Task[nTasks];
        for (int i = 0; i < nTasks; i++) {
            tasks[i] = new Task(n, "Task " + i);
            tpe.execute(tasks[i]);
        }
        tpe.shutdown( );
    }
}
