package eich.com.exampleapi.Security;

import eich.com.exampleapi.Models.Dtos.SignupRequestDTO;
import eich.com.exampleapi.Models.Entities.ContactoEntity;
import eich.com.exampleapi.Models.Entities.UserEntity;
import eich.com.exampleapi.Models.Repositories.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ContactoDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ContactoRepository contactoRepository;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ContactoEntity registerUser(SignupRequestDTO signupRequest) {
        if(contactoRepository.existsByCelular(signupRequest.getCelular())) {
            throw new RuntimeException("El celular ya esta en uso.");
        }

        ContactoEntity newUser = new ContactoEntity();
        newUser.setNombre(signupRequest.getName());
        newUser.setCelular(signupRequest.getCelular());
        newUser.setUsername(signupRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        return contactoRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ContactoEntity contacto =  contactoRepository
                .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario con username " + username + " no existe."));

        return new ContactoDetailsImpl(contacto);
    }
}
