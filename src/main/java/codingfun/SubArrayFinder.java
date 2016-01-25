package codingfun;

import java.util.List;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;

/**
 * UnThreadSafe solution - to avoid passing parameters around during refactoring
 */
public class SubArrayFinder {
    private Integer maxSumForSubString = Integer.MIN_VALUE;
    private Integer runningSum = 0;
    private Integer maxSubArrayBeginIdx;
    private Integer maxSubArrayEndIdx;
    private Integer currentIndex;
    private List<Integer> copyOfNumbers;

    public List<Integer> findSubArrayWithMaxSum(List<Integer> numbers) {
        if(numbers.isEmpty()) return newArrayList();

        copyOfNumbers = copyOf(numbers);

        for (int i = 0; i < copyOfNumbers.size(); i++) {
            currentIndex = i;
            if (wouldRunningSumMakeSubArraySumLarger()) {
                addCurrentNodeToRunningSum();
                if (newSubArrayMaxSumFound()) {
                    updateMaxSubArray();
                }
            } else {
                restartSubArrayFromCurrentNode();
            }
        }
        return copyOfNumbers.subList(maxSubArrayBeginIdx, maxSubArrayEndIdx + 1);
    }

    private void addCurrentNodeToRunningSum() {
        runningSum = copyOfNumbers.get(currentIndex) + runningSum;
    }

    private void restartSubArrayFromCurrentNode() {
        runningSum = copyOfNumbers.get(currentIndex);
        maxSumForSubString = runningSum;
        maxSubArrayBeginIdx = currentIndex;
        maxSubArrayEndIdx = currentIndex;
    }

    private void updateMaxSubArray() {
        maxSumForSubString = runningSum;
        maxSubArrayEndIdx = currentIndex;
    }

    private boolean newSubArrayMaxSumFound() {
        return runningSum > maxSumForSubString;
    }

    private boolean wouldRunningSumMakeSubArraySumLarger() {
        return runningSum > 0;
    }

}
