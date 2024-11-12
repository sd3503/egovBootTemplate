package egovframework.servicename.web.board.controller;

import egovframework.servicename.web.board.dto.BoardDTO;
import egovframework.servicename.web.board.service.BoardService;
import egovframework.servicename.web.user.dto.UserDTO;
import egovframework.servicename.web.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = "/board")
public class BoardController {
	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping(value={"/",""})
	public String getBoardPage() throws Exception {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setId(298);
		BoardDTO result = boardService.selectIssueById(boardDTO);
		System.out.println(result.getIssueContent());
		return "login";
	}
}
