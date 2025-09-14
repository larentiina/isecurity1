package larentina.isecutiry1.repository;

import larentina.isecutiry1.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByAuthUserUsername(String username);
}