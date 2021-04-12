package ssj.security.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssj.security.common.constants.SecurityConstants;
import ssj.security.controller.dto.LoginRequest;
import ssj.security.service.AuthService;

/**
 * @author harveylo
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "認證")
public class AuthController {

  private final AuthService authService;

  @ApiOperation(value = "登陸")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  @RequestMapping(value = {"/login"},method = RequestMethod.POST)
  public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
    String token = authService.createToken(loginRequest);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set(SecurityConstants.properties.getProperty("jwt.token.header"), token);
    return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
  }

}
