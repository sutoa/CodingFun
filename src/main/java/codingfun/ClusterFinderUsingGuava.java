package codingfun;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.ImmutableMultimap.copyOf;
import static com.google.common.collect.Sets.newHashSet;

public class ClusterFinderUsingGuava implements ClusterFinder {
    public Set<Set<Integer>> find(Multimap<Integer, Integer> links) {
        final ImmutableMultimap<Integer, Integer> copyOfLinks = copyOf(links);
        final ImmutableSet<Integer> allNodes = copyOfLinks.keySet();
        final Set<Integer> unvisitedNodes = newHashSet(allNodes);


        return from(allNodes)
                .transform(new Function<Integer, Set<Integer>>() {
                    @Override
                    public Set<Integer> apply(Integer node) {
                        return findClusterAroundNode(node, copyOfLinks, unvisitedNodes);
                    }
                })
                .filter(new Predicate<Set<Integer>>() {
                    public boolean apply(Set<Integer> input) {
                        return !input.isEmpty();
                    }
                })
                .toImmutableSet();

    }

    private boolean unvisitedNode(Integer currentNode, Set<Integer> unvisitedNodes) {
        return unvisitedNodes.contains(currentNode);
    }

    private Set<Integer> findClusterAroundNode(Integer node, Multimap<Integer, Integer> nodesConnection, Set<Integer> unvisitedNodes) {
        if (!unvisitedNode(node, unvisitedNodes))
            return ImmutableSet.of();

        final HashSet<Integer> neighboringNodes = newHashSet(node);
        unvisitedNodes.remove(node);

        for (Integer neighbor : nodesConnection.get(node)) {
            neighboringNodes.addAll(findClusterAroundNode(neighbor, nodesConnection, unvisitedNodes));
        }

        return neighboringNodes;
    }

}
