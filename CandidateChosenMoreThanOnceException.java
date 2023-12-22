
public class CandidateChosenMoreThanOnceException extends Exception {

	private String candidate;
	
	public CandidateChosenMoreThanOnceException(String candidate) {
		this.candidate = candidate;
	}
	
	/**
	 * @return candidate's name that got the exception
	 */
	public String getCand() {
		return candidate;
	}
}
