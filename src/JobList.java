/////////////////////////////////////////////////////////////////////////////
// Semester: CS367 Spring 2017 
// PROJECT: p2 
// FILE: JobList.java 
// 
// TEAM: Team 82 (Baked Goods Dinosaurs) 
// Authors 
// Author1: Connor Beckerle, cbeckerle@wisc.edu, cbeckerle, 003 
// Author2: Zhiheng Wang, zwang759@wisc.edu, zwang759, 003
// Author3: Chase Wember, cwember@wisc.edu, cwember, 003 
// Author4: Matt Marcouiller, mcmarcouille@wisc.edu, mcmarcouille, 003
// Author5: Savannah Mann, smann5@wisc.edu, smann5, 003 
// Author6: Evangeline Li, zli483@wisc.edu, zli483, 003 
// 
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: N/A 
// 
// Online sources: N/A 
//////////////////////////// 80 columns wide //////////////////////////////////


import java.util.Iterator;

public class JobList implements ListADT<Job>{
	
	Listnode<Job> header = new Listnode<Job>(null); //header reference
	Listnode<Job> curr = new Listnode<Job>(null); //used for adding in list
	Listnode<Job> removedNode = new Listnode<Job>(null); 
	//used for tracking removed nodes
	int counter = 0; //used for counting size
	
	/** Adds an item at the end of the list
	    * @param item
	     *              an item to add to the list
	     *@throws IllegalArgumentException
	     *              if item is null
	     */
	@Override
	public void add(Job item) {
		if (item==null){
			throw new IllegalArgumentException();
		}
		
		curr = header;
		
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}
		curr.setNext(new Listnode<Job>(item));
		}
	
	/** Add an item at any position in the list
     * @param item
     *              an item to be added to the list
     * @param pos
     *              position at which the item must be added. 
     *              Indexing starts from 0
     * @throws IllegalArgumentException
     *              if item is null
     * @throws IndexOutOfBoundsException
     *              if pos is less than 0 or greater than size()
     */
	@Override
	public void add(int pos, Job item) {
		Listnode<Job> addThis = new Listnode<Job>(item);
		
		if (item == null) {
			throw new IllegalArgumentException();
		}
		
		if (pos < 0 || pos > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		curr = header;
		for (int i = 0; i < pos; i++) {
			curr = curr.getNext();
		}
		addThis.setNext(curr.getNext());//point new element to the right half
										// of list
		curr.setNext(addThis);
	}


	 /** Check if a particular item exists in the list
     * @param item
     *              the item to be checked for in the list
     * @return true
     *              if value exists, else false
     * @throws IllegalArgumentException
     *              if item is null
     */
	@Override
	public boolean contains(Job item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		
		curr = header;
		
		while (curr.getNext()!=null){
			
			if (item.equals(curr.getData())){
				return true;
			}
			curr = curr.getNext();
		}
		return false;
	}

	/** Returns the position of the item to return
     * @param pos
     *              position of the item to be returned
     * @throws IndexOutOfBoundsException
     *              if position is less than 0 or greater than size() - 1
     */
	@Override
	public Job get(int pos) {
		if (pos < 0 || pos > size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		curr = header;
		
		for (int i = 0; i <= pos; i++){
			curr = curr.getNext();
		}
		return curr.getData();
	}

	/** Returns true if the list is empty
     * @return value is true if the list is empty
     *              else false
     */
	@Override
	public boolean isEmpty() {
		if (header.getNext() == null){
			return true;
		}
		return false;
	}

	/** Removes the item at the given positions
     * @param pos
     *          the position of the item to be deleted from the list
     * @return returns the item deleted
     * @throws IndexOutOfBoundsException
     *          if the pos value is less than 0 or greater than size() - 1
     */
	@Override
	public Job remove(int pos) {
		if (pos < 0 || pos > size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		curr = header;
		
		for (int i=0; i<pos; i++){
			curr = curr.getNext();
		}
		Listnode<Job> removedNode = curr.getNext();
		curr.setNext(curr.getNext().getNext());
		return removedNode.getData();
	}

	 /** Returns the size of the singly linked list
     * @return the size of the singly linked list
     */
	@Override
	public int size() {
		curr = header;
		
		for(counter =0; curr.getNext()!=null; counter++){
			curr=curr.getNext();
		}
		
		return counter;
	}

	
	/**Returns the corresponding iterator
	 * @return the corresponding iterator
	 */
	@Override
	public Iterator<Job> iterator() {
		return new JobListIterator(this);
	}

	
	
}