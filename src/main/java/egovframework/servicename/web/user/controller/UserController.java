package egovframework.servicename.web.user.controller;

import egovframework.servicename.web.user.dto.UserDTO;
import egovframework.servicename.web.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class UserController {
	private final UserService userService;

	@Value("${custom.properties.maker}")
	private String userName;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String search() throws Exception {
		UserDTO userDTO = new UserDTO();
		System.out.println("userName :: " + userName);
		userDTO.setLoginId(userName);
		UserDTO result = userService.selectUserByLoginId(userDTO);
		System.out.println(result.getPassword());
		return "login";
	}

}
