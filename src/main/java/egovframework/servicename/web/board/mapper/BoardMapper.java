package egovframework.servicename.web.board.mapper;

import egovframework.servicename.web.board.dto.BoardDTO;
import egovframework.servicename.web.user.dto.UserDTO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("boardMapper")
public interface BoardMapper {
    BoardDTO selectIssueById(BoardDTO boardDTO);
}
