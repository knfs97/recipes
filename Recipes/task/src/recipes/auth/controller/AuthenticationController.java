package recipes.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.auth.entity.RegisterRequest;
import recipes.auth.service.AuthenticationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void register(
            @Valid
            @RequestBody
            RegisterRequest request
    ) {
        authService.signup(request);
    }
}
