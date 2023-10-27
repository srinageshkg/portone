package com.dcp.portone.corejava.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// BOG O NOTATION - TIME AMORTIZED COMPLEXITY
public class StructuresOne {
    public static void main(String[] args) {
        int[] intAr = new int[] {5,25,10,-20,8,12,-9};
        List<Integer> listStreamBoxedCollecttolist = Arrays.stream(intAr).boxed().collect(Collectors.toList());
        List<Integer> listStreamBoxedTolist = Arrays.stream(intAr).boxed().toList();
        List<Integer> listArr = new ArrayList<>(intAr.length);

        //Animalll ac = new Animalll();
        //ac.reaadme();
        for (var i : intAr) {
            listArr.add(i);
        }
        //System.out.println(listArr.toString());

        //print("Array", intAr);
        //bubblesort1(intAr);
        //selectionSort(intAr);
        //insersionSort(intAr);
        //shellSort(intAr);
        //mergeSort(intAr, 0, intAr.length);
        //System.out.println("Index of -9: " + iterativeBinarySearch(intAr, -8));
        //System.out.println("Index of -9: " + recursiceBinarySearch(intAr, 5));
        //System.out.println(recursiveFactorial(5));
        String word = "recursiveBinarySearch";
        String strman = "recufrsiveFBinarfy F fearch";
        System.out.println("recufrsiveFBinarfy F fearch : " + stringManipulate(strman));
        //System.out.println("Reverse str of recursiveBinarySearch = " + getReverseWord(word) + "  " + word);
        //System.out.println("Reverse word of recursiveBinarySearch = " + String.valueOf(wordReverse(word)) + "  " + word);
        //System.out.println("Reverse mix of recursiveBinarySearch = " + String.valueOf(reverseString("max")) + "  " + word);
        //printFibonacci(0,1,10);
        //System.out.println();
        //findFactorial(8);
        int[] nums = new int[] {1, 7, 3, 9, 2, 6, 8, 5};
        int[] rotate = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        //List.of(1, 7, 3, 9, 2, 6, 4, 5)
        //missingNumber(nums);
        //magicNumber(163);
        //rotateNumber(rotate, 3);
        //----------------------------
/*        String text = "199";
        try {
            text = text.concat(".5");
            double decimal = Double.parseDouble(text);
            text = Double.toString(decimal);
            int status = (int) Math.ceil(Double.valueOf(text).doubleValue());
            System.out.println(status);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
        }*/
    }

    // Tower of Hanoi Problem???????????????????????????????????
    // Binary Search in Java using recursion
    // check if the two strings are anagrams
    // validate the length of strings and then if found equal, convert the string to char array and then sort the arrays and check if both are equal
    // Arrays.sort(a1) boolean b = Arrays.equal(a1, a2)
    // Sum of digits should be 1

    static String stringManipulate(String input) {
        if (input == null || input.length() < 1) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        for (int i=0; i<input.length();i++) {
            if (input.charAt(i) == 'F') {
                res.append("KF");
            } else if (input.charAt(i) == 'f') {
                res.append("Kf");
            } else  {
                res.append(input.charAt(i));
            }
        }
        return res.toString();
    }

    int solution(int[] a, int m, int k) {
        int cnt = 0;
        int l = a.length-m;

        for (int i=0; i<l; i++) {
            for (int j=i; j<m-1; j++) {
                for(int n=j+1; n<m-1;n++) {
                    if (a[j]+a[n] == k) {
                        cnt += 1;
                    }
                }
            }
        }

        for (int i=0; i<l; i++) {
            for (int j=i+1; j<m; j++) {
                if (a[i]+a[j] == k) {
                    cnt += 1;
                }
            }
        }

        return k;

    }

    String solution1(String s) {
        String result = s;
        if(s.isEmpty()){
            return "";
        }
        while(indexOfLargestPalindrome(result)>0){
            result = result.substring(indexOfLargestPalindrome(result)+1);
        }
        System.out.println(result);
        if(result.length()>1 && isPalindrome1(result)){
            return "";
        }else{
            return result;
        }
    }

    int indexOfLargestPalindrome(String s){
        int result = -1;
        String temp="";
        for(int i=0; i<s.length();i++){
            temp=temp+s.charAt(i);
            if(isPalindrome(temp)){
                System.out.println("is Pal: "+temp);
                if(i>result){
                    result = i;
                }
            }
        }

        return result;
    }

    Boolean isPalindrome1(String s) {
        int i=0;
        int j=s.length()-1;

        while (i<=j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }

        return true;
    }

    public static String isPalindromeTest(String s) {
            String result = "";

            for (int i=0; i<s.length()-1; i++) {
                if(s.charAt(i)==s.charAt(i+1)) {
                    result = s.substring(i+1);
                }
            }
            System.out.println(isPalindrome(result));

            return result;
        }
    public static boolean isPalindrome(String s) {
            boolean isp = true;

            int i=0, j=s.length()-1;

            while (i<=j) {
                if (s.charAt(i) == s.charAt(j)) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }

            return isp;
        }

    public static void magicNumber(int num) {
        int sumOfDig = 0;
        while (num > 0 || sumOfDig>9) {
            if (num == 0) {
                num = sumOfDig;
                sumOfDig = 0;
            }
            sumOfDig += num % 10;
            num /= 10;
        }

        if (sumOfDig == 1) {
            System.out.println("Number " + num + " is " + " a Magic Number");
        } else {
            System.out.println("Number " + num + " is NOT Magic Number");
        }
    }

    public static void rotateNumber(int[] nums, int n) {
        int temp;
        for (int j=0; j<n; j++) {
            temp = nums[nums.length-1];
            for (int i=nums.length-1; i>0; i--) {
                nums[i] = nums[i-1];
            }
            nums[0] = temp;
        }
        System.out.printf("Rotated number by " + n + " times is: ");
        for (var i : nums) {
            System.out.printf("%d, ", i);
        }
        System.out.println();

    }
    public static void missingNumber(int[] nums) {
        int n = nums.length + 1;
        int sumOfFirstNaturalNums = n*(n+1)/2;
        int ss = 0;
        for (int i = 0; i<nums.length; i++) {
            ss += nums[i];
        }
        System.out.println("Missing num = " + (sumOfFirstNaturalNums-ss));
    }

    public static void printFibonacci(int val1, int val2, int num) {
        //Base case
        if (num == 0) {
            return;
        }
        // Printing the next fibonacci number
        System.out.print(val1+val2+" ");
        // Recurvily calling for priting remaining fibonacci numbers
        printFibonacci(val2, val1+val2, --num);
    }
    public static char[] reverseString(String word) {
        int i=0, j=word.length()-1;
        char[] revStrChars = new char[j+1];

        while (i<=j) {
            revStrChars[j] = word.charAt(i);
            revStrChars[i] = word.charAt(j);
            i++;
            j--;
        }
        return revStrChars;
    }
    public static char[] wordReverse(String word) {
        //char[] myword = word.toCharArray();
        //System.out.println("myword String.valueOf (char[]) = " + String.valueOf(myword));
        char[] revword = new char[word.length()];
        String revStr = "";
        int index = 0;
        for (int j = word.length()-1; j>=0; j--) {
            revword[index++] = word.charAt(j);
            //revStr += myword[j];
        }
        revStr = java.lang.String.valueOf(revword);
        System.out.println("revword...... = " + java.lang.String.valueOf(revword));
        System.out.println("revStr s val of.....= " + revStr);
        //System.out.println("revStr = " + revStr.equals(revword.toString()));

        return revword;
    }

    public static String getReverseWord(String word){
        if(word == null || word.isEmpty()){
            return word;
        }
        return word.charAt(word.length() - 1) + getReverseWord(word.substring(0, word.length() - 1));
    }

    static int recursiceBinarySearch(int[] intAr, int val) {
        return recursiceBinarySearch(intAr, 0, intAr.length, val);
    }
    //{5,25,10,-20,8,12,-9};
    static int recursiceBinarySearch(int[] intAr, int start, int end, int val) {
        if (start >= end) {
            return -1;
        }

        int midPt = (start + end ) / 2;
        if (intAr[midPt] == val) {
            return midPt;
        }else if(intAr[midPt] < val) {
            return recursiceBinarySearch(intAr, midPt+1, end, val);
        } else {
            return recursiceBinarySearch(intAr, start, midPt, val);
        }
    }
    static int iterativeBinarySearch(int[] intAr, int val) {
        int start = 0;
        int end = intAr.length;

        while (start < end) {
            int midPt = (start + end) / 2;
            if (intAr[midPt] == val) {
                return midPt;
            } else if(intAr[midPt] < val) {
                start =  midPt + 1;
            } else {
                end = midPt;
            }
        }
        return -1;
    }

    // Merge Sort Not in-place O(nlogn) - base2  stable
    static void mergeSort(int[] intAr, int start, int end) {
        if ((end-start) < 2) {
            return;
        }
        int mid = (start + end ) / 2;
        mergeSort(intAr, start, mid);
        mergeSort(intAr, mid, end);
        merge(intAr, start, mid, end);
    }
    static void merge(int[] intAr, int start, int mid, int end) {
        if (intAr[mid-1] <= intAr[mid]) {
            return;
        }
        int i = start;
        int j = mid;
        int tempIdx = 0;
        int[] temp = new int[end-start];
        while (i<mid && j<end) {
            temp[tempIdx++] = intAr[i] <= intAr[j] ? intAr[i++] : intAr[j++];
        }
        System.arraycopy(intAr, i, intAr, start+tempIdx, mid-i);
        System.arraycopy(temp, 0, intAr, start, tempIdx);

        print("Merge Sort", intAr);
        print("Merge temp Sort", temp);
    }

    public static Long findFactorial(int num) {
        long facRes = 1l;
        for (int i=1; i<=num; i++) {
            facRes *=i;
        }
        System.out.println("Fac of " + num + " is : " + facRes);
        return facRes;
    }
    static int recursiveFactorial(int num) {
        if (num == 0 ) {
            return 1;
        }
        return num * recursiveFactorial(num-1);
    }
    //Shell Sort
    static void shellSort(int[] intAr) {
        for (int gap = intAr.length/2; gap>0; gap /= 2) {
            for (int i = gap; i<intAr.length; i++) {
                int newEle = intAr[i];
                int j = i;
                while (j>=gap && intAr[j-gap] > newEle) {
                    intAr[j] = intAr[j-gap];
                    j -= gap;
                }
                intAr[j] = newEle;
            }
        }
        print("Shell Sort", intAr);
    }
    // insersion sort o( n sq) stable  - quadratic
    static void insersionSort(int[] intAr) {
        int temp;
        for(int i= 1; i<intAr.length; i++) {
            temp = intAr[i];
            int k;
            for (k=i; k>0 && intAr[k-1]>intAr[k]; k--) {
                intAr[k] = intAr[k-1];
            }
            intAr[k] = temp;
        }
        print("Insersion Sort", intAr);
    }
    // Selection Sort in-place, O(n sq) better than bubble sort unstable
    static void selectionSort(int[] intAr) {
        for (int lastUSIndex = intAr.length-1; lastUSIndex>0; lastUSIndex--) {
            int index = 0;
            for (int i = 1; i < intAr.length; i++) {
                if (intAr[i] > intAr[index]) {
                    index = i;
                }
                if (index!= i) {
                    int temp = intAr[i];
                    intAr[i] = intAr[index];
                    intAr[index] = temp;
                }
            }
            //largest = Integer.MIN_VALUE;
        }
        print("Selection Sort", intAr);
    }

    // bubble sort in-place algorithm O(n sq) time complexity = degrades quickly - quadratic algo - stable
    static void bubblesort(int[] intAr) {
        int tmp;
        for (int lastUsIndex = intAr.length-1; lastUsIndex>0; lastUsIndex--) {
            for (int i = 0; i < lastUsIndex; i++) {
                if (intAr[i] > intAr[i + 1]) {
                    tmp = intAr[i];
                    intAr[i] = intAr[i + 1];
                    intAr[i + 1] = tmp;
                }
            }
        }
        print("Bubble Sort", intAr);
    }
    static void bubblesort1(int[] intAr) {
        int tmp;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < intAr.length - 1; i++) {
                if (intAr[i] > intAr[i + 1]) {
                    tmp = intAr[i];
                    intAr[i] = intAr[i + 1];
                    intAr[i + 1] = tmp;
                    flag = true;
                }
            }
        }
        print("Bubble Sort", intAr);
    }

    static void print(String desc, int[] intAr) {
        System.out.printf("%-20s:", desc);
        for (var i : intAr) {
            System.out.printf(i + ", ");
        }
        System.out.println();
    }
}
