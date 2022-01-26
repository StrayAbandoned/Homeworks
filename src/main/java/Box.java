import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> list = new ArrayList<>();
    private float weight;

    public float getWeight() {
        weight = 0.0f;
        for (T t : list) {
            weight += t.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0000000.1f;
    }

    public void moveFruitsFrom(Box<T> box) {
        if (this != box) {
            this.list.addAll(box.list);
            box.list.clear();
        }
    }

    public void addFruit(T fruit) {
        list.add(fruit);
    }
}
