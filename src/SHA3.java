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

    public String keccak() {
        return  "";
    }

}
