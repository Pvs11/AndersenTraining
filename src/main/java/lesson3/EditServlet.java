package lesson3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson2.FlipCard;
import lesson2.MemorizeService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editServlet", urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
	private final MemorizeService service = ServletConfiguration.getService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pr = resp.getWriter();
		pr.println("<html><body><p>Here you see the list of all FlipCards");
		for (FlipCard flipcard : service.getFlipCards()) {
			pr.print("<p>" + flipcard.getNativeWord() + " - " + flipcard.getTranslationWord() + "</p>");
			pr.println(String.format("<a href=\"/webAppExploded/edit/%s\">edit</a>", flipcard.getId()));
		}
		pr.println("</body></html>");
		pr.close();
	}
}
