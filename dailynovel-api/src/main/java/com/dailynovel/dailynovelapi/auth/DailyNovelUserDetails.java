package com.dailynovel.dailynovelapi.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DailyNovelUserDetails implements UserDetails {
    private long id;
    private String username;
    private String password;
    private String email;
    private List<GrantedAuthority> authorties;

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public List<GrantedAuthority> getAuthorties() {
        return authorties;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAuthorties(List<GrantedAuthority> authorties) {
        this.authorties = authorties;
    }



    
    @Override
    public String toString() {
        return "DailyNovelUserDetails [id=" + id + ", username=" + username + ", password=" + password + ", email="
                + email + ", authorties=" + authorties + "]";
    }


    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    public void getAuthorities(List<GrantedAuthority> authorities) {
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    
}
