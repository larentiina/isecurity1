package larentina.isecutiry1.repository;


import larentina.isecutiry1.entity.Post;
import larentina.isecutiry1.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Profile profile);
}