public class Game {

	/**
	 * A list of all jobs currently in the queue.
	 */

	private JobList list;
	/**
	 * Whenever a Job is completed it is added to the scoreboard
	 */
	private Scoreboard scoreBoard;
	private int timeToPlay;
	private JobSimulator jobSimulator;

	/**
	 * Constructor. Initializes all variables.
	 * 
	 * @param seed
	 *            seed used to seed the random number generator in the
	 *            Jobsimulator class.
	 * @param timeToPlay
	 *            duration used to determine the length of the game.
	 */
	public Game(int seed, int timeToPlay) {
		this.timeToPlay = timeToPlay;
		jobSimulator = new JobSimulator(seed);
		list = new JobList();
		scoreBoard = new Scoreboard();
		/**
		 *   Initializes all member variables
		 */
	}

	/**
	 * Returns the amount of time currently left in the game.
	 * 
	 * @returns the amount of time left in the game.
	 */
	public int getTimeToPlay() {
		//   return the amount of time left
		return timeToPlay;
	}

	/**
	 * Sets the amount of time that the game is to be executed for. Can be used
	 * to update the amount of time remaining.
	 * 
	 * @param timeToPlay
	 *            the remaining duration of the game
	 */
	public void setTimeToPlay(int timeToPlay) {
		//   Setter for amount of time to play
		this.timeToPlay = timeToPlay;
	}

	/**
	 * States whether or not the game is over yet.
	 * 
	 * @returns true if the amount of time remaining in the game is less than or
	 *          equal to 0, else returns false
	 */
	public boolean isOver() {
		if (timeToPlay <= 0) {
			return true;
		}
		//   check if the game is over or not
		else {
			return false;
		}
	}

	/**
	 * This method simply invokes the simulateJobs method in the JobSimulator
	 * object.
	 */
	public void createJobs() {
		//*debug*/ System.out.println("simulate jobs for " + timeToPlay);
		jobSimulator.simulateJobs(list, timeToPlay);// confirm
		//   Invoke the simulator to create jobs

	}

	/**
	 * @returns the length of the Joblist.
	 */
	public int getNumberOfJobs() {
		//   Get the number of jobs in the JobList

		return list.size();
	}

	/**
	 * Adds a job to a given position in the joblist. Also requires to calculate
	 * the time Penalty involved in adding a job back into the list and update
	 * the timeToPlay accordingly
	 * 
	 * @param pos
	 *            The position that the given job is to be added to in the list.
	 * @param item
	 *            The job to be inserted in the list.
	 */
	public void addJob(int pos, Job item) {
		/**
		 *   Add a job in the list based on position
		 */
		list.add(pos, item);
		timeToPlay -= pos;
	}

	/**
	 * Adds a job to the joblist.
	 * 
	 * @param item
	 *            The job to be inserted in the list.
	 */
	public void addJob(Job item) {
		//   Add a job in the joblist
		list.add(item);
	}

	/**
	 * Given a valid index and duration, executes the given job for the given
	 * duration.
	 *
	 * This function should remove the job from the list and return it after
	 * applying the duration.
	 *
	 * This function should set duration equal to the amount of time remaining
	 * if duration exceeds it prior to executing the job. After executing the
	 * job for a given amount of time, check if it is completed or not. If it
	 * is, then it must be inserted into the scoreBoard. This method should also
	 * calculate the time penalty involved in executing the job and update the
	 * timeToPlay value accordingly
	 * 
	 * @param index
	 *            The job to be inserted in the list.
	 * @param duration
	 *            The amount of time the given job is to be worked on for.
	 */
	public Job updateJob(int index, int duration) {
		//   As per instructions in comments

		Job temp = list.remove(index);
		if (duration > timeToPlay) { 
			duration = timeToPlay;
		}

		int stepsRemaining = temp.getTimeUnits() - temp.getSteps();
		if (duration > stepsRemaining) {
			duration = stepsRemaining;
		}
		temp.setSteps(temp.getSteps()+duration);
		
		// After executing the job
		int timePenalty = 0;
		if (index != 0 && index != -1) {
			timePenalty = index;
		}
		// If you choose index -1, the time penalty is equal to the total number
		// of jobs in the list.
		else if (index == -1) {
			timePenalty = list.size();
		}

		if (temp.isCompleted() == true) {
			scoreBoard.updateScoreBoard(temp);
		}
		timeToPlay -= (duration + timePenalty);

		return temp;
	}

	/**
	 * This method produces the output for the initial Job Listing, IE: "Job
	 * Listing At position: job.toString() At position: job.toString() ..."
	 *
	 */
	public void displayActiveJobs() {
		//   Display all the active jobs
		System.out.println("Job Listing");
		
		//iterator
		for (int i = 0; i < list.size(); ++i) {
			System.out.println("At position: " + i + " "+list.get(i).toString());

		}

	}

	/**
	 * This function simply invokes the displayScoreBoard method in the
	 * ScoreBoard class.
	 */
	public void displayCompletedJobs() {
		scoreBoard.displayScoreBoard();
		//   Display all the completed jobs

	}

	/**
	 * This function simply invokes the getTotalScore method of the ScoreBoard
	 * class.
	 * 
	 * @return the value calculated by getTotalScore
	 */
	public int getTotalScore() {
		//   Return the total score accumulated
		return scoreBoard.getTotalScore();

	}
}