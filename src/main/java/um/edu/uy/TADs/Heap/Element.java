package um.edu.uy.TADs.Heap;
import lombok.Data;



@Data
public class Element<K extends Comparable <K>, T> {
    private K key;
    private T value;

    public Element(K key, T value) {
        this.key = key;
        this.value = value;
    }

    public boolean isGreater(Element<K, T> elementToCompare) {
        if (elementToCompare == null || elementToCompare.key == null) {
            return true;
        }
        if (this.key == null) {
            return false;
        }
        return this.key.compareTo(elementToCompare.key) >= 0;
    }


}
