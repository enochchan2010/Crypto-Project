package src;

public class SHA3 {

    public String temp;

    public SHA3(String t) {
        temp = t;

        //System.out.println(temp);
    }


    public String KMACXOF256(String key, int m, int l, String s) {
        String newX = bytePad(encode_string(key),136) + key + right_encode(0);
        return cSHAKE256(newX, l,"KMAC", s);
    }

    public String bytePad(String  X, int w) {
        if(w < 0) {
            throw new IllegalArgumentException();
        }

        String z = left_encode(w) + X;
        while(z.length() % 8 != 0 ) {
            z += "0";
        }

        while((z.length() / 8) %  w != 0 ) {
            z += "00000000";
        }
        return z;
    }

    public static String right_encode(int x) {
        if (x < 0 || x >= Math.pow(2, 2040)) {
            throw new IllegalArgumentException("Invalid x");
        }
        int temp = x;
        int n = 1;
        while (Math.pow(256, n) < x) {
            n++;
        }

        int x_i[] = new int[n+1];
        for (int i = n; i > 0; i--) {
            x_i[i] = temp % 256;
            temp /= 256;
        }

        String o_i[] = new String[n+1];
        for (int i = 1; i <= n; i++) {
            String binaryString = String.format("%8s", Integer.toBinaryString(x_i[i])).replace(' ', '0');
            StringBuilder reverse = new StringBuilder();
            reverse.append(binaryString);
            reverse = reverse.reverse();
            o_i[i] = reverse.toString();
        }

        String o_nPlus1 = String.format("%8s", Integer.toBinaryString(n)).replace(' ', '0');
        StringBuilder o_nPlus1r = new StringBuilder();
        o_nPlus1r.append(o_nPlus1);
        o_nPlus1r = o_nPlus1r.reverse();

        String result = "";
        for (int i = 1; i <= n; i++) {
            result += o_i[i] += ' ';
        }
        result += o_nPlus1r.toString();

        return result;
    }

    public static String left_encode(int x) {
        if (x < 0 || x >= Math.pow(2, 2040)) {
            throw new IllegalArgumentException("Invalid x");
        }
        int temp = x;
        int n = 1;
        while (Math.pow(256, n) < x) {
            n++;
        }

        int x_i[] = new int[n+1];
        for (int i = n; i > 0; i--) {
            x_i[i] = temp % 256;
            temp /= 256;
        }

        String o_i[] = new String[n+1];
        for (int i = 1; i <= n; i++) {
            String binaryString = String.format("%8s", Integer.toBinaryString(x_i[i])).replace(' ', '0');
            StringBuilder reverse = new StringBuilder();
            reverse.append(binaryString);
            reverse = reverse.reverse();
            o_i[i] = reverse.toString();
        }

        String o_0 = String.format("%8s", Integer.toBinaryString(n)).replace(' ', '0');
        StringBuilder o_0r = new StringBuilder();
        o_0r.append(o_0);
        o_0r = o_0r.reverse();

        String result = "";
        for (int i = 1; i <= n; i++) {
            result = result + ' ' + o_i[i];
        }
        result = o_0r.toString() + result;

        return result;
    }

    public String encode_string(String s) {
        return left_encode(s.length()) + s;
    }

    public String cSHAKE256(String X, int L, String N, String S) {
        if(N.equals("")  &  S.equals("")) {
            return SHAKE256();
        } else {
            return keccak();
        }
    }

    public String SHAKE256() {


        return "";
    }

    // Start keccak function

    int KECCAKF_ROUNDS = 24;
    public void ROTL64(x, y) {
        ((x) << (y)) | ((x) >> (64 - (y)))
    }

    public void keccakf(long st[25]) {
        int[] keccakf_rndc = new int[] {
            0x0000000000000001, 0x0000000000008082, 0x800000000000808a,
            0x8000000080008000, 0x000000000000808b, 0x0000000080000001,
            0x8000000080008081, 0x8000000000008009, 0x000000000000008a,
            0x0000000000000088, 0x0000000080008009, 0x000000008000000a,
            0x000000008000808b, 0x800000000000008b, 0x8000000000008089,
            0x8000000000008003, 0x8000000000008002, 0x8000000000000080,
            0x000000000000800a, 0x800000008000000a, 0x8000000080008081,
            0x8000000000008080, 0x0000000080000001, 0x8000000080008008
        };

        int[] keccakf_rotc = new int[] {
            1,  3,  6,  10, 15, 21, 28, 36, 45, 55, 2,  14,
            27, 41, 56, 8,  25, 43, 62, 18, 39, 61, 20, 44
        }

        int[] keccakf_piln = new int[] {
            10, 7,  11, 17, 18, 3, 5,  16, 8,  21, 24, 4,
            15, 23, 19, 13, 12, 2, 20, 14, 22, 9,  6,  1
        }

        long t;
        long[] bc = new long[5];

        for (int r = 0; r < KECCAKF_ROUNDS; r++) {
            // Theta
            for (int i = 0; i < 5; i++) {
                bc[i] = st[i] ^ st[i + 5] ^ st[i + 10] ^ st[i + 15] ^ st[i + 20];
            }
            for (int i = 0; i < 5; i++) {
                t = bc[(i + 4) % 5] ^ ROTL64(bc[(i + 1) % 5], 1);
                for (int j = 0; j < 25; j += 5) {
                    st[j + i] ^= t;
                }
            }

            // Rho Pi
            t = st[1]
            for (int i = 0; i < 24; i++) {
                j = keccakf_piln[i];
                bc[0] = st[j];
                st[j] = ROTL64(t, keccakf_rotc[i]);
                t = bc[0];
            }

            // Chi
            for (int j = 0; j < 25; j += 5) {
                for (int i = 0; i < 5; i++) {
                    bc[i] = st[j + i];
                }
                for (int i = 0; i < 5; i++) {
                    st[j + i] ^= (~bc[(i + 1) % 5]) & bc[(i + 2) % 5];
                }
            }

            // Iota
            st[0] ^= keccakf_rndc[r];
        }
    }

}
