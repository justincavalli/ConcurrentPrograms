import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Message> queue;
    private int id;

    public Consumer(BlockingQueue<Message> q, int n) {
	queue = q;
	id = n;
    }
    
    public void run() {
	Message msg = null;
	int count = 0;
	do {
	    try {
		msg = queue.take(); // Get a message from the queue
		count++;
		RandomUtils.print("Consumed " + msg.get(), id);
		Thread.sleep(RandomUtils.randomInteger());
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	} while (msg.get() != "stop");
	// Don't count the "stop" message

	// add this for other consumers to stop
	msg = new Message("stop");
	try {
	    queue.put(msg); // Put this final message in the queue
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	count--;
	RandomUtils.print("Messages received: " + count, id);
    }
}
