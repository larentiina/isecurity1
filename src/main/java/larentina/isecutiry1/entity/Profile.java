package larentina.isecutiry1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue
    private Long id;

    private String fullName;

    private String email;

    @OneToOne
    @JoinColumn(name = "auth_user_id", nullable = false)
    private AuthUser authUser;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();


    public Profile(Long id, String fullName, String email, AuthUser authUser) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.authUser = authUser;
    }

}