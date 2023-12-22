
public class RedundantCandidateException extends Exception {

	private String candidate;

	RedundantCandidateException(String candidate) {
		this.candidate = candidate;
	}
	
	/**
	 * @return candidate's name that got the exception
	 */
	public String getCand() {
		return candidate;
	}
}
