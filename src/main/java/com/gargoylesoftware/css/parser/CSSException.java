/*
 * Copyright (c) 2018 Ronald Brill.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.css.parser;

/**
 * @author Ronald Brill
 */
public class CSSException extends RuntimeException {

    public enum ErrorCode {
        UNSPECIFIED_ERR,
        NOT_SUPPORTED_ERR,
        SYNTAX_ERR,
    }

    private String message_;
    private ErrorCode code_;

    /**
     * Creates a new CSSException
     */
    public CSSException() {
    }

    /**
     * Creates a new CSSException
     */
    public CSSException(final String message) {
        code_ = ErrorCode.UNSPECIFIED_ERR;
        message_ = message;
    }

    /**
     * Creates a new CSSException with an embeded exception.
     * @param a the embeded exception.
     */
    public CSSException(final Exception e) {
        code_ = ErrorCode.UNSPECIFIED_ERR;
        initCause(e);
    }

    /**
     * Creates a new CSSException with a specific code.
     * @param a the embeded exception.
     */
    public CSSException(final ErrorCode code) {
        code_ = code;
    }

    /**
     * Creates a new CSSException with an embeded exception and a specified
     * message.
     * @param code the specified code.
     * @param e the embeded exception.
     */
    public CSSException(final ErrorCode code, final String message, final Exception e) {
        code_ = code;
        message_ = message;
        initCause(e);
    }

    /**
     * Returns the detail message of this throwable object.
     *
     * @return the detail message of this Throwable, or null if this Throwable
     *         does not have a detail message.
     */
    @Override
    public String getMessage() {
        if (message_ != null) {
            return message_;
        }

        if (getCause() != null) {
            return getCause().getMessage();
        }

        switch (code_) {
            case UNSPECIFIED_ERR:
                return "unknown error";
            case NOT_SUPPORTED_ERR:
                return "not supported";
            case SYNTAX_ERR:
                return "syntax error";
            default:
                return null;
        }
    }

    /**
     * returns the error code for this exception.
     */
    public ErrorCode getCode() {
        return code_;
    }
}