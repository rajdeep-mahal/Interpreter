package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends BopCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine virtualMachine) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter an integer: ");
        while (!in.hasNextInt()) {
            System.out.println("Please enter an integer:");
            in.next();
        }
        int val = in.nextInt();
        virtualMachine.pushRunTimeStack(val);
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return "READ";
    }


}
