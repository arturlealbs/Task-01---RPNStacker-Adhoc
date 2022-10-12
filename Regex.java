public class Regex {
	
	
	public static boolean isNum(String input) {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
	
	public static boolean isOP(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }

}