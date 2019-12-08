package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 구름 
public class Practice1_0001 {

    public static int H, W;
    public static int sky[][];

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub

        System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            sky = new int[H][W];

            bw.flush();
            bw.write("#" + tc + " ");
            for (int i = 0; i < H; i++) {

                char[] w_char_arr = br.readLine().toCharArray();
                int k = -1;
                boolean beCloud = false;
                for (int j = 0; j < W; j++) {
                    char ch = w_char_arr[j];
                    if ('c' == ch) {
                        k = 0;
                        beCloud = true;
                    } else {
                        if(beCloud) {
                            k++;
                        }
                    }
                    sky[i][j] = k;
                        bw.write(k + "");
                    if (i < H) {
                        bw.write(" ");
                    }

                }
                bw.write("\n");
            }

        }
        bw.close();

    }

}
