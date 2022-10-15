public class TrafficController {

    private final Object leftLock = new Object();
    private final Object rightLock = new Object();

    // cars being queued to cross the bridge from left/right side
    private int leftQueued = 0;
    private int rightQueued = 0;

    public void enterLeft() {
        synchronized(leftLock) {
            // if cars from the right are crossing wait until they are done
            if(rightQueued > 0) {
                try {
                    leftLock.wait();
                } catch(Exception e) {
                    System.out.println(e);
                }
            }
            // queueing up cars to cross from the left
            leftQueued++;
        }
    }
    public void enterRight() {
        synchronized(rightLock) {
            // if cars from the left are crossing wait until they are done
            if(leftQueued > 0) {
                try {
                    rightLock.wait();
                } catch(Exception e) {
                    System.out.println(e);
                }
            }
            // queueing up cars to cross from the right
            rightQueued++;
        }
    }
    public void leaveLeft() {
        synchronized(leftLock) {
            // dequeing right cars as they cross the bridge
            rightQueued--;
            if(rightQueued == 0) {
                // right side has been dequeued allowing the left side to begin entering
                leftLock.notify();
            }
        }
    }
    public void leaveRight() {
        synchronized(rightLock) {
            // dequeing left cars as they cross the bridge
            leftQueued--;
            if(leftQueued == 0) {
                // left side has been dequeued allowing the right side to begin entering
                rightLock.notify();
            }
        }
    }

}