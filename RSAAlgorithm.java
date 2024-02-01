import java.math.BigInteger;
import java.util.Random;
import java.io.*;

class RSAAlgorithm {
    private BigInteger p, q, n, phi, e, d; // public key components
    private int bitLen = 1024;
    private int blkSz = 256; // block size in bytes
    private Random rand;

    // Convert bytes to string
    private static String bytesToString(byte[] encrypted) {
        StringBuilder str = new StringBuilder();
        for (byte b : encrypted) {
            str.append(Byte.toString(b));
        }
        return str.toString();
    }

    // Encrypt message
    public byte[] encrypt(byte[] msg) {
        return (new BigInteger(msg)).modPow(e, n).toByteArray();
    }

    // Decrypt message
    public byte[] decrypt(byte[] msg) {
        return (new BigInteger(msg)).modPow(d, n).toByteArray();
    }

    // Calculate public key components p, q, n, phi, e, d
    public RSAAlgorithm() {
        rand = new Random();
        p = BigInteger.probablePrime(bitLen, rand);
        q = BigInteger.probablePrime(bitLen, rand);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLen / 2, rand);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public RSAAlgorithm(BigInteger e, BigInteger d, BigInteger n) {
        this.e = e;
        this.d = d;
        this.n = n;
    }

    public static void main(String[] args) throws java.lang.Exception {
        RSAAlgorithm rsaObj = new RSAAlgorithm();
        String msg = "Hello world! Security Laboratory";
        System.out.println("Simulation of RSA algorithm");
        System.out.println("Message (string): " + msg);
        System.out.println("Message (bytes): " + bytesToString(msg.getBytes()));

        // Encrypt test message
        byte[] ciphertext = rsaObj.encrypt(msg.getBytes());
        System.out.println("Ciphertext (bytes): " + bytesToString(ciphertext));

        // Decrypt ciphertext
        byte[] plaintext = rsaObj.decrypt(ciphertext);
        System.out.println("Plaintext (bytes): " + bytesToString(plaintext));
        System.out.println("Plaintext (string): " + new String(plaintext));
    }
}
