package Arithmetics;

public class Task {
    private Expression expression;

    public Task(int level){
        expression = new Expression(level * 5);
    }

    public String getAnswerAsText(){
        return expression.getAnswer();
    }

    public String getAsText(){
        return expression.getExpressionToDisplay();
    }
}