import java.util.Random;
import java.util.Scanner;

public class RED {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Enter the maximum number of packets:");
        int maxPackets = sc.nextInt();

        System.out.println("Enter the queue size:");
        int queueSize = sc.nextInt();

        System.out.println("Enter the maximum probability:");
        double maxProb = sc.nextDouble();

        System.out.println("Enter the minimum probability:");
        double minProb = sc.nextDouble();

        System.out.println("Enter the threshold value:");
        int threshold = sc.nextInt();

        int currentQueue = 0;

        for (int i = 1; i <= maxPackets; i++) {

            // If queue below threshold → accept
            if (currentQueue < threshold) {
                currentQueue++;
                System.out.println("Packet accepted " + i);
            }

            // Congestion avoidance region
            else if (currentQueue >= threshold && currentQueue < queueSize) {

                // Generate random probability
                double prob = minProb + 
                             (maxProb - minProb) * rand.nextDouble();

                double randomValue = rand.nextDouble();

                if (randomValue < prob) {
                    System.out.println("Packet dropped (CONGESTION AVOIDANCE)");
                } else {
                    currentQueue++;
                    System.out.println("Packet accepted " + i);
                }
            }

            // Queue full → drop
            else {
                System.out.println("Packet dropped (CONGESTION AVOIDANCE)");
            }
        }

        sc.close();
    }
}
