public class SHA3 {

    public String temp;

    public SHA3(String t) {
        temp = t;

        //System.out.println(temp);
    }


    public String KMACXOF256(String key, int m, int l, String s) {
        return  "";
    }

    public void bytePad() {

    }

    int right_encode(int x) {
    if (x < 0 || x >= 2^2040) {
        throw new IllegalArgumentException("Invalid x");
    }
    int temp = x;
    int n = 1;
    while (256^n < x) {
        n++;
    }

    int x_i[] = new int[n+1];
    for (int i = n; i > 0; i--) {
        x_i[i] = temp % 256;
        temp /= 256;
    }

    String o_i[] = new String[n+1];
    for (int i = 1; i <= n; i++) {
        String binaryString = Integer.toBinaryString(x[i]);
        binaryString = binaryString.substring(binaryString.length() - 8);
        o_i[i] = binaryString;
    }

    String o_nPlus1 = Integer.toBinaryString(n+1);
    o_nPlus1 = o_nPlus1.substring(binaryString.length() - 8);

    String result;
    for (int i = 1; i <= n; i++) {
        result += o_i[i];
    }

    return result;
}
