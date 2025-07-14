package com.win.subticket.application.port.in;

public interface CloseSubticketUseCase {

     CloseSubticketState execute(CloseSubticketCommand command);
}
