package visa.center.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import visa.center.config.jwt.JwtTokenProvider;
import visa.center.model.dto.AuthenticationDto;
import visa.center.service.UserServiceImpl;

@RestController
@RequestMapping(value = "/visa/center")
public class AuthenticationController {

	private AuthenticationManager authenticationManager;

	private JwtTokenProvider jwtTokenProvider;

	private UserServiceImpl userService;

	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			UserServiceImpl userService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}

	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody AuthenticationDto authDto) {
		try {
			String username = authDto.getUsername();
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, authDto.getPassword()));
			UserDetails userDetails = userService.loadUserByUsername(username);
			if (userDetails == null) {
				throw new UsernameNotFoundException("User with username " + username + " not found");
			}

			String token = jwtTokenProvider.createToken(userDetails);
			Map<Object, Object> response = new HashMap<>();
			response.put("username", username);
			response.put("token", token);
			return ResponseEntity.ok(response);
		} catch (AuthenticationException ex) {
			throw new BadCredentialsException("Invalid username or password");
		}
	}
}
