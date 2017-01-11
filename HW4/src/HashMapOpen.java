/* Implements the Map interface using a hash table with open addressing
 * and linear probing.
 */
public class HashMapOpen<K, V> implements Map<K, V>
{
    private MapEntry<K, V>[] data;	// where the table's data is kept
    private final MapEntry<K, V> DELETED = new MapEntry<>(null, null);	// dummy entry to indicate deleted items
    private int numOccupied = 0;	// keeps track of how many spaces in the table have been filled
    
    public HashMapOpen(int capacity)
    {
	data = new MapEntry[capacity];
    }

    @Override
    public void add(K key, V value)
    {
	int index = findKey(key);
	if (data[index] == null) {	// key not in table - add a new MapEntry object
	    data[index] = new MapEntry<K, V>(key, value);
	    numOccupied++;
	    if (loadFactor() >= 0.75)	// rehash the table once the load factor reaches 0.75
		rehash();
	} else				// key already exists - just change the existing entry's value
	    data[index].setValue(value);
    }

    @Override
    public void remove(K key)
    {
	int index = findKey(key);
	if (data[index] != null)
	    data[index] = DELETED;
    }

    @Override
    public V get(K key)
    {
	int index = findKey(key);
	if (data[index] != null)	// key found
	    return data[index].getValue();
	else				// key not in table
	    return null;
    }

    // Rehashes the table to make more space.  Dummy deleted items are also
    //  excluded from the new table.
    private void rehash()
    {
	numOccupied = 0;
	MapEntry<K, V>[] oldData = data;
	data = new MapEntry[2*data.length + 1];	// the +1 ensures an odd number of elements (which is apparently better for performance)
	for (int i = 0; i < oldData.length; i++) {
	    if (oldData[i] != null && oldData[i] != DELETED)
		add(oldData[i].getKey(), oldData[i].getValue());
	}
    }
    
    // A simple hash function - this translates a key to an index in the table
    private int hash(K key)
    {
	int index = key.hashCode() % data.length;		//*MC* .hasCode() will return the int of key if key is a string or char then cast to int
	if (index < 0)	// hashCode can sometimes return negative values
	    index += data.length;
	return index;
    }

    // Searches the table for the specified key.  Returns either the index of
    //  that key in the table (if the key exists), or the first empty index in
    //  the table (if the key doesn't exist).
    private int findKey(K key)
    {
	int index = hash(key);
	while (data[index] != null && !key.equals(data[index].getKey()))		//*MC* while index has item && key != key at index
	    index = (index + 1) % data.length;
	return index;
    }

    // Returns the load factor of the table.  This is the ratio of the occupied
    //  table spaces (including deleted items) to the total spaces available.
    private double loadFactor()
    {
	return (double)numOccupied / data.length;
    }
    
    public String toString()
    {
	String r = "HashMapOpen:\n";
	for (int i = 0; i < data.length; i++)
	    r += i + " - " + data[i] + "\n";
	return r;
    }
    
    public static void main(String[] args)
    {
	Map<Integer, Sloth> slakoth = new HashMapOpen<>(5);
	Integer[] keys = {47, 17, 27, 29, 31};
	Sloth[] values = {new Sloth(2, 45), new Sloth(3, 123123), new Sloth(2, 0), new Sloth(3, 124), new Sloth(3, 12)};

	// add the key-value pairs to the hash table
	for (int i = 0; i < keys.length; i++) {
	    slakoth.add(keys[i], values[i]);
	    System.out.println(slakoth);
	}
	slakoth.add(17, new Sloth(5, 124));	// modify an existing key's value
	System.out.println(slakoth);

	// test the get method
	for (Integer i : keys)
	    System.out.println(slakoth.get(i));
	
	// test the remove method
	slakoth.remove(47);
	System.out.println(slakoth);
	System.out.println(slakoth.get(27));
    }
}
