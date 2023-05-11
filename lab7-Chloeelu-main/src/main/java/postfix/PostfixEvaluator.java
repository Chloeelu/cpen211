package postfix;

import java.util.Stack;
import parser.Token;
import parser.Scanner;
import parser.MalformedExpressionException;

/**
 *
 * @author Sathish Gopalakrishnan
 *
 * This class contains a method to evaluate an arithmetic expression
 * that is in Postfix notation (or Reverse Polish Notation).
 * See <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Wikipedia</a>
 * for details on the notation.
 *
 */
public class PostfixEvaluator {

    private String arithmeticExpr;

    /**
     * This is the only constructor for this class.
     * It takes a string that represents an arithmetic expression
     * as input argument.
     *
     * @param expr is a string that represents an arithmetic expression
     * <strong>in Postfix notation</strong>.
     */
    public PostfixEvaluator( String expr ) {
        arithmeticExpr = expr;
    }

    /**
     * This method evaluates the arithmetic expression that
     * was passed as a string to the constructor for this class.
     *
     * @return the value of the arithmetic expression
     * @throws MalformedExpressionException if the provided expression is not
     * 	a valid expression in Postfix notation
     */
    double eval( ) throws MalformedExpressionException {
        Stack<Token> stack;

        Scanner scanner = new Scanner(arithmeticExpr);
        stack = new Stack<>();
        Stack<Double> postfix=new Stack<>();
        int b=0;
        while (!scanner.isEmpty()) {
            Token currToken = scanner.getToken();
            System.out.println(currToken);
            boolean isinteger= currToken.isDouble();
            if(isinteger){
                postfix.push(currToken.getValue());
            }
            if(currToken.isVariable()) {
                if(postfix.size()>=2) {
                    double op1 = postfix.pop();
                    double op2 = postfix.pop();
                    String operator = currToken.getName();
                    if (operator.equals("+")) {
                        postfix.push(op1 + op2);
                    } else if (operator.equals("-")) {
                        postfix.push(op2 - op1);
                    } else if (operator.equals("*")) {
                        postfix.push(op2 * op1);
                    } else if (operator.equals("/")) {
                        postfix.push(op2 / op1);
                    }
                }else{b=1;}
            }
                scanner.eatToken();
        }

        if (postfix.size() == 1&&b==0) {
            Double t = postfix.pop();
            return t;
        }
        throw new MalformedExpressionException("Incorrect expression");

    }

}