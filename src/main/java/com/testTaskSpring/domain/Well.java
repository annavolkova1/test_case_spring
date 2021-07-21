package com.testTaskSpring.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@ApiModel("Well Entity Class")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Well implements MetaData {

  @Id
  private Long id;

  @ApiModelProperty("name")
  private String name;
}
