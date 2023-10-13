package Counters;
import java.util.*;
class Solution {
    public int[] solution(int N, int[] A) {

        int[] counters = new int[N];
        for(int i = 0; i< A.length ; i++){
            if(A[i] >= 1 && A[i] <= N ){
                counters[A[i] - 1]++;
            }
            else if(A[i] == N + 1){
                Arrays.fill(counters, max(counters));
            }
        }

        return counters;
    }

    public int max(int[] A){
        int min = 0;
        for(int j = 0; j < A.length; j++){
            if(min < A[j]){
                min = A[j];
            }
        }
        return min;
    }
}

