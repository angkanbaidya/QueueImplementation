//ANGKAN BAIDYA
//112309655
//R01

/**
 * The interface to be implemented  by the Phrase class
 *
 *
 */
public interface Queue {

    /**
     * Adds the input to the rear of the queue
     *
     */
    public void enqueue(Bigram b);

    /**
     * Removes the first element from the front of the queue and returns it
     *
     */
    public Bigram dequeue();

    /**
     * Returns the first element from the front of the queue without removing it
     *
     */
    public Bigram peek();





}
