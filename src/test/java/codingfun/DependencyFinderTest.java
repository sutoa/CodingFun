package codingfun;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DependencyFinderTest {
    private Multimap<String, String> dependencies;
    private DependencyFinder dependencyFinder = new DependencyFinder();

    @Before
    public void setUp() throws Exception {
        dependencies = LinkedHashMultimap.create();
    }

    @Test
    public void independentModulesBuildInOrderInDependencyList() throws Exception {
        dependencies.putAll("A", ImmutableSet.of("A"));
        dependencies.putAll("B", ImmutableSet.of("B"));

        assertThat(dependencyFinder.findBuildOrder(dependencies)).isEqualTo(ImmutableList.of("A", "B"));
    }


    @Test
    public void singleDependentModuleBuildsFirst() throws Exception {
        dependencies.putAll("A", ImmutableSet.of("B"));

        assertThat(dependencyFinder.findBuildOrder(dependencies)).isEqualTo(ImmutableList.of("B", "A"));
    }

    @Test
    public void multiDependentModulesBuildsFirst() throws Exception {
        dependencies.putAll("A", ImmutableSet.of("B", "C"));

        assertThat(dependencyFinder.findBuildOrder(dependencies)).isEqualTo(ImmutableList.of("B", "C", "A"));
    }

    @Test
    public void complexModuleDependenciesBuild() throws Exception {
        dependencies.putAll("D", ImmutableSet.of("D"));
        dependencies.putAll("A", ImmutableSet.of("B", "C", "F"));
        dependencies.putAll("B", ImmutableSet.of("F"));
        dependencies.putAll("C", ImmutableSet.of("F"));
        dependencies.putAll("E", ImmutableSet.of("A", "B"));
        dependencies.putAll("F", ImmutableSet.of("F"));
        dependencies.putAll("G", ImmutableSet.of("D"));

        assertThat(dependencyFinder.findBuildOrder(dependencies)).isEqualTo(ImmutableList.of("D", "F", "B", "C", "A", "E", "G"));
    }

    @Test
    public void complexModulesBuildInOrderInDependencyList() throws Exception {
        dependencies.putAll("A", ImmutableSet.of("B", "C", "F"));
        dependencies.putAll("B", ImmutableSet.of("F"));
        dependencies.putAll("C", ImmutableSet.of("F"));
        dependencies.putAll("D", ImmutableSet.of("D"));
        dependencies.putAll("E", ImmutableSet.of("A", "B"));
        dependencies.putAll("F", ImmutableSet.of("F"));
        dependencies.putAll("G", ImmutableSet.of("D"));

        assertThat(dependencyFinder.findBuildOrder(dependencies)).isEqualTo(ImmutableList.of("F", "B", "C", "A", "D", "E", "G"));
    }


    @Ignore
    @Test
    public void buildIndependentModulesInParallel() throws Exception {
        dependencies.putAll("A", ImmutableSet.of("B", "C", "F"));
        dependencies.putAll("B", ImmutableSet.of("F", "A"));
        dependencies.putAll("C", ImmutableSet.of("F"));
        dependencies.putAll("D", ImmutableSet.of("D"));
        dependencies.putAll("E", ImmutableSet.of("A", "B"));
        dependencies.putAll("F", ImmutableSet.of("F"));
        dependencies.putAll("G", ImmutableSet.of("D"));

        final ImmutableSet<ImmutableSet<String>> parallelBuildOrder = ImmutableSet.of(
                ImmutableSet.of("F", "B", "C", "A", "E"),
                ImmutableSet.of("D", "G")
        );

        assertThat(dependencyFinder.findParalellBuildOrder(dependencies)).isEqualTo(parallelBuildOrder);
    }

    @Test(expected = IllegalStateException.class)
    public void throwsExceptionWhenCyclicDependencyIsDetected() throws Exception {
        dependencies.putAll("A", ImmutableSet.of("B", "C", "F"));
        dependencies.putAll("B", ImmutableSet.of("F", "A"));
        dependencies.putAll("C", ImmutableSet.of("F"));
        dependencies.putAll("D", ImmutableSet.of("D"));
        dependencies.putAll("E", ImmutableSet.of("A", "B"));
        dependencies.putAll("F", ImmutableSet.of("F"));
        dependencies.putAll("G", ImmutableSet.of("D"));

        dependencyFinder.findBuildOrder(dependencies);
    }
}
