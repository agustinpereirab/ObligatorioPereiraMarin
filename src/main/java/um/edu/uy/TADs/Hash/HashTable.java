package um.edu.uy.TADs.Hash;

import um.edu.uy.TADs.Hash.Exceptions.ElementAlreadyExistsException;

public interface HashTable {
    public void insert (String key, Object value) throws
            ElementAlreadyExistsException;
    public boolean belongs (String key);
    public void remove (String key);
}