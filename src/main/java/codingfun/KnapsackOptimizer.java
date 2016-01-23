package codingfun;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.FluentIterable.from;
import static java.util.Collections.max;

public class KnapsackOptimizer {
    public int findMaxValueForWeight(final Set<List<Integer>> packages, final int maxWeight) {
        final Set<List<Integer>> smallPackages = findPackagesSmallerThanCapacity(packages, maxWeight);

        if (smallPackages.isEmpty()) return 0;

        final List<Integer> valueAmounts = calculateAmount(packages, maxWeight, smallPackages);

        return max(valueAmounts);
    }

    private List<Integer> calculateAmount(final Set<List<Integer>> packages, final int maxWeight, Set<List<Integer>> smallPackages) {
        return from(smallPackages)
                .transform(new Function<List<Integer>, Integer>() {
                    public Integer apply(List<Integer> pac) {
                        return pac.get(1) + findMaxValueForWeight(packages, maxWeight - pac.get(0));
                    }
                }).toImmutableList();
    }

    private Set<List<Integer>> findPackagesSmallerThanCapacity(Set<List<Integer>> packages, final int maxWeight) {
        return from(packages)
                .filter(new Predicate<List<Integer>>() {
                    public boolean apply(List<Integer> weightAndValue) {
                        return weightAndValue.get(0) <= maxWeight;
                    }
                }).toImmutableSet();
    }
}
