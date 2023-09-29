package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        boolean isPrime = true;
        if(number > 1) {
            for(int i = 2; (i * i) <= number; i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        } else {
            throw new IllegalNumberException("Illegal Argument");
        }
        return isPrime;
    }

    public int digitsSum(int number) {
        int res = 0;
        if(number < 0) {
            number *= -1;
        }
        res += number % 10;
        while(number > 0){
            number /= 10;
            res += number % 10;
        }
        return res;
    }
}