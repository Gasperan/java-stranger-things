
/**
 * Created by gaspar on 27-10-2016.
 */
public class Lambdas {

    public static void printNumbers(int[] numbers, CheckNumber checkNumber) {
        for (int number :numbers) {
             if (checkNumber.isValid(number)){
                 System.out.println(number + " is valid");
             }
        }
    }

    public static void main(String[] args) {
        int[] myNumbers = {4,2,1,7,5,6};

        System.out.println("implementacion with anonymous class");
        printNumbers(myNumbers, new CheckNumber() {
            public boolean isValid(int i) {
                return i%2==0;
            }
        });

        System.out.println("\nimplementacion with lambdas");
        printNumbers(myNumbers, (i) -> i%2==0);
    }

    interface CheckNumber {
        boolean isValid(int i);
        //default method
        default int multiply(int x, int z){
            return x*z;
        }
    }
}
