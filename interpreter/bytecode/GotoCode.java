package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode {
    private ArrayList<String> arguments;
    private int destinationAddr;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setProgramCounter(destinationAddr - 1);
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public void setDestinationAddr(int destionationn) {
        destinationAddr = destionationn;
    }

    public String toString() {
        return ( "GOTO" + " " + arguments.get(0));
    }
}