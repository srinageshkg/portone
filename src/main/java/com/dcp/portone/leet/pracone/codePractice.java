package com.dcp.portone.leet.pracone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class codePractice {
    public static void main(String[] args) {
        String s1= "race a car";
        String s2= "";
        String s = "A man, a plan, a canal: Panama";
        int[] a = {3, 1, 4, 5};
        //boolean ret = palind(s);
        //int i = oddOccurrencesInArray(a);

        //int i = TapeEquilibrium(a);
        //System.out.println("i = " + i);
        int[] b = {-1,0,1,2,-1,-4};
        List<List<Integer>> tr = threeSum(b);
        int[] passcarsss = {0,1,0,1,1};
        int cc = PassingCars(passcarsss);
        System.out.println("cc = " + cc);

    }

    public static int PassingCars(int[] A) {
        // Implement your solution here
        int counterva = 0;
        int counter = 0;

        for (int i=0; i<A.length; i++) {
            if (A[i] == 0) {
                counterva = counterva + 1;
            }
            if (A[i] == 1) {
                counter = counter + counterva;
            }
        }
        if(counter > 1000000000) return -1;
        return counter;
    }

    public static int PassingCars1(int[] A) {
        // Implement your solution here
        int p = 0;
        int maxCars = 1_000_000_000;
        //Map<Interget, Integer> pairs = new HashMap<>();

        List<List<Integer>> pairs = new ArrayList<>();
        List<int[]> cs = new ArrayList<>(2);
        List<Integer> cars = new ArrayList<>(A.length);
        cars = Arrays.stream(A).mapToObj(Integer::valueOf).collect(Collectors.toList());
        if (p > maxCars) return -1;
        if (!cars.contains(0) || !cars.contains(1)) return -1;

        for (int i=0; i<A.length-1; i++) {
            if (cars.get(i) == 0) {
                for(int j=i+1; j<A.length; j++) {
                    if (cars.get(j) == 1) {
                        List<Integer> p1= new ArrayList<Integer>();
                        cs.add(new int[]{i,j});
                        pairs.add(p1);
                    }
                }
            }

        }
        return pairs.size();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<Integer> subList;
        List<List<Integer>> mainList = new ArrayList();
        for (int i=0; i<nums.length; i++) {
            for (int j=0; j<nums.length; j++) {
                if((nums[i]<=nums[j]) && (i!=j)){
                    for (int k=0; k<nums.length; k++) {
                        if((nums[j]<=nums[k]) && (i!=k && j!=k)){
                            if (nums[i]+nums[j]+nums[k] == 0) {
                                System.out.println(i+" "+j+" "+k);
                                subList = new ArrayList();
                                subList.add(nums[i]);
                                subList.add(nums[j]);
                                subList.add(nums[k]);
                                mainList.add(subList);
                            }
                        }
                    }
                }
            }
        }
        return mainList;
    }

    public Boolean notContains(List<Integer> subList,List<List<Integer>> mainList){
        for (List<Integer> list : mainList) {
            if ((list.get(0) == subList.get(0)) && (list.get(1) == subList.get(1)) && (list.get(2) == subList.get(2))) {
                return false;
            }
        }
        return true;
    }
    public static int TapeEquilibrium(int[] A) {
        int a = 0;
        int diff = Integer.MAX_VALUE;

        int absval = 0;
        // Implement your solution here
        if (A.length ==2 ) return Math.abs(A[0]-A[1]);
        for (int i=1; i<A.length; i++) {
            int sh = 0;
            int fh = 0;
            for (int j=0; j<i; j++) {
                fh += A[j];
            }
            for (int j=i; j<A.length; j++) {
                sh += A[j];
            }
            absval = Math.abs(fh-sh);
            System.out.println("i = " + absval);
            if (diff > absval) {
                diff = absval;
            }
        }

        return diff;
    }
     public int PermMissingElem(int[] A) {
        // Implement your solution here
        Arrays.sort(A);
        if (A.length == 1) return 1;
        for (int i=0; i<A.length; i++) {
            if (A[i] == i+1) {
                continue;
            }
            return A[i]-1;
        }
        return A[A.length-1] + 1;
    }
    public int frogJmp(int X, int Y, int D) {
        // Implement your solution here
/*        int minJ = 0;
        int i = 1;
        while (true) {
            if ( X + (i * D) >= Y ) {
                minJ = i;
                break;
            }
            i += 1;
        }
        return minJ;*/
        int diff = Y - X;
        if (diff % D == 0)
            return diff /D;
        else
            return diff/D + 1;
    }

    public static int oddOccurrencesInArray(int[] A) {
        // Implement your solution here
        int a = -1;
        List<Integer> listA = new ArrayList<>(Arrays.stream(A).mapToObj(Integer::valueOf).sorted().collect(Collectors.toList()));
        System.out.println(listA);
/*        for (int i=0; i<listA.size(); i++) {
            if (listA.lastIndexOf(listA.get(i)) == i) {
                return listA.get(i);
            }
            if (listA.subList(i+1, listA.size()-1).contains(listA.get(i))) {
                if (i<listA.size()-1) {
                    i = i+1;
                }
            } else {
                return listA.get(i);
            }
        }*/
        for (int i=0; i<listA.size()-1; i++) {
            if (listA.get(i) == listA.get(i+1)) {

                if (i<listA.size()-1) {
                    i = i+1;
                }
                System.out.println("i = " + i + " " + listA.get(i));
                if (i==listA.size()-1) {
                    a = listA.get(i);
                }
            }else{
                return listA.get(i);
            }
        }
        if (a == -1) {
            return listA.get(listA.size()-1);
        } else {
            return a;
        }
    }

    public int[] solution(int[] A, int K) {
        int[] rot = null;

        List<Integer> totList = new ArrayList<>(A.length);

        totList = Arrays.stream(A).mapToObj(Integer::valueOf).collect(Collectors.toList());

        Collections.rotate(totList, K);


        return totList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static boolean palind(String s) {
        s = s.trim();

        return false;    
    }

    // you can also use imports, for example:
// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");


    class binaryGap {
        public int solution(int N) {
            // Implement your solution here
            int lobg = 0;

            String binaryStr = Integer.toBinaryString(N);
            List<Integer> onesList = new ArrayList<>();
            for (int i=0; i<binaryStr.length();i++) {
                if (binaryStr.charAt(i) == '0') {
                    continue;
                }
                onesList.add(i);
            }
            //System.out.println("binaryStr: "+binaryStr+" "+ onesList);

            for (int i=0; i<onesList.size()-1;i++) {
                int indicesDiff = onesList.get(i+1) - onesList.get(i) - 1;

                lobg = Math.max(lobg, indicesDiff);
                //System.out.println("binaryStr: "+indicesDiff+" "+ onesList);
            }
            return lobg;
        }
    }
}
