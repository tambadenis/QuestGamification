package ro.tambadenis.QuestGamification.model;

import javax.persistence.*;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Token() {}

    public Token(Long id, String  value, User user) {
        this.id = id;
        this.value = value;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String  getValue() {
        return value;
    }

    public void setValue(String  value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


