package Arithmetics;

import java.util.*;

public class Expression extends Random {
    private final String[] operator = {"+", "-", "*"};
    private int bound;
    private int answer;
    private List<String> expressionToDisplay;
    private List<String> expressionPostfix;
    private PostfixConverter converter;

    public Expression(int bound){
        this.bound = bound;
        converter = new PostfixConverter();
        generateExpression();
        converter.setExpressionToConvert(expressionToDisplay);
        expressionPostfix = converter.convert();
        calculateAnswer();
    }

    private void generateExpression(){
        expressionToDisplay = new ArrayList<>();
        int operatorsNumber = nextInt(bound/5)+1;
        boolean generateOperator = false;
        int multiplicationOperatorsInARow = 0;

        for(int i = 0; i < operatorsNumber*2+1; i++){

            if(generateOperator){

                String tempOperator = operator[nextInt(operator.length)];

                //not allowing two multiplication operators in a row
                if(tempOperator.equals("*")){
                    multiplicationOperatorsInARow++;
                }

                if(multiplicationOperatorsInARow == 2){
                    tempOperator = operator[nextInt(operator.length-1)];
                    multiplicationOperatorsInARow = 0;
                }

                expressionToDisplay.add(tempOperator);
                generateOperator = false;

            }
            else{
                String tempOperand = Integer.toString(nextInt(bound));
                expressionToDisplay.add(tempOperand);
                generateOperator = true;
            }
        }
    }


    private void calculateAnswer(){
        Stack<String> stack = new Stack<>();

        for(int i = 0; i < expressionPostfix.size(); i++){
            String currentElement = expressionPostfix.get(i);

            if(isOperator(currentElement)){
                int op2 = Integer.parseInt(stack.pop());
                int op1 = Integer.parseInt(stack.pop());
                stack.push(Integer.toString(getTempAnswer(op1, op2, currentElement)));
            }
            else{
                stack.push(currentElement);
            }
        }

        answer = Integer.parseInt(stack.pop());
    }

    private boolean isOperator(String oper){

        for(int i = 0; i < operator.length; i++){
            if(oper.equals(operator[i])){
                return true;
            }
        }

        return false;
    }

    private int getTempAnswer(int num1, int num2, String oper){
        if(oper.equals("+")){
            return num1+num2;
        }
        else if(oper.equals("-")){
            return num1-num2;
        }
        else if(oper.equals("*")){
            return num1*num2;
        }
        else if(oper.equals("/")){
            return num1/num2;
        }

        return 0;
    }

    public String getExpressionPostfix(){
        String result = "";
        for(int i = 0; i < expressionPostfix.size(); i++){
            result = result + expressionPostfix.get(i) + " ";
        }

        return result;
    }

    public String getExpressionToDisplay() {
        String expression = "";

        for(int i = 0; i < expressionToDisplay.size(); i++){
            expression = expression + expressionToDisplay.get(i) + " ";
        }

        return expression;
    }

    public String getAnswer() {
        return Integer.toString(answer);
    }
}








