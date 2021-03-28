package com.korolar.itennis.service.token;

import com.korolar.itennis.dto.auth.AuthorizationToken;
import com.korolar.itennis.dto.auth.LoginUser;

public interface ITokenService {
	AuthorizationToken getAuthorizationToken(LoginUser authenticationRequest);
}
