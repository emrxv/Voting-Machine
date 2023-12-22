import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

public class Examples {


	@Test(expected = CandidateChosenMoreThanOnceException.class)
	public void candidateChoice()
			throws RedundantCandidateException, CandidateNotFoundException, CandidateChosenMoreThanOnceException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Adem");
		voteData.submitVote("Adem", "Gompei", "Adem");
	}

	@Test(expected = CandidateChosenMoreThanOnceException.class)
	public void candidateChoice2()
			throws RedundantCandidateException, CandidateNotFoundException, CandidateChosenMoreThanOnceException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Adem");
		voteData.submitVote("Adem", "Adem", "Husky");
	}

	@Test(expected = CandidateNotFoundException.class)
	public void candidateNotFound()
			throws RedundantCandidateException, CandidateNotFoundException, CandidateChosenMoreThanOnceException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Adem");
		voteData.submitVote("Adem", "Gompei", "Emre");
	}

	@Test(expected = CandidateNotFoundException.class)
	public void candidateNotFound2()
			throws RedundantCandidateException, CandidateNotFoundException, CandidateChosenMoreThanOnceException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Adem");
		voteData.submitVote("Emre", "Husky", "Adem");
	}

	@Test(expected = CandidateNotFoundException.class)
	public void candidateNotFound3()
			throws RedundantCandidateException, CandidateNotFoundException, CandidateChosenMoreThanOnceException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Adem");
		voteData.submitVote("Emre", "Gompei", "Husky");
	}

	@Test
	public void SubmitVote()
			throws RedundantCandidateException, CandidateNotFoundException, CandidateChosenMoreThanOnceException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Adem");
		voteData.submitVote("Husky", "Gompei", "Adem");
		voteData.submitVote("Adem", "Gompei", "Husky");
		voteData.submitVote("Husky", "Gompei", "Adem");
	}

	@Test
	public void checkNomination() throws RedundantCandidateException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Adem");
		voteData.nominateCandidate("Mike");
		voteData.nominateCandidate("Shea");

		LinkedList<String> ballot = new LinkedList<String>();

		ballot.add("Gompei");
		ballot.add("Husky");
		ballot.add("Adem");
		ballot.add("Mike");
		ballot.add("Shea");

		assertEquals(voteData.getBallot(), ballot);
	}

	@Test(expected = RedundantCandidateException.class)
	public void checkNominationError() throws RedundantCandidateException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Husky");
		voteData.nominateCandidate("Husky");
	}

	@Test
	public void Winners()
			throws RedundantCandidateException, CandidateNotFoundException, CandidateChosenMoreThanOnceException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Adem");
		voteData.nominateCandidate("Mike");
		voteData.nominateCandidate("Shea");
		voteData.submitVote("Adem", "Mike", "Shea");
		voteData.submitVote("Adem", "Gompei", "Shea");
		voteData.submitVote("Adem", "Husky", "Shea");
		voteData.submitVote("Mike", "Husky", "Shea");
		assertEquals("Adem", voteData.pickWinnerMostFirstChoice());
	}

	@Test
	public void Runoff()
			throws RedundantCandidateException, CandidateNotFoundException, CandidateChosenMoreThanOnceException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Adem");
		voteData.nominateCandidate("Mike");
		voteData.nominateCandidate("Shea");
		voteData.submitVote("Adem", "Gompei", "Shea");
		voteData.submitVote("Mike", "Husky", "Shea");
		voteData.submitVote("Mike", "Husky", "Shea");
		voteData.submitVote("Adem", "Mike", "Shea");

		assertEquals("*Requires Runoff Poll*", voteData.pickWinnerMostFirstChoice());
	}

	@Test
	public void mostWinners()
			throws CandidateNotFoundException, CandidateChosenMoreThanOnceException, RedundantCandidateException {

		VotingData voteData = new VotingData();

		voteData.nominateCandidate("Shea");
		voteData.nominateCandidate("Adem");
		voteData.nominateCandidate("Mike");
		voteData.submitVote("Adem", "Mike", "Shea");
		voteData.submitVote("Adem", "Husky", "Shea");
		voteData.submitVote("Adem", "Husky", "Shea");
		voteData.submitVote("Mike", "Husky", "Shea");

		assertEquals("Shea", voteData.pickWinnerMostAgreeable());

	}

	@Test
	public void noCands() throws CandidateChosenMoreThanOnceException, RedundantCandidateException {

		boolean exception = false;

		try {

			VotingData voteData = new VotingData();

			voteData.nominateCandidate("Adem");
			voteData.submitVote("Emre", "Gompei", "Adem");

		} 
		catch (CandidateNotFoundException e) {

			exception = true;

		}
		assertTrue(exception);
	}

	@Test
	public void moreCands() throws RedundantCandidateException, CandidateNotFoundException {

		boolean exception = false;

		try {

			VotingData voteData = new VotingData();

			voteData.nominateCandidate("Adem");
			voteData.submitVote("Adem", "Gompei", "Adem");

		} 
		catch (CandidateChosenMoreThanOnceException e) {

			exception = true;

		}
		assertTrue(exception);
	}
}
