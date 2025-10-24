package practice.codesdope.course;

public class Prime {

    public static boolean isPrimeNumber(int number) {
        boolean prime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }

    public static void main(String[] args) {
        int count = 0;
        int number = 2;
        long start = System.currentTimeMillis();
        while (count < 10000) {
            if (isPrimeNumber(number)) {
                System.out.println("Prime Number:" + number);
                count++;
            }
            number++;
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000 + " second(s) took for completion");
    }
}
