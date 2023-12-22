
public class CandidateNotFoundException extends Exception {
	
	private String candidate;
	
	 public CandidateNotFoundException(String candidate) {
		this.candidate = candidate;
	}

	 
	 /**
		 * @return candidate's name that got the exception
		 */
	public String getCand() {
		 return candidate;
	 }
}
