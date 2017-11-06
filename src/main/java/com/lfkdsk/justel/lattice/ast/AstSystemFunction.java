package com.lfkdsk.justel.lattice.ast;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;

import java.util.List;

public class AstSystemFunction extends AstList {
//    ISEMPTY : 'isEmpty' | '集合为空';                          // 'list == null || list.size() == 0' || String == null || String.lengh() == 0
//    ISNOTEMPTY : 'isNotEmpty' | '集合不为空';                  // 'list != null && list.size() > 0' || String != null && String.lengh() == 0
//    ISBLANK : 'isBlank' | '字符串为空';                        // String == null || String.length == 0;
//    ISNOTBLANK : 'isNotBlank' | '字符串不为空';                 // String != null && String.length > 0
//    ISNULL : 'isNull';                                       // x == null
//    ISNOTNULL : 'isNotNull';                                 // x != null
//    CONTAINS : 'contains' | '包含';                           // contains(Object) used on set, list object and String;
//    IN : 'in' | '在...内';                                    // Arrays.asList('x', 'xx').conatins('x');
//    CONTAINSKEY : 'hasKey' | '包含关键字';                     // Map.containsKey('x');
//    CONTAINSVALUE : 'hasValue' | '包含值';                    // Map.containsValue('value')
//    CONTAINSKV : 'hasKV' | '包含关键字和值';                    // Map.contains('key') && Map.get(key) == value


    public AstSystemFunction(List<AstNode> children) {
        super(children, AstNode.FUNCTION_NAME);
    }
}
