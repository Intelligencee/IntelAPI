package me.intelligence.intelapi.command;

public class CommandException extends RuntimeException
{
    private String message;

    public CommandException(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
