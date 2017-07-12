package com.s2.easycode.sourcegenerator;

public class SourceGeneratorException extends RuntimeException {

    private static final long serialVersionUID = 2313378633647839478L;

    public SourceGeneratorException() {
    }

    public SourceGeneratorException(final String message) {
        super(message);
    }

    public SourceGeneratorException(final Throwable cause) {
        super(cause);
    }

    public SourceGeneratorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SourceGeneratorException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
