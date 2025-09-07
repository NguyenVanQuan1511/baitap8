package vn.edu.hcmuaf.fit.demo3;
import vn.edu.hcmuaf.fit.demo3.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class EmailListServlet extends HttpServlet {
    private ArrayList<User> users;

    @Override
    public void init() {
        users = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        User user = new User(firstName, lastName, email);

        // Lưu user vào session list
        HttpSession session = request.getSession();
        users.add(user);
        session.setAttribute("users", users);

        // Lưu user hiện tại
        request.setAttribute("user", user);

        // Lưu ngày hiện tại
        request.setAttribute("currentDate", new Date());

        String url = "/thanks.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
