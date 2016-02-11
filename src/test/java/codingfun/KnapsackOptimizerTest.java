package codingfun;

import org.junit.Test;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

public class KnapsackOptimizerTest {
    private KnapsackOptimizer packer = new KnapsackOptimizer();

    @Test
    public void pickPackageWithHighestValue() throws Exception {
        final Set<ValuePackage> packages = newHashSet();
        packages.add(new ValuePackage(3, 40));
        packages.add(new ValuePackage(5, 70));
        packages.add(new ValuePackage(10, 30));

        int maxWeight = 16;

        final Sack sackWithMaxValueForWeight = packer.findSackWithMaxValueForWeight(packages, maxWeight);
        assertThat(sackWithMaxValueForWeight.value()).isEqualTo(220);
    }
}
