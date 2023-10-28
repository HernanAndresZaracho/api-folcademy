package eich.com.exampleapi.Security;

import eich.com.exampleapi.Models.Entities.ContactoEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class ContactoDetailsImpl implements UserDetails {
    private final ContactoEntity contacto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); //util si mi usuario tiene permisos o roles
    }

    @Override
    public String getPassword() {
        return contacto.getPassword();
    }

    @Override
    public String getUsername() {
        return contacto.getUsername();
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
        return true;
    }

    public String getName() {
        return contacto.getNombre();
    }
}
