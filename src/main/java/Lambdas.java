import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

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

    public static void printNumbersPredicate(int[] numbers, Predicate<Integer> x  ) {
        for (int number : numbers ) {
            if (x.test(number)) {
                System.out.println(number + "is valid");
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
        printNumbers(myNumbers, i -> i%2==0);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hola");
            }
        };

        System.out.println("**** implementacion usando predicate ****");
        printNumbersPredicate(myNumbers, i-> i%2==0);

        BinaryOperator<Integer> binaryOperator = (x,y) -> x+ y;

        System.out.println("binaryOperator: " + binaryOperator.apply(5,4));

        Runnable runnable1  = () -> System.out.println("hola2");
        runnable.run();
        runnable1.run();

        ToLongFunction toLongFunction = (Object d) -> {
            Date myDate = (Date) d;
            return myDate.toInstant().toEpochMilli();
        };

        System.out.println("toLongFunction: " + toLongFunction.applyAsLong(new Date()));


        System.out.println("*** second part ***");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.forEach((x)-> {
            if (x%2==0)
                System.out.println(x);
        });


        numbers.stream().filter(x->x%2==0).forEach((x)-> System.out.println(x + " es par"));

        Random random = new Random();
        random.ints().limit(10).sorted();

        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("Filtered List: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);

    }

    interface CheckNumber {
        boolean isValid(int i);
        //default method
        default int multiply(int x, int z){
            return x*z;
        }

        //static method
        static void sayHello() {
            System.out.println("I am an static method inside a interface");
        }
    }
}
