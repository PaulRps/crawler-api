package paulrps.crawler.domain.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import paulrps.crawler.domain.dto.UserJobFilterDto;
import paulrps.crawler.domain.entity.UserJobFilter;
import paulrps.crawler.domain.enums.JobOpeningSourceEnum;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserJobFilterConverter {

  @Mappings({
    @Mapping(source = "jobOpeningSources", target = "jobOpeningSources"),
    @Mapping(source = "isActive", target = "isActive", defaultValue = "false")
  })
  UserJobFilter toEntity(UserJobFilterDto dto);

  default List<Integer> mapJobOpeningsEnumToInteger(List<JobOpeningSourceEnum> sources) {
    return sources.stream().map(JobOpeningSourceEnum::getId).collect(Collectors.toList());
  }
}
