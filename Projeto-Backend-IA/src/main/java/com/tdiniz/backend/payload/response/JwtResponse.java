package com.tdiniz.backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String isAdmin;

	public JwtResponse(String accessToken, Long id, String username, String isAdmin) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.isAdmin = isAdmin;
	}

}
