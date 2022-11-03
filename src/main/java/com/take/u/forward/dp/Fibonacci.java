package com.take.u.forward.dp;

public class Fibonacci {
    //recursion
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    //memorization-Top Down (Use Case to the Base case)
    public static int fibonacci(int n, int[] fib) {
        if (n <= 1) {
            return n;
        }
        if (fib[n] != -1) {
            return fib[n];
        }
        return fib[n] = fibonacci(n - 1, fib) + fibonacci(n - 2, fib);
    }

    //tabulation-Bottom Up (Base Case to the Use Case)
    public static int tabulationFibonacci(int n, int[] fib) {
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    //tabulation optimization
    public static int tabulationFibonacci(int n) {
        int lastPrev = 0;
        int prev = 1;

        for (int i = 2; i <= n; i++) {
            int current = prev + lastPrev;
            lastPrev = prev;
            prev = current;
        }

        return prev;
    }

    public static void main(String[] args) {

        int n = 5;

        int[] fib = new int[n + 1];

        for (int i = 0; i < (n + 1); i++) {
            fib[i] = -1;
        }

        // System.out.println(fibonacci(n));
        //System.out.println(fibonacci(n, fib));
        //optimization
        System.out.println(tabulationFibonacci(n));
        //System.out.println(tabulationFibonacci(n, fib));

    }
}
