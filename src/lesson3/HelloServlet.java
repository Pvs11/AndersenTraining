package lesson3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(
		name = "Hello Servlet",
		urlPatterns = "/hello",
		description = "Servlet that prints greetings"
)
public class HelloServlet extends HttpServlet {

	private static final String HTML_TEXT = """
      <!DOCTYPE html>
      <html lang="en">
      <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hello Servet</title>
      </head>
      <body>
        <h1>Hello %s!</h1>
      </body>
      </html>
      """;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		var name = Optional.ofNullable(request.getParameter("name"))
				.orElse("World");

		response.setContentType("text/html");

		var out = response.getWriter();
		out.printf(HTML_TEXT, name);
	}

}