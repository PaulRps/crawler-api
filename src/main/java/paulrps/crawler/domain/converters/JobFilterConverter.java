package paulrps.crawler.domain.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import paulrps.crawler.domain.dto.JobFilterDto;
import paulrps.crawler.domain.entity.JobFilter;
import paulrps.crawler.domain.enums.JobOpeningSourceEnum;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JobFilterConverter {

  @Mappings({
    @Mapping(source = "jobOpeningSources", target = "jobOpeningSources"),
    @Mapping(source = "isActive", target = "isActive", defaultValue = "false")
  })
  JobFilter toEntity(JobFilterDto dto);

  default List<Integer> mapJobOpeningsEnumToInteger(List<JobOpeningSourceEnum> sources) {
    return sources.stream().map(JobOpeningSourceEnum::getId).collect(Collectors.toList());
  }
}
