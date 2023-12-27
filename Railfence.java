import java.util.*;
import java.io.*;

public class Railfence {
    public static int key[] = new int[8];
    public char mat[][] = new char[10][8];
    public char pmat[][] = new char[10][8];
    public char cmat[][] = new char[10][8];
    String plain = "";
    String cipher = "";
    int rows = 0, col;

    public String rfencryption(String text1) {
        int i, j, len, ch, k, p = 0;
        String enctxt = "";
        String text = "";
        len = text1.length();
        for (i = 0; i < len; i++)
            text += text1.charAt(i);
        if ((len % 7) != 0) {
            rows = (len / 7) + 1;
            ch = len % 7;
            for (i = 0; i < (7 - ch); i++)
                text += 'X';
        } else
            rows = len / 7;
        k = 0;
        for (i = 1; i <= rows; i++) {
            for (j = 1; j <= 7; j++)
                mat[i][j] = text.charAt(k++);
        }
        for (i = 1; i <= rows; i++) {
            for (j = 1; j <= 7; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
        k = 1;
        j = 1;
        while (k <= 7) {
            for (p = 0; p < 7; p++) {
                if (k == key[p]) {
                    j = p + 1;
                    k++;
                    break;
                }
            }
            for (i = 1; i <= rows; i++)
                enctxt += mat[i][j];
        }
        System.out.println(enctxt);
        return enctxt;
    }

    public String rfdecryption(String txt, int plength) {
        int i, j = 1, len, k = 1, p, q = 0;
        String dectxt = "";
        String ptext = "";
        while (k <= 7) {
            for (p = 0; p < 7; p++) {
                if (key[p] == k) {
                    j = p + 1;
                    k++;
                    break;
                }
            }
            for (i = 1; i <= rows; i++)
                cmat[i][j] = txt.charAt(q++);
        }
        for (i = 1; i <= rows; i++) {
            for (j = 1; j <= 7; j++)
                System.out.print(cmat[i][j] + " ");
            System.out.println();
        }
        for (i = 1; i <= rows; i++) {
            for (j = 1; j <= 7; j++)
                dectxt += cmat[i][j];
        }
        len = dectxt.length();
        if (plength < len) {
            for (i = 0; i < plength; i++)
                ptext += dectxt.charAt(i);
        }
        return ptext;
    }

    public static void main(String[] args) throws IOException {
        int i = 0;
        int k;
        String c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Railfence rf = new Railfence();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter key");
        for (i = 0; i < 7; i++) {
            c = br.readLine();
            key[i] = Integer.parseInt(c);
        }
        for (i = 0; i < 7; i++)
            System.out.print(key[i] + " ");
        String plain = new String();
        System.out.println("Enter PLAIN TEXT");
        plain = sc.next();
        k = plain.length();
        System.out.println(plain);
        String ctext = new String();
        ctext = rf.rfencryption(plain);
        System.out.println();
        System.out.println("CIPHER TEXT :" + ctext);
        System.out.println();
        String plaintext = new String();
        plaintext = rf.rfdecryption(ctext, k);
        System.out.println();
        System.out.println("PLAIN TEXT :" + plaintext);
        sc.close();
    }
}
