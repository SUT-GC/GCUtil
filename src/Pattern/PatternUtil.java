package Pattern;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
	public static boolean haveUpperAndLowerCasePattern(String src, String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(src);
		return matcher.matches();
	}
	public static boolean noUpperAndLowerCasePattern(String src, String regex){
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(src);
		return matcher.matches();
	}
}