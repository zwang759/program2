/////////////////////////////////////////////////////////////////////////////
// Semester: CS367 Spring 2017 
// PROJECT: p2 
// FILE: Scoreboard.java 
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


public class Scoreboard implements ScoreboardADT{
	
	
	JobList complete = new JobList(); //stores completed Jobs
	
	
	/**
     * Calculates the total combined number of points for every job in the 
     * scoreboard.
     * @return The summation of all the points for every job currently stored 
     * in the scoreboard.
     */
	@Override
	public int getTotalScore() {
		int totalPoints = 0;
		for (Job job : complete) {
			totalPoints += job.getPoints();
		}
		return totalPoints;
	}
	 /**
     * Inserts the given job at the end of the scoreboard.
     * 
     * @param job 
     * 		The job that has been completed and is to be inserted into the 
     *      list.
     */
	@Override
	public void updateScoreBoard(Job job) {
		complete.add(job);	
	}
	 /**
     * Prints out a summary of all jobs currently stored in the scoreboard. 
     * The formatting must match the example exactly.
     */
	@Override
	public void displayScoreBoard() {
		Iterator<Job> iter = complete.iterator();
		System.out.println("The jobs completed:");
		for (int i = 0; i < complete.size(); i++){
			Job currJob = iter.next();
			System.out.println("Job Name: " + currJob.getJobName());
			System.out.println("Points earned for this job: " + currJob.getPoints());
			System.out.println("--------------------------------------------");
		}
		
	}

}