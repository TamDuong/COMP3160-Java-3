/*
 * Class that represents a single item (key-value pair) in a map.
 */
public class MapEntry<K, V>
{
    private K key;
    private V value;
    
    public K getKey()
    {
        return key;
    }

    public void setKey(K key)
    {
        this.key = key;
    }

    public V getValue()
    {
        return value;
    }

    public void setValue(V value)
    {
        this.value = value;
    }

    // Constructor
    public MapEntry(K key, V value)
    {
	this.key = key;
	this.value = value;
    }
    
    public String toString()
    {
	return "<" + key + ", " + value + ">";
    }
}
