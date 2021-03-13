package interpreter.virtualmachine;

import interpreter.bytecode.*;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        dumping;

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram() {
        programCounter = 0;
        int topRunTimeStack = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<Integer>();
        isRunning = true;
        dumping = true;
        try {
            while (isRunning) {
                ByteCode code = program.getCode(programCounter);
                if (code instanceof StoreCode && dumping && ((StoreCode) code).getArguments().size() == 2) {
                    topRunTimeStack = runTimeStack.peek();
                }
                code.execute(this);
                if (dumping) {
                    String toString = code.toString();

                    if (!(code instanceof StoreCode) && !(code instanceof WriteCode) && !(code instanceof ReturnCode && ((ReturnCode) code).getArguments().size() == 1) && !(code instanceof CallCode) && !(code instanceof DumpCode)) {
                        System.out.println(toString);
                    } else if (code instanceof StoreCode && ((StoreCode) code).getArguments().size() == 2) {
                        System.out.println(toString + topRunTimeStack);
                    } else if (code instanceof ReturnCode) {
                        System.out.println(code.toString() + runTimeStack.peek());
                    } else if (code instanceof WriteCode) {
                        System.out.println(toString);
                    } else if (code instanceof CallCode) {
                        int arr_arguments[] = this.getArgs();
                        toString += "(";
                        for (int i = 0; i < arr_arguments.length; i++) {
                            toString += arr_arguments[i];
                            toString += ",";
                        }
                        if (toString.charAt(toString.length() - 1) == ',') {
                            toString = toString.substring(0, toString.length() - 1);
                        }
                        toString += ")";
                        System.out.println(toString);
                    }
                    runTimeStack.dump();
                }
                programCounter++;
            }
        } catch (Exception e) {
            System.out.println("Exception in vm.executeProgram");
        }
    }

    public void DumpOff() {          //dumping off
        dumping = false;
    }

    public void DumpOn() {           //dumping on
        dumping = true;
    }

    public void setProgramCounter(int val) {   //setter for Program Counter
        programCounter = val;
    }

    public int getProgramCounter() {                  //getter for Program Counter
        return programCounter;
    }

    public int popRunStack() {
        return runTimeStack.pop();
    }

    public void mult_popRunStack(int num_to_pop) {
        runTimeStack.multiPop(num_to_pop);
    }
    public void halt() {
        isRunning = false;
    }

    public int popReturnAddress() {
        return returnAddress.pop();
    }

    public void pushReturnAddress(int returnAddrsess) {
        returnAddress.push(returnAddrsess);
    }

    public void pushRunTimeStack(int val) {
        runTimeStack.push(val);
    }

    public int peekRunTimeStack() {
        return runTimeStack.peek();
    }

    public void newFrameAt(int offset) {
        runTimeStack.newFrameAt(offset);
    }

    public void popFrame() {
        runTimeStack.popFrame();
    }

    public int Store(int offset) {
        return runTimeStack.store(offset);
    }

    public int Load(int offset) {
        return runTimeStack.load(offset);
    }

    public int[] getArgs() {
        return runTimeStack.getArgs();
    }
}
