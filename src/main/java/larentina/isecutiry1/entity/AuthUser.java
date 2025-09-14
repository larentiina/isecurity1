package larentina.isecutiry1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "auth_users")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @OneToOne(mappedBy = "authUser", cascade = CascadeType.ALL)
    private Profile profile;

}
