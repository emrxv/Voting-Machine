import java.util.LinkedList;
import java.util.Scanner;

public class PollingDevice {
	
	private Scanner keyboard = new Scanner(System.in);
	
	private VotingData data = new VotingData();

	public PollingDevice(VotingData data) {
		this.data = data;
	}

	/**
	 * Prints the ballot
	 */
	public void printBallot() {
		
		System.out.println("The candidates are ");
		
		for (String s : data.getBallot()) {
			System.out.println(s);
		}
	}
	
	/**
	 * Voting Device that holds the input and output
	 * @throws CandidateChosenMoreThanOnceException invalid when voted on the same candidate twice
	 * @throws CandidateNotFoundException when a candidate doesn't exist
	 * @throws RedundantCandidateException when a candidate already exists in the ballot
	 */
	public void screen() throws CandidateChosenMoreThanOnceException, CandidateNotFoundException, RedundantCandidateException {
		
		this.printBallot();
		
		System.out.println("First Vote:");
		String cand1 = keyboard.next();
		System.out.println("Second Vote:");
		String cand2 = keyboard.next();
		System.out.println("Third Vote:");
		String cand3 = keyboard.next();
		
		try {
			
			data.submitVote(cand1, cand2, cand3);
			System.out.println("Votes Submitted!");
			
		} catch (CandidateNotFoundException e) {
			
			System.out.println("Candidate not found. Add Candidate? (Y/N)");
			String input = keyboard.next();
			
			if (input.equals("Y") && input.equals("y")) {
				
				try {
					addWriteIn(e.getCand());
					System.out.println("Candidate Added Successfully!");
				} 
				
				catch (RedundantCandidateException e1) {
					System.out.println("Candidate Already Exists.");
				}
			}
			
		} catch (CandidateChosenMoreThanOnceException e) {
			
			System.out.println("!Cannot Vote Twice on same Candidate! Restarting Voting Process... ");
			
			try {
				
				addWriteIn(e.getCand());
				System.out.println("Candidate Added Successfully!");
				
			} catch (RedundantCandidateException e1) {
				System.out.println("Candidate Already Exists.");
			}
		}
	}

	
	/**
	 * Begins the nomination process
	 * @param candidate name of candidate being nominated
	 * @throws RedundantCandidateException when a candidate 
	 * already exists in the ballot and is being nominated
	 */
	public void addWriteIn(String candidate) throws RedundantCandidateException {
		
		data.nominateCandidate(candidate);
	}
	
}