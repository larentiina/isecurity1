package larentina.isecutiry1.entity;

@Data
public class User {
    @Entity
    @Table(name = "users")
    public class User {
        @Id @GeneratedValue
        private Long id;

        @Column(unique = true, nullable = false)
        private String username;

        @Column(nullable = false)
        private String passwordHash;

        @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Post> posts = new ArrayList<>();

        public User() {}

        public User(Long id, String username, String passwordHash) {
            this.id = id;
            this.username = username;
            this.passwordHash = passwordHash;
        }

        // getters/setters
    }
}
