/*Write a program for congestion control using leaky bucket algorithm 
and token bucket algorithm.*/

/*Leaky bucket algorithm*/
import java.util.Scanner;
public class LeakyBucket {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the bucket capacity");
        int bucketSize = sc.nextInt();

        System.out.println("Enter the bucket rate (Rate at which the bucket sends the packets)");
        int rate = sc.nextInt();

        System.out.println("Enter the number of packets to be sent");
        int n = sc.nextInt();

        int[] packet = new int[n];

        System.out.println("Enter the packets sizes one by one");
        for (int i = 0; i < n; i++) {
            packet[i] = sc.nextInt();
        }

        int stored = 0;

        System.out.println();
        System.out.println("Time_t\tP_size\taccepted\tsent\tremaining");

        for (int i = 0; i < n; i++) {

            int accepted;

            // Check if packet can be accepted
            if (stored + packet[i] <= bucketSize) {
                accepted = packet[i];
                stored += packet[i];
            } else {
                accepted = -1; // dropped
            }

            // Send packets at fixed rate
            int sent;
            if (stored >= rate) {
                sent = rate;
                stored -= rate;
            } else {
                sent = stored;
                stored = 0;
            }

            // Print output row
            if (accepted == -1) {
                System.out.println((i + 1) + "\t" + packet[i] +
                        "\t dropped\t\t" + sent + "\t " + stored);
            } else {
                System.out.println((i + 1) + "\t" + packet[i] +
                        "\t " + accepted + "\t\t" + sent + "\t " + stored);
            }
        }

        sc.close();
    }
}






/*output:-
  Enter the bucket capacity
6
Enter the bucket rate (Rate at which the bucket sends the packets)
2
Enter the number of packets to be sent
4
Enter the packets sizes one by one
2
3
4
6

Time_t	    P_size	  accepted	sent	 remaining
1	        2	      2	        2	     0
2	        3	      3	        2	     1
3	        4	      4	        2	     3
4	        6	   dropped	    2	     1
*/
