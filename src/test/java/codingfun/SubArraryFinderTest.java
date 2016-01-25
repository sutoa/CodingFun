package codingfun;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubArraryFinderTest {
    private SubArrayFinder subArrayFinder = new SubArrayFinder();

    @Test
    public void maxSubArrayIsTheArrayIfArrayContainsOnlyOneElement() throws Exception {
        final List<Integer> numbers = Arrays.asList(-2);
        assertThat(subArrayFinder.findSubArrayWithMaxSum(numbers)).isEqualTo(Arrays.asList(-2));
    }

    @Test
    public void maxSubArrayIsTheConsecutiveSectionInTheArrayWithTheLargestSum() throws Exception {
        final List<Integer> numbers = Arrays.asList(-2, 1, -3, 4, -1, 2, 1, -5, 4);
        assertThat(subArrayFinder.findSubArrayWithMaxSum(numbers)).isEqualTo(Arrays.asList(4, -1, 2, 1));
    }

    @Test
    public void maxSubArrayIsTheMaxNumberIfArrayContainsAllNegatives() throws Exception {
        final List<Integer> numbers = Arrays.asList(-2, -1);
        assertThat(subArrayFinder.findSubArrayWithMaxSum(numbers)).isEqualTo(Arrays.asList(-1));
    }

    @Test
    public void maxSubArrayIsTheEntireArrayIfArrayContainsAllPositives() throws Exception {
        final List<Integer> numbers = Arrays.asList(2, 1, 3, 10);
        assertThat(subArrayFinder.findSubArrayWithMaxSum(numbers)).isEqualTo(Arrays.asList(2, 1, 3, 10));
    }

    @Test
    public void maxSubArrayIsEmptyIfArrayIsEmpty() throws Exception {
        final List<Integer> numbers = Arrays.asList();
        assertThat(subArrayFinder.findSubArrayWithMaxSum(numbers)).isEqualTo(Arrays.asList());

    }
}
