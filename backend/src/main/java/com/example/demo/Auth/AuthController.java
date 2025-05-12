package com.example.demo.Auth;

import com.example.demo.Auth.DTO.SignUpRequestDTO;
import com.example.demo.Auth.DTO.SignUpResponseDTO;
import com.example.demo.Auth.JWT.JWTProvider;
import com.example.demo.Auth.Security.AuthValidator;
import com.example.demo.Auth.Security.Exception.InvalidSignUpRequestException;
import com.example.demo.Common.Redis.RedisService;
import com.example.demo.Lib.ApiResponse;
import com.example.demo.Common.Response.ResponseMessage;
import com.example.demo.Common.Response.ValidationStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthValidator authValidator;
    private final RedisService redisService;
    private final JWTProvider jWTProvider;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> signUp(@Valid @RequestBody SignUpRequestDTO request) throws InvalidSignUpRequestException {
        SignUpResponseDTO response = authValidator.validateSignUpRequest(request);

        boolean isValidUsername = response.getUsername().equals(ValidationStatus.SUCCESS);
        boolean isValidDisplayName = response.getDisplayName().equals(ValidationStatus.SUCCESS);
        boolean isValidPassword = response.getPassword().equals(ValidationStatus.SUCCESS);
        boolean isValidEmail = response.getEmail().equals(ValidationStatus.SUCCESS);

        if (!(isValidUsername && isValidDisplayName && isValidPassword && isValidEmail)) {
            throw new InvalidSignUpRequestException(response);
        }

        authService.signUp(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<SignUpResponseDTO>(true, ResponseMessage.CREATED, response));
    }

    @GetMapping("/access-token")
    public ResponseEntity<ApiResponse<Object>> accessToken(@CookieValue(value = "refresh_token", defaultValue = "") String refreshTokenId) {
        String refreshToken = redisService.get(refreshTokenId);
        if (refreshToken == null || jWTProvider.isExpired(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, ResponseMessage.UNAUTHORIZED, null));
        }
        String accessToken = "Bearer " + jWTProvider.generateAccessToken(jWTProvider.getUsername(refreshToken));
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", accessToken).body(new ApiResponse<>(true, ResponseMessage.SUCCESS, null));
    }
}