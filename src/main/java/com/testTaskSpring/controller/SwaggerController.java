package com.testTaskSpring.controller;

import com.testTaskSpring.core.State;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConditionalOnProperty(prefix = "testTaskSpring", name = "swagger", havingValue = State.DISABLED, matchIfMissing = true)
public class SwaggerController {

  @GetMapping(value = "swagger-ui.html")
  public void getSwagger(HttpServletResponse response) throws IOException {
    response.sendError(HttpServletResponse.SC_NOT_FOUND);
  }

}
