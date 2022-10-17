import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Scanner;

public class MessageQueue {
    private static int n_ids;

    public static void main(String[] args) {
	BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
	ArrayList<Producer> producers = new ArrayList<>();

	Scanner input = new Scanner(System.in);
	System.out.println("How many producers?");
	int MProducers = input.nextInt();
	System.out.println("How many consumers?");
	int NConsumers = input.nextInt();
	input.close();
	
	for(int i = 0; i < MProducers; i ++) {
		Producer p = new Producer(queue, n_ids++);
		producers.add(p);
		new Thread(p).start();

	}

	for(int j = 0; j < NConsumers; j++) {
		Consumer c = new Consumer(queue, n_ids++);
		new Thread(c).start();
	}

	try {
	    Thread.sleep(10000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	
	for(Producer p : producers) {
		p.stop();
	}
    }
}
