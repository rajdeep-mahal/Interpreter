package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    private ArrayList<String> arguments;
    private int destinationAddr;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine virtualMachine) {
        int val = virtualMachine.popRunStack();
        if (val == 0) {
            virtualMachine.setProgramCounter(destinationAddr - 1);
        }
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public void setDestinationAddr(int destinationn) {
        destinationAddr = destinationn;
    }

    public String toString() {
        return ("FALSEBRANCH" + " " + arguments.get(0));
    }


}
