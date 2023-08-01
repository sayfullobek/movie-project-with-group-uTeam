package Movie.Movie.controller;

import Movie.Movie.Entity.Comment;
import Movie.Movie.Repository.CommentRepository;
import Movie.Movie.pyload.ApiResponse;
import Movie.Movie.pyload.CommentDto;
import Movie.Movie.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping
    public HttpEntity<?> sendComment(@RequestBody CommentDto dto) {
        try {
            ApiResponse apiResponse = commentService.sendComment(dto);
            return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{movieId}")
    public HttpEntity<?> getComment(@PathVariable UUID movieId) {
        try {
            List<Comment> commentsByMoviesId = commentRepository.findCommentsByMoviesId(movieId);
            return ResponseEntity.ok(commentsByMoviesId);
        } catch (Exception e) {
            return null;
        }
    }
}
