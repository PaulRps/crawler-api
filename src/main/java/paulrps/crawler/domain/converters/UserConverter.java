package paulrps.crawler.domain.converters;

import org.mapstruct.Mapper;
import paulrps.crawler.domain.dto.UserDto;
import paulrps.crawler.domain.entity.User;

@Mapper(componentModel = "spring")
public interface UserConverter {

  User toEntity(UserDto dto);

  UserDto toDto(User user);
}
