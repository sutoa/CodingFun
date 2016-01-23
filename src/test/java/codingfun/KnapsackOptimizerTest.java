package codingfun;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

public class KnapsackOptimizerTest {
    private KnapsackOptimizer packer = new KnapsackOptimizer();

    @Test
    public void pickPackageWithHighestValue() throws Exception {
//        final ImmutableSet<ImmutableSet<Integer>> packages = of(
//                of(3, 40),
//                of(5, 70),
//                of(10, 30)
//        );

        final Set<List<Integer>> packages = newHashSet();
        packages.add(newArrayList(3, 40));
        packages.add(newArrayList(5, 70));
        packages.add(newArrayList(10, 30));

        int maxWeight = 4;

        assertThat(packer.findMaxValueForWeight(packages, maxWeight)).isEqualTo(210);
    }
}
