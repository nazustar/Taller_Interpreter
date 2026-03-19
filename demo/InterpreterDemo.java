package demo;

import expressions.*;

public class InterpreterDemo {

    //The four OR expressions to validate the different components of the clause.

    //Here we validate the subject, aplying an OR expression.
    public static Expression getSubjectExpression() {
        Expression lessee = new TerminalExpression("arrendatario");
        Expression renter = new TerminalExpression("inquilino");
        return new OrExpression(lessee, renter);
    }

    //Here we validate the action, aplying an OR expression.
    public static Expression getActionExpression() {
        Expression pay = new TerminalExpression("pagará");
        Expression deposit = new TerminalExpression("abonará");
        return new OrExpression(pay, deposit);
    }

    //Here we validate the object, aplying an OR expression.
    public static Expression getObjectExpression() {
        Expression rent = new TerminalExpression("renta");
        Expression canon = new TerminalExpression("canon");
        return new OrExpression(rent, canon);
    }

    //Here we validate the time, aplying an OR expression.
    public static Expression getTimeExpression() {
        Expression monthly = new TerminalExpression("mensual");
        Expression advance = new TerminalExpression("anticipado");
        return new OrExpression(monthly, advance);
    }


    //Final clause, it must not contain the term "subarrendar".
    public static Expression getProhibitedClauseExpression() {
        Expression subject = getSubjectExpression();
        Expression action = getActionExpression();
        Expression object = getObjectExpression();
        Expression time = getTimeExpression();

        //Structure of the clause, it must contain all the components.
        Expression structure = new AndExpression(
                new AndExpression(subject, action),
                new AndExpression(object, time)
        );
        //Prohibited term, the clause must not contain "subarrendar".
        Expression prohibido = new TerminalExpression("subarrendar");
        
        //The final expression.
        return new AndExpression(structure, new NotExpression(prohibido));
    }

    public static void main(String[] args) {
        //Final expressions for test clauses.
        Expression clausulaFinal = getProhibitedClauseExpression();

        System.out.println("Validación de cláusulas:\n");
        //Test clauses.

        //This clause is valid because it contains all the required components.
        String c1 = "El arrendatario pagará la renta mensual";
        System.out.println(c1 + "\n¿Es una cláusula válida? :" + clausulaFinal.interpret(c1));

        //This clause is invalid because it does not contain the time component.
        String c2 = "El arrendatario pagará la renta";
        System.out.println(c2 + "\n¿Es una cláusula válida? :" + clausulaFinal.interpret(c2));

        //This clause is invalid because it contains the prohibited term "subarrendar".
        String c3 = "El inquilino pagará el canon anticipado y debería poder subarrendar";
        System.out.println(c3 + "\n¿Es una cláusula válida? :" + clausulaFinal.interpret(c3));

        //This clause is valid because it contains all the required components.
        String c4 = "El arrendatario abonará el canon anticipado";
        System.out.println(c4 + "\n¿Es una cláusula válida? :" + clausulaFinal.interpret(c4));
    }
}