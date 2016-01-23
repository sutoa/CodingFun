package codingfun;

import com.google.common.collect.Multimap;

import java.util.Set;

/**
 * Created by suto on 12/9/15.
 */
public interface ClusterFinder {
    Set<Set<Integer>> find(Multimap<Integer, Integer> links);
}
