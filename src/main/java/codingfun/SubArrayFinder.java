package codingfun;

import com.google.common.collect.ImmutableList;

import java.util.List;

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

    public List<Integer> findMaxSubArray(List<Integer> numbers) {
        if(numbers.isEmpty()) return newArrayList();

        copyOfNumbers = ImmutableList.copyOf(numbers);

        for (int i = 0; i < copyOfNumbers.size(); i++) {
            currentIndex = i;
            if (wouldCurrentRunningSumMakeTotalGreater()) {
                addCurrentNodeToRunningSum();
                if (newSubStringMaxFound()) {
                    updateMaxSubString();
                }
            } else {
                restartSubStringFromCurrentNode();
            }

        }
        return copyOfNumbers.subList(maxSubArrayBeginIdx, maxSubArrayEndIdx + 1);
    }

    private void addCurrentNodeToRunningSum() {
        runningSum = copyOfNumbers.get(currentIndex) + runningSum;
    }

    private void restartSubStringFromCurrentNode() {
        runningSum = copyOfNumbers.get(currentIndex);
        maxSumForSubString = runningSum;
        maxSubArrayBeginIdx = currentIndex;
        maxSubArrayEndIdx = currentIndex;
    }

    private void updateMaxSubString() {
        maxSumForSubString = runningSum;
        maxSubArrayEndIdx = currentIndex;
    }

    private boolean newSubStringMaxFound() {
        return runningSum > maxSumForSubString;
    }

    private boolean wouldCurrentRunningSumMakeTotalGreater() {
        return runningSum > 0;
    }

}
