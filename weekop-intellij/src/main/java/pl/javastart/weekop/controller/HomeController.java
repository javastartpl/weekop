package pl.javastart.weekop.controller;
 
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import pl.javastart.weekop.model.Discovery;
import pl.javastart.weekop.service.DiscoveryService;
 
@WebServlet("")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveDiscoveriesInRequest(request);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
 
    private void saveDiscoveriesInRequest(HttpServletRequest request) {
        DiscoveryService discoveryService = new DiscoveryService();
        List<Discovery> allDiscoveries = discoveryService.getAllDiscoveries(new Comparator<Discovery>() {
            //more votes = higher
            @Override
            public int compare(Discovery d1, Discovery d2) {
                int d1Vote = d1.getUpVote() - d1.getDownVote();
                int d2Vote = d2.getUpVote() - d2.getDownVote();
                if(d1Vote < d2Vote) {
                    return 1;
                } else if(d1Vote > d2Vote) {
                    return -1;
                }
                return 0;
            }
        });
        allDiscoveries.forEach(
                discovery -> {
                    Timestamp timestamp = discovery.getTimestamp();
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    System.out.println(localDateTime);
                }
        );
        request.setAttribute("discoveries", allDiscoveries);
    }
}
