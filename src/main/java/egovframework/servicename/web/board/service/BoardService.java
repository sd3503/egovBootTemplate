package egovframework.servicename.web.board.service;

import egovframework.servicename.web.board.dto.BoardDTO;
import egovframework.servicename.web.board.mapper.BoardMapper;
import egovframework.servicename.web.user.dto.UserDTO;
import egovframework.servicename.web.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class BoardService extends EgovAbstractServiceImpl{

    @Resource(name = "boardMapper")
    private BoardMapper boardMapper;

    public BoardDTO selectIssueById(BoardDTO boardDTO) throws Exception {
        return boardMapper.selectIssueById(boardDTO);
    }

}
