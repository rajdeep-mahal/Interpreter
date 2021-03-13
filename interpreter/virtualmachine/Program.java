package interpreter.virtualmachine;

import interpreter.bytecode.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    private ArrayList<ByteCode> program;

    public Program() {
        program = new ArrayList<>();
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    public void addCode(ByteCode byteCode) {
        program.add(byteCode);
    }

    public int getSize() {
        return this.program.size();
    }
    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     */
    public void resolveAddress() {
        HashMap<String, Integer> labelTable = new HashMap<>();

        for (int i = 0; i < this.program.size(); i++) {
            ByteCode addr = program.get(i);
            if (addr instanceof LabelCode) {
                labelTable.put(((LabelCode) addr).getArguments().get(0), i);
            }
        }
        for (int i = 0; i < this.program.size(); i++) {
            ByteCode addr = program.get(i);
            if ((addr instanceof FalseBranchCode)) {
                int num = labelTable.get(((FalseBranchCode) addr).getArguments().get(0));
                ((FalseBranchCode) addr).setDestinationAddr(num);
            } else if (addr instanceof GotoCode) {
                int num = labelTable.get(((GotoCode) addr).getArguments().get(0));
                ((GotoCode) addr).setDestinationAddr(num);
            } else if (addr instanceof CallCode) {
                int num = labelTable.get(((CallCode) addr).getArguments().get(0));
                ((CallCode) addr).setDestinationAddr(num);
            }
        }
    }
}