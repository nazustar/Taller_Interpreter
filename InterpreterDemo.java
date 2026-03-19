import expressions.AndExpression;

public class InterpreterDemo {
    public static void main(String[] args) {
        //Las clausulas deben estar completas.
        Expression clausula = 
            new expression(
                new AndExpression("", null)
            )
    }
}
