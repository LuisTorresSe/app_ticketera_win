package com.win.auth.adapter.in;

import com.win.ApiResponse;
import com.win.auth.application.port.in.LoginCommand;
import com.win.auth.application.port.in.LoginState;
import com.win.auth.application.port.in.LoginUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("http://localhost:5173/")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse<LoginState>> login(@RequestBody LoginRequest loginRequest) {

        LoginCommand command = new LoginCommand(loginRequest.email(), loginRequest.password());

        LoginState execute = this.loginUseCase.execute(command);

        return ResponseEntity.ok(ApiResponse.success(execute));
    }


}
