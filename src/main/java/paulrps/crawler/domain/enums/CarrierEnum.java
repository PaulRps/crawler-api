package paulrps.crawler.domain.enums;

import lombok.Getter;

@Getter
public enum CarrierEnum {
  CORREIOS(ParserTypeEnum.CORREIOS_TRACK_OBJ.getId());

  CarrierEnum(final Integer id) {
    this.id = id;
  }

  private Integer id;
}
