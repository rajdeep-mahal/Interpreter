package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode {
    private ArrayList<String> arguments;
    private int destinationAddr;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.pushReturnAddress(virtualMachine.getProgramCounter());
        virtualMachine.setProgramCounter(destinationAddr - 1);
    }

    public void setDestinationAddr(int destinationn) {
        destinationAddr = destinationn;
    }

    public String toString() {
        String returnString = "CALL" + " " + this.getArguments().get(0) + "    ";
        String arg0 = arguments.get(0);
        int baseID = arg0.indexOf("<<");
        for (int i = 0; i < baseID; i++) {
            returnString += arg0.charAt(i);
        }
        if (baseID == -1) {
            returnString += arguments.get(0);
        }
        return returnString;
    }
}
