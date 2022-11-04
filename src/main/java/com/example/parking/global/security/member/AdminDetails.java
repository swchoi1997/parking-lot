package com.example.parking.global.security.member;

import com.example.parking.domain.admin.entity.Admin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Builder
public class AdminDetails implements UserDetails {

    private Admin admin;

    public AdminDetails(Admin admin) {
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(admin.getRole().toString()));
        return roles;
    }

    public Long getId() {
        return admin.getAdminId();
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getAdminName();
    }
    //계정 만료 여부 -> true(만료되지 않음)

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //계정 잠김 여부 -> true(잠기지 않음)

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //비밀번호 만료 여부 -> true(만료되지 않음)

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //계정이 활성화 된건지 -> true(활성화)

    @Override
    public boolean isEnabled() {
        return true;
    }
}
