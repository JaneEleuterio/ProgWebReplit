package br.ufms.facom.progweb.calc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping
public class CalculadoraController {

  @GetMapping("/soma")
  @ResponseBody
  public String somaGet(
    HttpServletRequest request, HttpServletResponse response,
    @RequestParam(name = "a", defaultValue = "0") int a,
    @RequestParam(name = "b", defaultValue = "0") int b
  ) {

    
    int result = Calculadora.somar(a, b);
      incrementaCookie(request, response);
    
    return "<p>(GET) O resultado da soma é: "+result+"</p>";
  }

  @PostMapping("/calc")
    @ResponseBody
    public String calcula(
      HttpServletRequest request, HttpServletResponse response,
      @RequestParam(name = "a", defaultValue = "0") int a,
      @RequestParam(name = "b", defaultValue = "0") int b,
      @RequestParam(name = "op", defaultValue = "somar") String op
    ) {
      int result = Calculadora.calcular(a, b, op);

      incrementaCookie(request, response);

      return "<p>(POST) O resultado da operação "+op+" é: "+result+"</p>";
    }

  private void incrementaCookie(HttpServletRequest request, HttpServletResponse response) {

      Cookie c = getCookie(request, "contador-Calc");

      if (c == null) {
          c = new Cookie("contador-Calc", "1");
      } else {
          int contagem = Integer.parseInt(c.getValue());
          c.setValue(Integer.toString(++contagem));
      }

      // Expira em 3 horas
      c.setMaxAge(3 * 60 * 60);

      response.addCookie(c);

  }


  @GetMapping("/cookie")
    @ResponseBody
    public String mostraCookie(HttpServletRequest request, HttpServletResponse response) {

        Cookie c = getCookie(request, "contador-Calc");

        return "O cookie salvo é " + c.toString() + "<br>com valor armazenado: " + c.getValue();

    }

  private Cookie getCookie(HttpServletRequest request, String cookieName) {

      Cookie[] cookies = request.getCookies();
      Cookie cookieEncontrado = null;

      if (cookies != null) {
          for (Cookie cookie : cookies) {
              if (cookie.getName().equals(cookieName))
                  cookieEncontrado = cookie;
          }
      }
      return cookieEncontrado;
  }

}
