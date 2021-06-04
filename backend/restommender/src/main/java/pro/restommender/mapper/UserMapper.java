package pro.restommender.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pro.restommender.dto.responseDTO.UserResponseDTO;
import pro.restommender.model.AuthenticatedUser;

@Service
public class UserMapper {

    public List<UserResponseDTO> toDtoList(List<AuthenticatedUser> users) {

        List<UserResponseDTO> usersDto = new ArrayList<>();

        for (AuthenticatedUser user : users) {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setEmail(user.getEmail());
            dto.setFirstName(user.getFirstName());
            dto.setId(user.getId());
            dto.setLastName(user.getLastName());
            dto.setType(user.getType());

            usersDto.add(dto);
        }

        return usersDto;
    }
    
}
