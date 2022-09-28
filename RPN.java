import java.io.*;
import java.util.*;



public class RPN {
    public static void main(String[] args) throws Exception {
        //Obter o arquivo que quer ser lido
        File file = new File("./Input.txt");
        Scanner sc = new Scanner(file);
        
        //Creando a pilha
        Stack<Double>  pile = new Stack<>();
        List<Token> tokens = new ArrayList<>();

        System.out.println(scan(sc, pile, tokens));
       
    }
    
    public static Double scan(Scanner sc , Stack<Double> pile, List<Token> tokens) throws Exception{
        Double result = null;
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (isDouble(input))
            {
                tokens.add(new Token(TokenType.NUM, input));
                pile.push(Double.parseDouble(input));
            } 
            else if(isOperator(input))
            {
                Token token = getOperatorToken(input);
                tokens.add(token);
                result = operate (token, pile);
            }
            else 
            {
                throw new Exception("Error: Unexpected character: " + input);
            }
        }
        printTokens(tokens);
        System.out.println();
        return result;
    }

    public static void printTokens(List<Token> tokens){
        for (Token token : tokens){
            System.out.println(token.toString());
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

    public static Token getOperatorToken(String input){
        if (input.equals("+")){
            return new Token(TokenType.PLUS, input);
        }
        else if (input.equals("-")){
            return new Token(TokenType.MINUS, input);
        }
        else if (input.equals("*")){
            return new Token(TokenType.STAR, input);
        }
        else{
            return new Token(TokenType.SLASH, input);
        }
    }

    public static Double operate(Token token, Stack<Double> pile) throws Exception{
        double y = pile.pop();
        double x = pile.pop();
        TokenType operator = token.type;

        switch(operator){
            case PLUS:
                Double result = x + y;
                pile.push(result);
                return result;
            case MINUS:
                result = x - y;
                pile.push(result);
                return result;
            case STAR:
                result = x * y;
                pile.push(result);
                return result;
            case SLASH:
                if (y == 0){
                    throw new Exception("Can't Divide by 0");
                } else {
                    result = x / y;
                    pile.push(result);
                    return result;
                }
            default:
                result = null;
                return result;
            }
        }
            
    }

