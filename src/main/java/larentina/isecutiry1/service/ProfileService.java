package larentina.isecutiry1.service;

import larentina.isecutiry1.entity.Profile;
import larentina.isecutiry1.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepo;

    public Profile getProfileByUsername(String username) {
        return profileRepo.findByAuthUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}
