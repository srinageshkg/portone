package com.dcp.portone.leet;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(String.valueOf(s.twoSumi()));
    }
    public void twoSum() {
        ArrayList<Integer> mya = new ArrayList<>(Arrays.asList(3,2,4));
        Integer target = 6;

        for (Integer i : mya){
            for (Integer j : mya){
                if (i != j ) {
                    if (i + j == target) {
                        System.out.println("Int are: " + mya.indexOf(i) + " and " +  mya.indexOf(j) + " Target:" + target);
                    }
                }
            }
        }
    }

    public int[] twoSumi() {
        int[] mya = {-1, -4, -6, -7, 9};
        int[] out = new int[2];
        int target = -10;

        for (int i=0; i<mya.length; i++ ){
            for (int j=i+1; j<mya.length; j++ ){
                if (i != j ) {
                    if (mya[i] + mya[j] == target) {
                        //  System.out.println("Int are: " + mya[i] + " and " +  mya[j] + " Target:" + target);
                        out[0]=i;
                        out[1]=j;
                    }
                }
            }
        }
        System.out.println(out[0] + " " + out[1]);
        return out;
    }
}

