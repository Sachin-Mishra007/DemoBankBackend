package com.sachin.demobank.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.demobank.DTO.RequestDTO.AuthRequestDTO;
import com.sachin.demobank.DTO.ResponseDTO.AuthResponseDTO;
import com.sachin.demobank.Security.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request)
    {
        Authentication auth=authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String role=auth.getAuthorities().stream().findFirst().map(a->a.getAuthority()).orElse("USER");
        if(role.startsWith("ROLE_")) role=role.substring(5);
        String token=jwtUtil.generateToken(request.getEmail(), role);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    
    }


}
