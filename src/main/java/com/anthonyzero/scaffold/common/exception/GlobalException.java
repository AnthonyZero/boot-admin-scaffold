package com.anthonyzero.scaffold.common.exception;

import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;

public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private CodeMsgEnum codeMsgEnum;

    public GlobalException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum.getMsg());
        this.codeMsgEnum = codeMsgEnum;
    }

    public CodeMsgEnum getCodeMsgEnum() {
        return codeMsgEnum;
    }
}
