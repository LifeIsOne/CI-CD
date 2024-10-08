package shop.miraclecoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.miraclecoding.blog._core.errors.exception.Exception400;
import shop.miraclecoding.blog._core.errors.exception.Exception401;
import shop.miraclecoding.blog._core.errors.exception.Exception403;
import shop.miraclecoding.blog._core.errors.exception.Exception404;
import shop.miraclecoding.blog._core.utils.ApiUtil;
import shop.miraclecoding.blog.user.User;
import shop.miraclecoding.blog.user.UserRequest;

import java.io.Writer;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final HttpSession session;
    private final BoardService boardService;

    // Sever-Side Rendering은 DTO를 굳이 만들 필요가 없습니다.
    // 필요한 데이터만 Rendering해서 Client한테 전달하면 된다.

    // TODO : 글 상세보기 API 필요. @GetMapping("/")

    @GetMapping("/")
    public ResponseEntity<?> main (){
        List<BoardResponse.MainDTO> respDTO = boardService.글목록조회();
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    // TODO : 글 목록 조회 API 필요. @GetMapping("/api/boards/{id}/detail")
    @GetMapping("/api/boards/{id}/detail")
    public ResponseEntity<?> detail (@PathVariable Integer id){
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글상세보기(id, sessionUser);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    // TODO : 글 조회 API 필요. @GetMapping("/api/boards/{id}")
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> findOne (@PathVariable Integer id){
        Board board = boardService.글조회(id);
        return ResponseEntity.ok(new ApiUtil(board));
    }


    @PostMapping("/api/boards")
    public ResponseEntity<?> save(@RequestBody BoardRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글쓰기(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글수정(id, sessionUser.getId(),reqDTO);
        return ResponseEntity.ok(new ApiUtil(board));
    }

    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @GetMapping("/hello")
    public String hello(){
        return "err/400";
    }



    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

}
