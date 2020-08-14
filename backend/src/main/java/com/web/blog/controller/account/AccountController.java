package com.web.blog.controller.account;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.ChangepwdRequest;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class AccountController {

	@Autowired
	UserDao userDao;

	@GetMapping("/account/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

        
		Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);

		ResponseEntity response = null;

		if (userOpt.isPresent()) {
			System.out.println("ok");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
	}
	//카카오로그인
	@PostMapping("/account/kakao")
	@Async
	@ApiOperation(value = "카카오로그인")
	public Object kakaologin(@Valid @RequestBody Map<String, String> data) {
		String email = data.get("email");
		String nickname = data.get("nickname");
		
		User user = new User();
		user.setEmail(email);
		user.setNickname(nickname);
        user.setPassword("");
       
        //이메일이 없으면 회원가입
        if(userDao.getUserByEmail(email) == null) {
        	userDao.save(user);
        }
        
        return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	//구글 로그인
	@PostMapping("/account/google")
	@Async
	@ApiOperation(value = "구글로그인")
	public Object googlelogin(@Valid @RequestBody Map<String, String> data) {
		String gEmail = data.get("gEmail");
		String gNickname = data.get("gNickname");
		
		User user = new User();
		user.setEmail(gEmail);
		user.setNickname(gNickname);
		user.setName(gNickname);
        user.setPassword("");
       
        //이메일이 없으면 회원가입
        if(userDao.getUserByEmail(gEmail) == null) {
        	userDao.save(user);
        }
        
        return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@DeleteMapping("/account")
	@ApiOperation(value = "회원 탈퇴")
	public Object delete(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {
		
		Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);
		ResponseEntity response = null;
		System.out.println(userOpt.toString());
		if(userOpt.isPresent()) {
			
			userDao.delete(userOpt.get());
			System.out.println("삭제완료");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			System.out.println("실패");
		}

		return response;
	}

	//회원 가입
	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@Valid @RequestBody SignupRequest request) {
		String email = request.getEmail();
		String password = request.getPassword();
		String nickname = request.getNickname();
		String name = request.getName();
		
		System.out.println("가입한 이메일 : " + email);
		System.out.println("닉네임 : " + nickname);

		//이미 있는 이메일, 닉네임
		if (userDao.getUserByEmail(email) != null && userDao.getUserByNickname(nickname) != null) {
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "fail";
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}

		User user = new User();
		user.setNickname(nickname);
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		
		userDao.save(user);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
    
	@PutMapping("/account")
    @ApiOperation(value = "비밀번호변경")
    public Object changepwd(@Valid @RequestBody ChangepwdRequest request) {
    	System.out.println("비밀번호 변경?");
    	BasicResponse result = null;
    	Optional<User> userOpt = 
    			userDao.findUserByEmailAndPassword(request.getEmail(), request.getOldPassword());
    	
    	if(!userOpt.isPresent()){ //존재하지 않는 경우
            result = new BasicResponse();
            result.status = true;
            result.data = "fail";
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    	else { //존재하는 하는 경우
    		User user = userOpt.get();
    		user.setPassword(request.getNewPassword());
    		userDao.save(user);
    		result = new BasicResponse();
            result.status = true;
            result.data = "success";	
            System.out.println("변경됨");
            return new ResponseEntity<>(result, HttpStatus.OK);
    	}	
    }
	
	
	

}