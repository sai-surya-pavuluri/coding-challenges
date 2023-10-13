class Solution {
    public int solution(int[] A) {
        int uncovered = A.length;
        int[] covered = new int[uncovered + 1];

        for(int i = 0; i < A.length ; i++){
                int current = A[i];
            if(current >= 1 && current <= A.length && covered[current] == 0){
                covered[current] = 1;
                uncovered--;
                if(uncovered == 0){
                    return 1;
                }
            }
        }
        return 0;
    }
}