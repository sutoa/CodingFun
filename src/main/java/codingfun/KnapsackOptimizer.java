package codingfun;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static codingfun.Sack.anEmptySack;
import static com.google.common.collect.FluentIterable.from;

public class KnapsackOptimizer {
    public Sack findSackWithMaxValueForWeight(final Set<ValuePackage> packages, final int maxWeight) {

        final Set<ValuePackage> smallPackages = findPackagesSmallerThanCapacity(packages, maxWeight);

        if (smallPackages.isEmpty()) return anEmptySack();

        final List<Sack> valueAmounts = addSmallPackagesToSack(packages, maxWeight, smallPackages);

        return pickMostValuableSack(valueAmounts).get();

    }

    private Optional<Sack> pickMostValuableSack(List<Sack> valueAmounts) {
        return valueAmounts.stream().max(new Comparator<Sack>() {
            @Override
            public int compare(Sack o1, Sack o2) {
                return o1.value() - o2.value();
            }
        });
    }

    private List<Sack> addSmallPackagesToSack(final Set<ValuePackage> packages, final int maxWeight, Set<ValuePackage> smallPackages) {
        return from(smallPackages)
                .transform(new Function<ValuePackage, Sack>() {
                    @Override
                    public Sack apply(ValuePackage valuePackage) {
                        final Sack sackWithMaxValueForWeight = findSackWithMaxValueForWeight(packages, maxWeight - valuePackage.weight());
                        sackWithMaxValueForWeight.add(valuePackage);
                        return sackWithMaxValueForWeight;
                    }
                }).toImmutableList();
    }

    private Set<ValuePackage> findPackagesSmallerThanCapacity(Set<ValuePackage> packages, final int maxWeight) {
        return from(packages)
                .filter(new Predicate<ValuePackage>() {
                    public boolean apply(ValuePackage valuePackage) {
                        return valuePackage.weight() <= maxWeight;
                    }
                }).toImmutableSet();
    }

}
