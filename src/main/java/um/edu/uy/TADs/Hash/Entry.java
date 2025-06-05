package um.edu.uy.TADs.Hash;

import lombok.Data;

@Data
public class Entry implements Comparable<Entry> {
    private String key;
    private Object value;

    public Entry(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Entry o) {
            return this.key.compareTo(o.key);
    }
}
