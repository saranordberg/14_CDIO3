package cdio.client.validate;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
public class PasswordValidator{
	
	  private RegExp pattern;
	  private MatchResult matcher;
 
	  private static final String PASSWORD_PATTERN = 
              "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])|(?=.*[@#$%!-]).{8,20})";
	        
	  public PasswordValidator(){
		  pattern = RegExp.compile(PASSWORD_PATTERN);
	  }
	  
	  /**
	   * Validate password with regular expression
	   * @param password password for validation
	   * @return true valid password, false invalid password
	   */
	  public boolean validate(final String password){
		  
		  matcher = pattern.exec(password);
		  return pattern.test(password);
	    	    
	  }
}