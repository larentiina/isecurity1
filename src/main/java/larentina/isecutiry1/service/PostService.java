package larentina.isecutiry1.service;

import larentina.isecutiry1.entity.Post;
import larentina.isecutiry1.entity.Profile;
import larentina.isecutiry1.repository.PostRepository;
import larentina.isecutiry1.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepo;
    private final ProfileRepository profileRepo;
    private final JwtService jwtService;


    public List<Post> getPosts(String username) {
        Profile profile = profileRepo.findByAuthUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return postRepo.findByAuthor(profile);
    }

    public Post createPost(String token, String content) {
        String username = jwtService.extractUsername(token);

        Profile profile = profileRepo.findByAuthUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Post post = Post.builder()
                .content(content)
                .author(profile)
                .build();

        return postRepo.save(post);
    }
}