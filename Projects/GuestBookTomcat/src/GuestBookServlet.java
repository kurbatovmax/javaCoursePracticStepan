import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 10/5/13
 * Time: 1:19 AM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "GuestBookServlet", urlPatterns = "/")
public class GuestBookServlet extends HttpServlet
{
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        out.print("<h1>Hi</h1>");
        out.flush();
        out.close();
    }
}
