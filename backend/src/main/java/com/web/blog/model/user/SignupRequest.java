package com.web.blog.model.user;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class SignupRequest {
    @ApiModelProperty(required = true)
    @NotNull
    String email;
    @ApiModelProperty(required = true)
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
    String password;
    @ApiModelProperty(required = true)
    @NotNull
    String nickname;
    @ApiModelProperty(required = true)
    @NotNull
    String name;
    @ApiModelProperty(required = true)
    LocalDateTime birth;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getBirth() {
		return birth;
	}
	public void setBirth(LocalDateTime birth) {
		this.birth = birth;
	}

    
    
}
