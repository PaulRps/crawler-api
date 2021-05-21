package paulrps.crawler.domain.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import paulrps.crawler.domain.dto.TrackObjectDto;
import paulrps.crawler.domain.entity.TrackObject;

@Mapper(componentModel = "spring")
public interface TrackObjectConverter {

  @Mappings({
    @Mapping(source = "dto.carrier.id", target = "carrier"),
    @Mapping(constant = "true", target = "isActive")
  })
  TrackObject toEntity(TrackObjectDto dto);
}
