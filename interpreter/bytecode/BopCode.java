package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine virtualMachine) {
        String operation = arguments.get(0);
        int operandOne = 0;
        int operandTwo = 0;
        if (operation.equals("+") || operation.equals("-") || operation.equals("/") || operation.equals("*") || operation.equals("==") || operation.equals("!=") || operation.equals("<=") || operation.equals(">") || operation.equals(">=") || operation.equals("<") || operation.equals("|") || operation.equals("&")) {
            operandTwo = virtualMachine.popRunStack();
            operandOne = virtualMachine.popRunStack();
        }

        switch (operation) {
            case "+":
                virtualMachine.pushRunTimeStack(operandOne + operandTwo);
                break;

            case "-":
                virtualMachine.pushRunTimeStack(operandOne - operandTwo);
                break;

            case "/":
                virtualMachine.pushRunTimeStack(operandOne / operandTwo);
                break;

            case "*":
                virtualMachine.pushRunTimeStack(operandOne * operandTwo);
                break;

            case "==":
                int is_equal;
                if (operandOne == operandTwo) {
                    is_equal = 1;
                } else {
                    is_equal = 0;
                }
                virtualMachine.pushRunTimeStack(is_equal);
                break;

            case "!=":
                int is_not_equal;
                if (operandOne != operandTwo) {
                    is_not_equal = 1;
                } else {
                    is_not_equal = 0;
                }
                virtualMachine.pushRunTimeStack(is_not_equal);
                break;

            case "<=":
                int is_less_equal;
                if (operandOne <= operandTwo) {
                    is_less_equal = 1;
                } else {
                    is_less_equal = 0;
                }
                virtualMachine.pushRunTimeStack(is_less_equal);
                break;

            case ">=":
                int is_greater_equal;
                if (operandOne >= operandTwo) {
                    is_greater_equal = 1;
                } else {
                    is_greater_equal = 0;
                }
                virtualMachine.pushRunTimeStack(is_greater_equal);
                break;

            case "<":
                int is_less_than;
                if (operandOne < operandTwo) {
                    is_less_than = 1;
                } else {
                    is_less_than = 0;
                }
                virtualMachine.pushRunTimeStack(is_less_than);
                break;

            case ">":
                int is_greater;
                if (operandOne > operandTwo) {
                    is_greater = 1;
                } else {
                    is_greater = 0;
                }
                virtualMachine.pushRunTimeStack(is_greater);
                break;

            case "&":
                int and;
                if ((operandOne == 1) && (operandTwo == 1)) {
                    and = 1;
                } else {
                    and = 0;
                }
                virtualMachine.pushRunTimeStack(and);
                break;

            case "|":
                int or;
                if ((operandOne == 1) || (operandTwo == 1)) {
                    or = 1;
                } else {
                    or = 0;
                }
                virtualMachine.pushRunTimeStack(or);
                break;
        }
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return ("BOP" + " " + arguments.get(0));
    }
}