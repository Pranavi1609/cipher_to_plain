import java.util.*;
import java.io.*;

public class Vigenerecipher {
    public static String key = new String();
    public String extndkey;
    public String plaintxt, ciphertxt;
    public String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int row, flag = 0, decrypt = 0;

    public String keyextnsn(String ptxt, String keytxt) {
        int i, j = 0, n;
        String nukey = "";
        for (i = 0; i < ptxt.length(); i++) {
            nukey += keytxt.charAt(j);
            j++;
            if (j == keytxt.length())
                j = 0;
        }
        return nukey;
    }

    public int valueofchar(char x) {
        int i, pos = 0;
        for (i = 0; i < 26; i++) {
            if (x == ALPHABET.charAt(i)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public char charofvalue(int y) {
        int i;
        char ch;
        ch = ALPHABET.charAt(y);
        return ch;
    }

    public String vcencryption(String txt) {
        int i, j, p = 0, k = 0, tmp1 = 0;
        char tmp;
        String ctxt = "";
        extndkey = keyextnsn(txt, key);
        System.out.println("VIGENERE ENCRYPTION");
        System.out.println("PLAIN TEXT : " + txt);
        System.out.println("VIGENERE KEY : " + extndkey);
        for (i = 0; i < txt.length(); i++) {
            p = valueofchar(txt.charAt(i));
            k = valueofchar(extndkey.charAt(i));
            tmp1 = (p + k) % 26;
            tmp = charofvalue(tmp1);
            ctxt += tmp;
        }
        return ctxt;
    }

    public String vcdecryption(String txt) {
        int i, c = 0, k = 0, tmp1 = 0;
        char ch;
        String ptxt = "";
        System.out.println("VIGENERE DECRYPTION");
        System.out.println("CIPHER TEXT : " + txt);
        System.out.println("VIGENERE KEY : " + extndkey);
        for (i = 0; i < txt.length(); i++) {
            c = valueofchar(txt.charAt(i));
            k = valueofchar(extndkey.charAt(i));
            tmp1 = (c - k + 26) % 26;
            ch = charofvalue(tmp1);
            ptxt += ch;
        }
        return ptxt;
    }

    public static void main(String[] args) {
        Vigenerecipher vc = new Vigenerecipher();
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER KEY");
        key = sc.next();
        String text = new String();
        System.out.println("Enter PLAIN TEXT");
        text = sc.next();
        String ciphertext = new String();
        ciphertext = vc.vcencryption(text);
        System.out.println();
        System.out.println("CIPHER TEXT :" + ciphertext);
        System.out.println();
        String plaintext = new String();
        plaintext = vc.vcdecryption(ciphertext);
        System.out.println();
        System.out.println("PLAIN TEXT :" + plaintext);
        sc.close();
    }
}
