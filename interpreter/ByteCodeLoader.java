
package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    private Program program;
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
        program = new Program();
        String nextline;
        try {
            while ((nextline = byteSource.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(nextline);
                ArrayList<String> args = new ArrayList<>();
                String bytecode_name = str.nextToken(" ");
                String bytecode_classname = CodeTable.getClassName(bytecode_name);
                Class byteCodeClass = Class.forName("interpreter.bytecode." + bytecode_classname);
                ByteCode newByteCode = (ByteCode) byteCodeClass.getDeclaredConstructor().newInstance();
                while (str.hasMoreTokens()) {
                    args.add(str.nextToken());
                }
                newByteCode.init(args);
                program.addCode(newByteCode);
            }
        }
        catch (Exception e) {
        }
        program.resolveAddress();
        return program;
    }
}