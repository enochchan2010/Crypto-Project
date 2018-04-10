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

    public String right_encode(int x) {
        int n = 1;
        int temp = x;
        temp /= 256;
        while(temp > 0) {
            temp /= 256;
            n++;
        }

        int x;

        for(int i = 1; i < n; i++) {
            x = Math.pow(2,8 * (n - i));
        }
        return "";
    }
}
