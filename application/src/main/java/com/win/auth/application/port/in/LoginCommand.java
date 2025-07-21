package com.win.auth.application.port.in;

public record LoginCommand
        (String email, String password)
{
}
