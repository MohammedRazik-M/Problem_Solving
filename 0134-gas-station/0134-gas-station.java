class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas, totalCost, currGas, startIndex;

        totalGas = totalCost = currGas = startIndex = 0;

        int n = gas.length;

        for(int index=0; index<n; index++) {
            totalGas += gas[index];
            totalCost += cost[index];

            currGas += gas[index] - cost[index];

            if(currGas < 0) {
                startIndex = index+1;
                currGas = 0;
            }
        }
        return totalCost > totalGas ? -1 : startIndex;
    }
}