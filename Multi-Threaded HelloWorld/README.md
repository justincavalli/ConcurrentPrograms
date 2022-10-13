Write a Java program that creates multiple threads (additionally to the main thread). Each of the threads should execute a loop that prints "Hello World! I'm thread [thread id]. The time is [current time]" on the screen, sleep for 2 seconds, and repeat, until it is stopped.

Thread management should be done on the main thread. The main thread is a program that presents the following options to the user:

"Here are your options:

a - Create a new thread

b - Stop a given thread (e.g. "b 2" kills thread 2)

c - Stop all threads and exit this program"

The main thread should wait for input from the user, execute the selected action, and present the options again -- unless it's option 3, in which case it should exit.
