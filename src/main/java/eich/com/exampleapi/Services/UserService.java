package eich.com.exampleapi.Services;

import eich.com.exampleapi.Models.Domain.UserAddDTO;
import eich.com.exampleapi.Models.Domain.UserReadDTO;
import eich.com.exampleapi.Models.Entities.UserEntity;
import eich.com.exampleapi.Models.Mappers.UserMapper;
import eich.com.exampleapi.Models.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserReadDTO> findAll(){
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::userEntityToUserReadDTO)
                .collect(Collectors.toList());
    }

    public UserReadDTO add(UserAddDTO userAddDTO){
        return userMapper.userEntityToUserReadDTO(
                userRepository.save(
                userMapper.userAddDTOToUserEntity(userAddDTO)));
    }

    public UserReadDTO findUserById(Integer userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return userMapper.userEntityToUserReadDTO(userEntity);
        } else {
            return null;
        }
    }

    public UserReadDTO updateUser(Integer userId, UserAddDTO updatedUserData) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            userEntity.setName(updatedUserData.getName());
            userEntity.setSurname(updatedUserData.getSurname());
            userEntity.setEmail(updatedUserData.getEmail());
            userEntity.setPassword(updatedUserData.getPassword());

            userRepository.save(userEntity);

            return userMapper.userEntityToUserReadDTO(userEntity);
        } else {
            return null;
        }
    }

    public UserReadDTO partialUpdateUser(Integer userId, Map<String, Object> updatedUserData) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            for (Map.Entry<String, Object> entry : updatedUserData.entrySet()) {
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                if ("name".equals(fieldName)) {
                    userEntity.setName((String) fieldValue);
                } else if ("surname".equals(fieldName)) {
                    userEntity.setSurname((String) fieldValue);
                } else if ("email".equals(fieldName)) {
                    userEntity.setEmail((String) fieldValue);
                }
            }
            userRepository.save(userEntity);

            return userMapper.userEntityToUserReadDTO(userEntity);
        } else {
            return null;
        }
    }


    public boolean deleteUser(Integer userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

}
