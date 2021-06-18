package pro.restommender.mapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

            if (user.getBlockedDate() != null) {
                Date now = new Date();
                // Calendar date = Calendar.getInstance();
                // long timeInSecs = date.getTimeInMillis();
                long userBlockedTimeInSec = user.getBlockedDate().getTime();
                Date userBlockedDateAfter5min = new Date(userBlockedTimeInSec + (1 * 60 * 1000));
                if (now.after(userBlockedDateAfter5min)) {
                    dto.setCanBeUnblocked(true);
                } else {
                    dto.setCanBeUnblocked(false);
                }
            } else {
                dto.setCanBeUnblocked(true);
            }

            usersDto.add(dto);
        }

        return usersDto;
    }

}
