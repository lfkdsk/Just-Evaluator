package com.lfkdsk.justel.utils.asm;

import org.objectweb.asm.FieldVisitor;

/**
 *
 */
public class FieldBuilder {

    private FieldVisitor fieldVisitor;

    public FieldBuilder(FieldVisitor fieldVisitor) {
        this.fieldVisitor = fieldVisitor;
    }

}
