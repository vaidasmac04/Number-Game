package Arithmetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostfixConverter {

    private Stack<String> stack;
    private List<String> input;
    private List<String> output;

    public PostfixConverter() {
        input = new ArrayList<>();
        output = new ArrayList<>();
        stack = new Stack();
    }

    public void setExpressionToConvert(List<String> expression){
        input = expression;
    }

    public List<String> convert() {
        for (int j = 0; j < input.size(); j++) {
            String element = input.get(j);

            if(element.equals("+") || element.equals("-")){
                handleOperator(element, 1);
            }
            else if(element.equals("*")){
                handleOperator(element, 2);
            }
            else{
                output.add(element);
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    public void handleOperator(String thisOperator, int precedence1) {
        while (!stack.isEmpty()) {
            String topElement = stack.pop();

            int precedence2;

            if (topElement == "+" || topElement == "-"){
                precedence2 = 1;
            }
            else{
                precedence2 = 2;
            }

            if (precedence2 < precedence1) {
                stack.push(topElement);
                break;
            }
            else {
                output.add(topElement);
            }
        }

        stack.push(thisOperator);
    }
}