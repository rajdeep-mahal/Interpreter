package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.Store(Integer.parseInt(arguments.get(0)));
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        String returnString = "STORE" + " " + arguments.get(0);

        if (arguments.size() == 2) {
            returnString += " " + arguments.get(1) + "    " + arguments.get(1) + " = ";
        }
        return returnString;
    }

}
