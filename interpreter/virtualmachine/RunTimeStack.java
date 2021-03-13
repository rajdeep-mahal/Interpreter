package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.Stack;
import interpreter.bytecode.*;

class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer>     framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public int peek() {
        return runTimeStack.get(runTimeStack.size() - 1);
    }

    public int pop() {
        int frameSize = runTimeStack.size() - framePointer.peek();
        int peek = 0;
        if (frameSize >= 1) {
            peek = runTimeStack.get(runTimeStack.size() - 1);
            runTimeStack.remove(runTimeStack.size() - 1);
        }
        return peek;
    }

    public int push(int val) {
        runTimeStack.add(val);
        return val;
    }
    public int stackSize() {
        return runTimeStack.size();
    }

    public void newFrameAt(int offset) {
        framePointer.push(runTimeStack.size() - offset);
    }

    public void multiPop(int val) {
        for (int i = 0; i < val; i++) {
            this.pop();
        }
    }

    public void popFrame() {
        int val = runTimeStack.size() - framePointer.peek();
        int returnValue = runTimeStack.get(runTimeStack.size() - 1);
        this.multiPop(val);
        this.framePointer.pop();
        runTimeStack.add(returnValue);
    }

    public int store(int offset) {
        int storeValue = runTimeStack.get(runTimeStack.size() - 1);
        runTimeStack.remove(runTimeStack.size() - 1);
        runTimeStack.set(offset + framePointer.peek(), storeValue);
        return storeValue;
    }

    public int load(int offset) {
        int loadValue = runTimeStack.get(framePointer.peek()) + offset;
        runTimeStack.add(loadValue);
        return loadValue;
    }

    public Integer push(Integer val) {
        runTimeStack.add(val);
        return val;
    }

    public int[] getArgs() {
        final int numArgs = (runTimeStack.size() - framePointer.peek());
        int[] array = new int[numArgs];
        for (int i = framePointer.peek(); i < runTimeStack.size(); i++) {
            array[i - framePointer.peek()] = runTimeStack.get(i);
        }
        return array;
    }

    public void dump() {
        String string = "";
        for (int i = 0; i < framePointer.size(); i++) {
            string += "[";
            if (i == framePointer.size() - 1) {
                for (int j = framePointer.peek(); j < (runTimeStack.size()); j++) {
                    string += runTimeStack.get(j);
                    string += ",";
                }
            } else {
                int dist = framePointer.get(i + 1) - framePointer.get(i);
                for (int m = framePointer.get(i); m < framePointer.get(i) + dist; m++) {
                    string += runTimeStack.get(m);
                    string += ",";
                }
            }
            if (string.charAt(string.length() - 1) == ',') {
                string = string.substring(0, string.length() - 1);
            }
            string += "] ";
        }
        System.out.println(string);
    }
}