import java.io.*;
import java.util.*;



public class RPN {
    public static void main(String[] args) throws Exception {
        //Obter o arquivo que quer ser lido
        File file = new File("./Input.txt");
        HashMap<String,String> variableInterpreter = new HashMap<String,String>();
        variableInterpreter.put("y", "10");
        Scanner sc = new Scanner(file);
        List<Token> tokens = scan(sc, variableInterpreter);

        
        System.out.println(interpreter(tokens));
       
    }
    
    public static List<Token> scan(Scanner sc, HashMap<String, String> interpreter) throws Exception{
        List<Token> tokens = new ArrayList<>();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (Regex.isNum(input))
            {
                tokens.add(new Token(TokenType.NUM, input));
            } 
            else if(Regex.isOP(input))
            {
                Token token = getOperatorToken(input);
                tokens.add(token);
            }
            else 
            {
                if(interpreter.get(input) != null){
                    tokens.add(new Token(TokenType.NUM, interpreter.get(input)));
                } else{
                    throw new Exception("Error: Unexpected character: " + input);
                }
                
            }
        }
        return tokens;
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
    
    public static Double interpreter(List<Token> tokens) throws Exception{
        Double result = null;
        Stack<Double>  pile = new Stack<>();
        for (Token token : tokens){
            System.out.println(token.toString());
            TokenType operator = token.type;
            
            switch(operator){
                case PLUS:
                    double y = pile.pop();
                    double x = pile.pop();
                    result = x + y;
                    pile.push(result);
                    break;
                case MINUS:
                    y = pile.pop();
                    x = pile.pop();
                    result = x - y;
                    pile.push(result);
                    break;
                case STAR:
                    y = pile.pop();
                    x = pile.pop();
                    result = x * y;
                    pile.push(result);
                    break;
                case SLASH:
                    y = pile.pop();
                    x = pile.pop();
                    if (y == 0){
                        throw new Exception("Can't Divide by 0");
                    } else {
                        result = x / y;
                        pile.push(result);
                    }
                    break;
                case NUM:
                    pile.push(Double.parseDouble(token.lexeme));
                    break;
                default:
                    result = null;
                    break;
                }

        }
        System.out.println();
        return result;
    }
}