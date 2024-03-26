package com.example.campuschool_backend.domain.user;

import com.example.campuschool_backend.domain.lecture.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Setter
@Getter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String img;
    private String portfolioImg;
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;
    @Enumerated(value = EnumType.STRING)
    private LoginType loginType;
    @Embedded
    private Description description;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    public void addReview(Review review) {
        //reviewList.add(review);
        review.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    protected  UserEntity(){}

    public static UserEntity of(String username,String password, String name, LoginType loginType,RoleType roleType) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setName(name);
        userEntity.setLoginType(loginType);
        userEntity.setRoleType(roleType);
        userEntity.setDescription(Description.of());
        return userEntity;
    }
}
