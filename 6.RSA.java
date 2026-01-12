import java.util.Random;
import java.util.Scanner;

public class RSA {

    // Modular exponentiation
    static int modPow(int base, int exp, int n) {
        int result = 1;
        base = base % n;

        while (exp > 0) {
            if (exp % 2 == 1)
                result = (result * base) % n;

            exp = exp / 2;
            base = (base * base) % n;
        }
        return result;
    }

    // GCD
    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Check prime
    static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // ---- RANDOM PRIME GENERATION ----
        int p, q;

        do {
            p = rand.nextInt(50) + 50;   // ensures p >= 50
        } while (!isPrime(p));

        do {
            q = rand.nextInt(50) + 50;   // ensures q >= 50
        } while (!isPrime(q) || q == p);

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        // ---- AUTO SELECT e ----
        int e;
        do {
            e = rand.nextInt(phi - 2) + 2;
        } while (gcd(e, phi) != 1);

        // ---- AUTO COMPUTE d ----
        int d = 0;
        for (int i = 1; i < phi; i++) {
            if ((e * i) % phi == 1) {
                d = i;
                break;
            }
        }

        // ---- INPUT ----
        System.out.print("Enter the plain text:\n");
        String plaintext = sc.nextLine();

        System.out.println("Encrypting string: " + plaintext);

        byte[] bytes = plaintext.getBytes();

        // Display bytes
        System.out.print("String in bytes: ");
        for (byte b : bytes) {
            System.out.print((b & 0xff));
        }
        System.out.println();

        // ---- ENCRYPT ----
        int[] encrypted = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            encrypted[i] = modPow(bytes[i] & 0xff, e, n);
        }

        // ---- DECRYPT ----
        System.out.print("Decrypting Bytes: ");
        String decrypted = "";

        for (int i = 0; i < encrypted.length; i++) {
            int dec = modPow(encrypted[i], d, n);
            System.out.print(dec);
            decrypted += (char) dec;
        }

        System.out.println();
        System.out.println("Decrypted string: " + decrypted);

        sc.close();
    }
}
