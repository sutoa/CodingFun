package codingfun;

import java.util.List;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;

/**
 * UnThreadSafe solution - to avoid passing parameters around during refactoring
 */
public class SubArrayFinder {
    private Integer maxSumForSubArray = Integer.MIN_VALUE;
    private Integer runningSum = 0;
    private Integer maxSubArrayBeginIdx = 0;
    private Integer maxSubArrayEndIdx = 0;
    private Integer currentIndex;
    private List<Integer> copyOfNumbers;
    private Integer beginIdx;
    private Integer endIdx;

    public List<Integer> findSubArrayWithMaxSum(List<Integer> numbers) {
        if(numbers.isEmpty()) return newArrayList();

        copyOfNumbers = copyOf(numbers);

        for (int i = 0; i < copyOfNumbers.size(); i++) {
            currentIndex = i;
            if (wouldRunningSumMakeSubArraySumLarger()) {
                extendSubArray();
                checkForNewMax();
            } else {
                restartSubArray();
                checkForNewMax();
            }
        }
        return copyOfNumbers.subList(maxSubArrayBeginIdx, maxSubArrayEndIdx + 1);
    }

    private void checkForNewMax() {
        if (newSubArrayMaxSumFound()) {
            maxSubArrayIsCurrentSubArray();
        }
    }

    private void extendSubArray() {
        runningSum = copyOfNumbers.get(currentIndex) + runningSum;
        endIdx = currentIndex;
    }

    private void restartSubArray() {
        runningSum = copyOfNumbers.get(currentIndex);
        beginIdx = currentIndex;
        endIdx = currentIndex;
    }

    private void maxSubArrayIsCurrentSubArray() {
        maxSumForSubArray = runningSum;
        maxSubArrayBeginIdx = beginIdx;
        maxSubArrayEndIdx = endIdx;
    }

    private boolean newSubArrayMaxSumFound() {
        return runningSum > maxSumForSubArray;
    }

    private boolean wouldRunningSumMakeSubArraySumLarger() {
        return runningSum > 0;
    }

}
