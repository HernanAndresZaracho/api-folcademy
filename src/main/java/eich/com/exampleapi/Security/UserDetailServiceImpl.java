package eich.com.exampleapi.Security;

import eich.com.exampleapi.Models.Dtos.SignupRequestDTO;
import eich.com.exampleapi.Models.Entities.AddressEntity;
import eich.com.exampleapi.Models.Entities.UserEntity;
import eich.com.exampleapi.Models.Repositories.AddressRepository;
import eich.com.exampleapi.Models.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity registerUser(SignupRequestDTO signupRequest) {
        if(usuarioRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("El email ya estÃ¡ en uso.");
        }

        UserEntity newUser = new UserEntity();
        newUser.setName(signupRequest.getName());
        newUser.setSurname(signupRequest.getSurname());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        AddressEntity address = new AddressEntity();
        address.setNumber(signupRequest.getAddress().getNumber());
        address.setStreet(signupRequest.getAddress().getStreet());
        addressRepository.save(address);

        newUser.setAddress(address);
        return usuarioRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity usuario =  usuarioRepository
                .findOneByEmail(email) //si no encuentro ningun usuario con este correo electronico
                .orElseThrow(()-> new UsernameNotFoundException("El usuario con email " + email + " no existe."));

        return new UserDetailsImpl(usuario);
    }
}
