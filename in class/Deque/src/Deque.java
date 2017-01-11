/**
 * Interface specifying the basic operations for a deque (double-ended queue).
 * A deque operates like a regular queue, but it allows the enqueue and dequeue
 * operations to be done from either end of the queue.
 * 
 */
public interface Deque<E>
{
    // remember that all methods in an interface are public and abstract by default
    
    // Returns whether the deque is empty.
    boolean isEmpty();
    
    // Returns (but does not remove) the element in the front of the deque.
    E peekFront();
    
    // Returns (but does not remove) the element in the back of the deque.
    E peekBack();

    // Adds the specified newItem to the front of the deque.
    void enqueueFront(E newItem);

    // Adds the specified newItem to the back of the deque.
    void enqueueBack(E newItem);

    // Returns and removes the element in the front of the deque.
    E dequeueFront();

    // Returns and removes the element in the back of the deque.
    E dequeueBack();
}
