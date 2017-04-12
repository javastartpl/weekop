package pl.javastart.weekop.controller;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import pl.javastart.weekop.service.UserService;
 
@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
      
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("inputUsername");
        String password = request.getParameter("inputPassword");
        String email = request.getParameter("inputEmail");
        UserService userService = new UserService();
        userService.addUser(username, email, password);
        response.sendRedirect(request.getContextPath() + "/");
    }
 
}