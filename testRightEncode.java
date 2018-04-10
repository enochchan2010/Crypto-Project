public class MyClass {
    public static void main(String[] args) {
        int x = 258;
        System.out.println(right_encode(x));
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
            String binaryString = String.format("%8s", Integer.toBinaryString(x_i[i]).replace(' ', '0'));
            o_i[i] = binaryString;
        }
    
        String o_nPlus1 = String.format("%8s", Integer.toBinaryString(n+1).replace(' ', '0'));

        String result = "";
        for (int i = 1; i <= n; i++) {
            result += o_i[i];
        }
        result += o_nPlus1;
    
        return result;
    }
}
