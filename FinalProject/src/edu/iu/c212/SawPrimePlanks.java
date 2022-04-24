package edu.iu.c212;

import java.util.ArrayList;
import java.util.List;

public class SawPrimePlanks {
    static int count = 0;
    // Methods
    public ArrayList<Integer> getPlanksLengths(int longPlankLength) {
        ArrayList<Integer> tempList = new ArrayList<Integer>();
//        Integer tempInt = new Integer(10);
//        tempList.add(tempInt);

        /**
         *  IF LPH CAN BE DIVIDED FROM A NUMBER IN PRIME NUM
         * ELSE DIVIDE BY 2 IF EVEN or 3 IF ODD
         */

        if (primenums.contains(longPlankLength)) {
            for (int i = 0; i < count; i++) {
                tempList.add(longPlankLength);
            }
        } else if (longPlankLength % 2 == 0) {
            
            getPlanksLengths(longPlankLength / 2);

        } else if (longPlankLength % 3 == 0) {
            getPlanksLengths(longPlankLength / 3);
        }

    }



    //helper function that creates same arraylist over and over
    public int arrayMaker()

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


    ArrayList<Integer> primenums = new ArrayList<Integer>();
     public void PrimeNums()
     {
         primenums.add(97); primenums.add(89);primenums.add(83);primenums.add(79);primenums.add(73);
         primenums.add(71);primenums.add(67); primenums.add(61);primenums.add(59);primenums.add(53);
         primenums.add(47);primenums.add(43);primenums.add(41);primenums.add(37);primenums.add(31);
         primenums.add(29);primenums.add(23);primenums.add(19);primenums.add(17);primenums.add(13);
         primenums.add(11); primenums.add(7);primenums.add(5);primenums.add(4);primenums.add(3);primenums.add(2);
     }


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
    public int sawPlank()
    {

        int tempInt = 10;

        return tempInt;

    }
}
