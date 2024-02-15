package ca.sheridancollege.vutran.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ca.sheridancollege.vutran.beans.Role;
import ca.sheridancollege.vutran.beans.User;
import ca.sheridancollege.vutran.models.AuthenticationRequest;
import ca.sheridancollege.vutran.models.AuthenticationResponse;
import ca.sheridancollege.vutran.repo.UserRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
	private UserRepo userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(AuthenticationRequest request) {
        User user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
