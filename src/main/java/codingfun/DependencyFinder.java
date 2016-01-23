package codingfun;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DependencyFinder {

    public List<String> findBuildOrder(Multimap<String, String> moduleDependencies) {
        checkForCyclicReferences(moduleDependencies);

        Set<String> buildOrder = Sets.newLinkedHashSet();
        for (String module : modulesWithDependencies(moduleDependencies)) {
            appendDependentModulesToBuildOrder(module, moduleDependencies, buildOrder);
        }

        return ImmutableList.copyOf(buildOrder);
    }

    private Set<String> modulesWithDependencies(Multimap<String, String> moduleDependencies) {
        return moduleDependencies.keySet();
    }

    private void checkForCyclicReferences(Multimap<String, String> moduleDependencies) {
        for (Map.Entry<String, String> dependency : moduleDependencies.entries()) {
            if (!selfDependency(dependency) && containsReversedDependency(moduleDependencies, dependency)) {
                throw new IllegalStateException("found cyclic reference: " + dependency);
            }
        }
    }

    private boolean containsReversedDependency(Multimap<String, String> moduleDependencies, Map.Entry<String, String> dependency) {
        return moduleDependencies.containsEntry(dependency.getValue(), dependency.getKey());
    }

    private boolean selfDependency(Map.Entry<String, String> dependency) {
        return dependency.getKey().equals(dependency.getValue());
    }

    private void appendDependentModulesToBuildOrder(String module, Multimap<String, String> dependencies, Set<String> buildOrder) {
        for (String dependentModule : dependencies.get(module)) {
            if (!module.equals(dependentModule)) {
                appendDependentModulesToBuildOrder(dependentModule, dependencies, buildOrder);
            }
        }
        buildOrder.add(module);
    }

    public Set<Set<String>> findParalellBuildOrder(Multimap<String, String> dependencies) {
        return null;
    }
}
