package egovframework.servicename.web.user.mapper;

import egovframework.servicename.web.user.dto.UserDTO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userMapper")
public interface UserMapper {
    UserDTO selectUserByLoginId(UserDTO userDTO);
}
