package com.win.subticket.application.port.in;



public interface UpdateSubticketUseCase {

    SubticketState execute(UpdateSubticketCommand updateSubticketCommand);
}
