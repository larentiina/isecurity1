package larentina.isecutiry1.controller;

import larentina.isecutiry1.dto.CreatePostDto;
import larentina.isecutiry1.dto.PostDto;
import larentina.isecutiry1.entity.Post;
import larentina.isecutiry1.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/data")
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam String username) {
        return ResponseEntity.ok(postService.getPosts(username));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody CreatePostDto request
    ) {
        String token = authHeader.replace("Bearer ", "");
        Post post = postService.createPost(token, request.getContent());
        return ResponseEntity.ok("Post created with id: " + post.getId());
    }
}
