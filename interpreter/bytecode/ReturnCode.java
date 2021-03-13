package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.popFrame();
        int returnAddr = virtualMachine.popReturnAddress();
        virtualMachine.setProgramCounter(returnAddr);
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        String returnString = "RETURN";

        if (arguments.size() == 1) {
            returnString += " " + arguments.get(0) + "    exit ";
            String arg0 = arguments.get(0);
            int baseID = arg0.indexOf("<<");
            for (int i = 0; i < baseID; i++) {
                returnString += arg0.charAt(i);
            }
            if (baseID == -1) {
                returnString += arguments.get(0);
            }
            returnString += ":";
        }
        return returnString;
    }
}