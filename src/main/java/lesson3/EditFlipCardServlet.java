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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

@WebServlet(name = "editSpecificServlet", urlPatterns = "/edit/*")
public class EditFlipCardServlet extends HttpServlet {
	private final MemorizeService service = ServletConfiguration.getService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LinkedList<String> linkedList = Arrays.stream(req.getPathInfo().split("/")).collect(Collectors.toCollection(LinkedList::new));
		int id = Integer.parseInt(linkedList.getLast());
		FlipCard flipcard = service.findFlipCardById(id);
		resp.setContentType("text/html");
		PrintWriter pr = resp.getWriter();
		pr.println("<html><body><p>Edit page");
		pr.print("<p>" + flipcard.getNativeWord() + " - " + flipcard.getTranslationWord() + "</p>");

		pr.println("<form name=\"editForm\" method=\"put\" action=\"/webAppExploded/edit/\"" + id + "/>");
		pr.println("nativeWord: <input type=\"text\" name=\"newNativeWord\"/><br/>");
		pr.println("translation: <input type=\"text\" name=\"newTranslation\"/><br/>");
		pr.println("<input type=\"submit\" value=\"update\"></form>");

		pr.println("</body></html>");
		pr.close();
	}
}
