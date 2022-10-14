import java.time.LocalTime;
import java.util.Scanner;
import java.util.Hashtable;

public class HelloWorldThread extends Thread
{
    public void run()
    {
        // run the thread continuously every two seconds
        while(true)
        {
            System.out.println("Hello World! I'm thread " + Thread.currentThread().getId() + " the time is " + LocalTime.now());
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e) {
                // stop running when interrupted
                return;
            }
        }
    }

    public static void main(String args[])
    {
        // mapping of ids to threads
        Hashtable<Long, HelloWorldThread> threads = new Hashtable<Long, HelloWorldThread>();

        // take user input for the options
        Scanner input = new Scanner(System.in);
        String selection;
        while(!(selection = input.next()).equals("c"))
        {
            if(selection.equals("a"))
            {
                // start a new thread and add it to the hashmap
                HelloWorldThread thread = new HelloWorldThread();
                thread.start();
                threads.put(thread.getId(), thread);
            }
            else if(selection.equals("b"))
            {
                // find the thread by its ID and interrupt it
                long id = input.nextInt();
                threads.get(id).interrupt();
            }
        }
        
        // interrupt all the threads in the hashmap and close the program
        input.close();
        for(long key : threads.keySet())
            threads.get(key).interrupt();

    }
}