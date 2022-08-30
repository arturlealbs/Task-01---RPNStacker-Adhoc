import java.io.*;
import java.util.*;

public class RPN {
    public static void main(String[] args) throws Exception {
        //Obter o arquivo que quer ser lido
        File file = new File("./Input.txt");
        Scanner sc = new Scanner(file);
        
        //Creando a pilha
        Stack<Double>  pile = new Stack<>();
        Double result = null;
        
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (isDouble(input)){
                pile.push(Double.parseDouble(input));
            } else if(isOperator(input)){
                result = operate (input, pile);
            }else {
                sc.close();
            }
        }
        if (result != null){
            System.out.println(result);
        }
    }
    
    public static boolean isDouble(String input) {
    try {
        Double.parseDouble(input);
    } catch (NumberFormatException ex) {
        return false;
    }
    return true;
    }

    public static boolean isOperator(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }

    public static double operate(String operator, Stack<Double> pile) throws Exception{
        double y = pile.pop();
        double x = pile.pop();
        
        if (operator.equals("+")){
            double result = x + y;
            pile.push(result);
            return result;
        } else if (operator.equals("-")) {
            double result = x - y;
            pile.push(result);
            return result;
        } else if (operator.equals("*")){
            double result = x * y;
            pile.push(result);
            return result;
        } else {
            if (y == 0){
                throw new Exception("Can't Divide by 0");
            } else {
                double result = x / y;
                pile.push(result);
                return result;
            }
        }
    }
}
