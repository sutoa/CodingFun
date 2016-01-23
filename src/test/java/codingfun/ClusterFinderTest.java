package codingfun;

import codingfun.ClusterFinderUsingGuava;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClusterFinderTest {

    private Multimap<Integer, Integer> nodes;
    private ClusterFinderUsingGuava clusterFinder = new ClusterFinderUsingGuava();
    private ImmutableSet<ImmutableSet<Integer>> clusters;


    @Test
    public void isolatedNodesAreInClustersOfTheirOwn() throws Exception {
        nodes = HashMultimap.create();
        nodes.putAll(1, ImmutableSet.of(1));
        nodes.putAll(2, ImmutableSet.of(2));
        nodes.putAll(3, ImmutableSet.of(3));


        clusters = ImmutableSet.of(
                ImmutableSet.of(1),
                ImmutableSet.of(2),
                ImmutableSet.of(3)

        );
        assertThat(clusterFinder.find(nodes)).isEqualTo(clusters);
    }

    @Test
    public void connectedNodesAndIsolatedNodes() throws Exception {
        nodes = HashMultimap.create();
        nodes.putAll(1, ImmutableSet.of(1, 2));
        nodes.putAll(2, ImmutableSet.of(2));
        nodes.putAll(3, ImmutableSet.of(3));


        clusters = ImmutableSet.of(
                ImmutableSet.of(2, 1),
                ImmutableSet.of(3)

        );
        assertThat(clusterFinder.find(this.nodes)).isEqualTo(clusters);
    }


}
