package com.khanhvo.expensetracking.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Builder // Lombok annotation to generate builder pattern for the object
@Data // Lombok annotation to generate getters, setters, toString, hash, equals, etc.
@AllArgsConstructor // Lombok annotation to generate constructor with all fields
@NoArgsConstructor // Lombok annotation to generate no-arg constructor
@Document("user")
public class User implements UserDetails {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    @Field("username")
    private String username;
    @Field("password")
    private String password;
    @Field("email")
    private String email;
    @Field("fullName")
    private String fullName;
    @Field("role")
    private Role role;
    @Field("verified")
    private boolean verified;
    @Field("createdDate")
    @Builder.Default
    private LocalDateTime createdDate= LocalDateTime.now();

    @Field("notifications")
    private List<Notification> notifications;

    @Field("personalSharedGroups")
    private List<PersonalSharedGroup> personalSharedGroups;

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return verified;
    }

}
