package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "orderServlet", value = {"/shopping-cart-post", "/shopping-cart-delete","/shopping-cart-get","/order-add-post"})
public class OrderManagementServlet extends HttpServlet {

}
