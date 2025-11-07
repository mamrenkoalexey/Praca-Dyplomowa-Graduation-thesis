package thesis.Graduation.thesis.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import thesis.Graduation.thesis.entity.Client;
import thesis.Graduation.thesis.entity.Employee;

import java.util.Collection;
import java.util.Collections;


public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final String displayName;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean active;

    public CustomUserDetails(Employee employee) {
        this.id = employee.getId();
        this.username = employee.getLogin();
        this.password = employee.getPassword();
        this.active = employee.getActive();

        this.displayName = employee.getFirstName() + " " + employee.getLastName();

        this.authorities = Collections.singleton(() -> "ROLE_" + employee.getRole().name());
    }

    public CustomUserDetails(Client client) {
        this.id = client.getId();
        this.username = client.getLogin();
        this.password = client.getPassword();
        this.active = client.getActive();

        if (client.getCompanyName() != null && !client.getCompanyName().isEmpty()) {
            this.displayName = client.getCompanyName();
        } else {
            this.displayName = client.getFirstName() + " " + client.getLastName();
        }

        this.authorities = Collections.singleton(() -> "ROLE_CLIENT");
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return active;
    }
}

