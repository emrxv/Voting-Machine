import java.util.LinkedList;
import java.util.HashMap;

public class VotingData {

	private LinkedList<String> ballot = new LinkedList<String>();
	private HashMap<String, Integer> vote1 = new HashMap<String, Integer>();
	private HashMap<String, Integer> vote2 = new HashMap<String, Integer>();
	private HashMap<String, Integer> vote3 = new HashMap<String, Integer>();

	VotingData() {

		this.getBallot().add("Gompei");
		this.getBallot().add("Husky");
		vote1.put("Gompei", 0);
		vote1.put("Husky", 0);
	}

	/**
	 * @param firstVote place vote
	 * @param secondVote place vote
	 * @param thirdVote place vote
	 * @throws CandidateNotFoundException if the candidate does not exist
	 * @throws CandidateChosenMoreThanOnceException if the candidate is voted for twice
	 */
	public void submitVote(String firstVote, String secondVote, String thirdVote)
			throws CandidateNotFoundException, CandidateChosenMoreThanOnceException {

		if (getBallot().contains(firstVote) && getBallot().contains(secondVote) && getBallot().contains(thirdVote)
				&& !(firstVote.equals(thirdVote)) && !(firstVote.equals(secondVote)) && !(secondVote.equals(thirdVote))) {

			for (String x : vote1.keySet()) {

				if (x.equals(firstVote)) {
					vote1.put(x, vote1.get(x) + 1);
				}
			}

			for (String x : vote2.keySet()) {

				if (x.equals(secondVote)) {

					vote2.put(x, vote2.get(x) + 1);
				}
			}

			for (String x : vote3.keySet()) {

				if (x.equals(thirdVote)) {

					vote3.put(x, vote3.get(x) + 1);
				}
			}
		} 
		else if (!getBallot().contains(firstVote)) {
			throw new CandidateNotFoundException(firstVote);
		} 
		else if (!getBallot().contains(secondVote)) {
			throw new CandidateNotFoundException(secondVote);
		} 
		else if (!getBallot().contains(thirdVote)) {
			throw new CandidateNotFoundException(thirdVote);
		} 
		else if (firstVote.equals(thirdVote)) {
			throw new CandidateChosenMoreThanOnceException(firstVote);
		} 
		else if (secondVote.equals(thirdVote)) {
			throw new CandidateChosenMoreThanOnceException(secondVote);
		} 
		else {
			throw new CandidateChosenMoreThanOnceException(thirdVote);
		}
	}

	/**
	 * @param candidate of the candidate being added
	 * @throws RedundantCandidateException if the candidate is on the ballot
	 */
	public void nominateCandidate(String candidate) throws RedundantCandidateException {

		if (getBallot().contains(candidate)) {
			
			throw new RedundantCandidateException(candidate);
		} 
		else {
			
			vote1.put(candidate, 0);
			vote2.put(candidate, 0);
			vote3.put(candidate, 0);
			ballot.add(candidate);
		}

	}

	/**
	 * @return the winner of the vote using the percent method. 
	 */
	public String pickWinnerMostFirstChoice() {

		double percent = 0;
		
		double total = 0;

		for (String x : getBallot()) {
			
			total = total + vote1.get(x);
		}

		for (String y : getBallot()) {
			
			percent = vote1.get(y) / total;
			
			if (percent > 0.5) {
				
				return y;
			}
		}

		return "*Requires Runoff Poll*";

	}

	/**
	 * @return the winner using agreeable method
	 */
	public String pickWinnerMostAgreeable() {

		int a = 0;
		int b = 0;
		int c = 0;
		int max = 0;
		int points = 0;

		String winner = getBallot().getFirst();

		for (String x : getBallot()) {
			
			if (vote1.containsKey(x)) {
				
				a = vote1.get(x);
			}
			if (vote2.containsKey(x)) {
				
				b = vote2.get(x);
			}
			if (vote3.containsKey(x)) {
				
				c = vote3.get(x);
			}
			
			if (a > b) {
				
				points = a;
				
			} else if (b > c) {
				
				points = b;
				
			} else {
				
				points = c;
			}

			if (max < points) {
				max = points;
				winner = x;
			}
		}
		return winner;

	}

	/**
	 * @return the ballot
	 */
	public LinkedList<String> getBallot() {
		
		return ballot;
	}
}