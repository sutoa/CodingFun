package codingfun;

public class ValuePackage {
    private int weight;
    private int value;

    public ValuePackage(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int weight() {
        return weight;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "ValuePackage{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}
