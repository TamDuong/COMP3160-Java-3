/* Implements the Map interface using a hash table with chaining.  With
 * chaining, each index of the table holds a list of entries (rather than
 * a single entry).
 */

import java.util.LinkedList;

public class HashMapChained<K, V> implements Map<K, V>
{
    // where the table's data is kept (an array of linked lists; each list holds MapEntry objects)
    private LinkedList<MapEntry<K, V>>[] data;
    
    public HashMapChained(int capacity)
    {
	data = new LinkedList[capacity];
	for (int i = 0; i < capacity; i++)	// instantiate a LinkedList object at each index in the data array
	    data[i] = new LinkedList<>();
    }
    
    @Override
    public void add(K key, V value)
    {
	int index = hash(key);	// hash the key to determine which list to go to
	for (MapEntry<K, V> e : data[index]) {	// search that list for the key
	    if (key.equals(e.getKey())) {	// key found - change the existing entry's value
		e.setValue(value);
		return;
	    }
	}
	// key not found - add a new MapEntry object to the end of the list
	data[index].add(new MapEntry<K, V>(key, value));
    }

    @Override
    public void remove(K key)
    {
	int index = hash(key);	// hash the key to determine which list to go to
	for (int i = 0; i < data[index].size(); i++) {	// search that list for the key
	    if (key.equals(data[index].get(i).getKey())) {	// key found - remove that entry from the list
		data[index].remove(i);
		return;
	    }
	}
	// do nothing if key not found
    }

    @Override
    public V get(K key)
    {
	int index = hash(key);	// hash the key to determine which list to go to
	for (MapEntry<K, V> e : data[index]) {	// search that list for the key
	    if (key.equals(e.getKey()))		// key found - return its associated value
		return e.getValue();
	}
	return null;	// key not found
    }
    
    // Hash function (same one used for HashMapOpen)
    private int hash(K key)
    {
	int index = key.hashCode() % data.length;
	if (index < 0)
	    index += data.length;
	return index;
    }
    
    public String toString()
    {
	String r = "HashMapChained:\n";
	for (int i = 0; i < data.length; i++)
	    r += i + " - " + data[i] + "\n";
	return r;
    }
    
    // Same test main as the one we used for HashMapOpen
    public static void main(String[] args)
    {
	Map<Integer, Sloth> slakoth = new HashMapChained<>(5);
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
