import java.util.concurrent.*;

public class Main3 {

    private final static Semaphore semaphore = new Semaphore(1);

   private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addProc(HighLevelDisplay d) {

	// Add a sequence of addRow operations with short random naps.
        for(int i = 0; i < 100; i++) {
            try {
                semaphore.acquire();
                d.addRow("AAAAAAA " + i);
                d.addRow("BBBBBBB " + i);
                semaphore.release();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            
            nap(750);
        }

   }

    private static void deleteProc(HighLevelDisplay d) {
	
	// Add a sequence of deletions of row 0 with short random naps.
        for(int i = 0; i < 100; i++) {
            try {
                semaphore.acquire();
                d.deleteRow(0);
                semaphore.release();
            } catch(InterruptedException e ) {
                System.out.println(e);
            }
            
            
            nap(1300);
        }
    }

    public static void main(String [] args) {
	final HighLevelDisplay d = new JDisplay2();

	new Thread () {
	    public void run() {
		addProc(d);
	    }
	}.start();


	new Thread () {
	    public void run() {
		deleteProc(d);
	    }
	}.start();

    }
}