/////////////////////////////////////////////////////////////////////////////
// Semester: CS367 Spring 2017 
// PROJECT: p2 
// FILE: JobListIterator.java 
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
import java.util.NoSuchElementException;

public class JobListIterator implements Iterator<Job>{
	
	Listnode<Job> currNode; //keeps track of the current node being used

	//constructor
	public JobListIterator(JobList list) {
		this.currNode = list.header;
	}

	
	/*
	 * checks to see if the next element is null
	 * @returns boolean for hasNext
	 */
	@Override
	public boolean hasNext() {
		return (currNode.getNext() != null);
	}

	
	/*
	 *returns the next element in the JobList
	 */
	@Override
	public Job next() {
		if (! hasNext()) {
			throw new NoSuchElementException();
		}
		currNode = currNode.getNext();
		return currNode.getData();
	}

}