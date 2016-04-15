package codingfun;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LevenshteinTest {
    private LevenshteinCalculator lev = new LevenshteinCalculator();

    @Test
    public void insertOne() throws Exception {
        assertThat(lev.calculateDistance("train", "rain")).isEqualTo(1);

    }
}
