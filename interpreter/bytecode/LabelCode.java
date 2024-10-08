package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine virtualMachine) {
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return ("LABEL" + " " + arguments.get(0));
    }
}
