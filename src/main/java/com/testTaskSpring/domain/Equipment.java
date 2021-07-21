package com.testTaskSpring.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Equipment Entity Class")

public class Equipment implements MetaData {

  @Id
  private Long id;

  @ApiModelProperty("name")
  private String name;

  @ApiModelProperty("well_Id")
  private Long wellId;
}
