

public class Driver {

    public static void main(String[] args) {
        //System.out.println("Hello");

        SHA3 encrypt = new SHA3("This is me");

//        System.out.println(encrypt.right_encode(255));
//        System.out.println(encrypt.right_encode(256));
//        System.out.println(encrypt.right_encode(257));
        System.out.println(encrypt.right_encode(65538));
    }





}
