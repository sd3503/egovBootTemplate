package egovframework.servicename.web.user.service;

import egovframework.servicename.web.user.dto.UserDTO;
import egovframework.servicename.web.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserService extends EgovAbstractServiceImpl{

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public UserDTO selectUserByLoginId(UserDTO userDTO) throws Exception {
        return userMapper.selectUserByLoginId(userDTO);
    }

}
