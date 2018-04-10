public class MyClass {
    public static void main(String[] args) {
        int x = 257;
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
            String binaryString = Integer.toBinaryString(x_i[i]);
            //binaryString = binaryString.substring(binaryString.length() - 8);
            o_i[i] = binaryString;
        }
    
        String o_nPlus1 = Integer.toBinaryString(n+1);
        //o_nPlus1 = o_nPlus1.substring(o_nPlus1.length() - 8);
    
        String result = "";
        for (int i = 1; i <= n; i++) {
            result += o_i[i];
        }
    
        return result;
    }
}
