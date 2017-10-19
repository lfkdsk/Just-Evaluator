package com.lfkdsk.justel.generate.bytegen;

public class ELCommand {
    public enum CommandType {

        // load <var1> <var2> <var3> from env
        load("load"),
        // call <function> <arg1> <arg2> <arg3>
        call("call"),
        // op <operator> <arg1> <arg2>
        op("op");

        public final String cmdOp;

        CommandType(java.lang.String cmdOp) {
            this.cmdOp = cmdOp;
        }
    }

    public final CommandType type;
    public final String[] args;

    public ELCommand(CommandType type, String[] args) {
        this.type = type;
        this.args = args;
    }
}
