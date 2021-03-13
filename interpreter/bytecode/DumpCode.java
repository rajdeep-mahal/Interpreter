package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine virtualMachine) {
        if (arguments.get(0).equals("ON")) {
            virtualMachine.DumpOn();
        } else {
            virtualMachine.DumpOff();
        }
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return ("DUMP" + " " + arguments.get(0));
    }
}