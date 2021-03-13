package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.pushRunTimeStack(Integer.parseInt(arguments.get(0)));
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        String returnString = "LIT" + " " + arguments.get(0);
        if (arguments.size() == 2) {
            returnString += " " + arguments.get(1) + "   int " + arguments.get(1);
        }
        return returnString;
    }

}