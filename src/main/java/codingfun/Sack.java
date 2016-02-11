package codingfun;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

public class Sack {
    private int weight;
    private int value;
    private List<ValuePackage> packages = Lists.newArrayList();

    public static final Sack anEmptySack(){
        return new Sack();
    }

    public Sack() {
    }

    public Sack(int weight, int value, List<ValuePackage> packages) {
        this.weight = weight;
        this.value = value;
        this.packages = packages;
    }

    public int weight() {
        return weight;
    }

    public void add(ValuePackage pac) {
        Preconditions.checkNotNull(pac);
        weight += pac.weight();
        value += pac.value();
        packages.add(pac);
//        final ArrayList<ValuePackage> valuePackages = Lists.newArrayList(packages);
//        valuePackages.add(pac);
//        return new Sack(weight + pac.weight(), value + pac.value(), valuePackages);
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "Sack{" +
                "weight=" + weight +
                ", value=" + value +
                ", packages=" + packages +
                '}';
    }
}
