/* Interface that specifies the basic map operations.  A map
 * is a set of key-value pairs.
 */
public interface Map<K, V>
{
    // Adds the specified key-value pair to the table.  If the key
    //  already exists, it replaces the old value with the new one.
    void add(K key, V value);

    // Removes the specified key and its associated value from the
    //  table.  Takes no action if the key doesn't exist.
    void remove(K key);
    
    // Returns the value associated with the specified key, or null
    //  if the key doesn't exist.
    V get(K key);
}
