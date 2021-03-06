package edu.iu.c212.programs;

import java.util.ArrayList;
import java.util.List;

public class SawPrimePlanks {

    List<Integer> length = new ArrayList<>();
    // Methods
    public List<Integer> getPlanksLengths(int longPlankLength) {
        length = new ArrayList<>();
        sawPlank(longPlankLength);
        return length;
    }

        /**
         *  IF LPH CAN BE DIVIDED FROM A NUMBER IN PRIME NUM
         * ELSE DIVIDE BY 2 IF EVEN or 3 IF ODD
         */

    //divider helper method
    public int division(int[] n) {
        int quotient = n[0];
        for (int i = 1; i < n.length; i++) {
            quotient = quotient / n[i];
        }
        return quotient;
    }


    /**
     * Take longPlankLength (LPL)
     * if LPH even -> divide by 2
     * if LPH odd -> divide by 3
     *
     * keep dividing until LPL is divisible by a primenum
     *   count amount of times divided by two / prime number you are dividing by
     *  quotient divisble by prime number after dividing by two -> count amount of times divided by two * prime number
     *
     */


    //checks if number is primenum
    public boolean isPrime(int a){
        boolean result = false;
        for (int i = 2; i <= a / 2; ++i) {
            if (a % i == 0){
                result = true;
                break;
            }
        }
        return result;
}



    // This will be a recursive method
    public int sawPlank(int plankLength) {
        int primeNumCheck = 2;
        if (isPrime(plankLength)){
            while(true){
                if ((plankLength % primeNumCheck) == 0){
                    for(int i = 0; i < primeNumCheck; i++) {
                        sawPlank(plankLength/primeNumCheck);
                    }
                     break;
                }
            else {
                primeNumCheck++;
                }
            }
        }
    else {
        length.add(plankLength);
        }
        return plankLength;

    }
}
