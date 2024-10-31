package br.ufms.facom.progweb.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class HelloController {

  @GetMapping("/hello")
  @ResponseBody
  public String index() {
    return "<p>Hello World!</p><p>Olá Mundo!</p>";
  }

  @GetMapping("/hello2")
  @ResponseBody
  public String index2(
      @RequestParam(name = "n", defaultValue = "World") String n) {
    return "<p>Hello " + n + "!</p><p>Olá " + n + "!</p>";
  }

}
