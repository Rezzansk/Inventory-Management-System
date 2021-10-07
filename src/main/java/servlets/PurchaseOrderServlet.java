package servlets;
/*
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "purchaseOrderServlet", value = {"/purchaseOrder-post", "/purchaseOrder-get"})
public class PurchaseOrderServlet extends HttpServlet {

    SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int po_id = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        String obj2 = req.getParameter("obj");

        System.out.println(obj2);
        try {
            String obj = req.getParameter("obj");
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(obj);
            String customerId = object.get("po_customer").getAsString();

            Customer customers = (Customer) sesi.createQuery("from Customer where cu_id = ?1")
                    .setParameter(1,Integer.parseInt(customerId))
                    .getSingleResult();

            //System.out.println("cusotmer id " + customerId + " name " + customers.getCu_name());

            PurchaseOrder purchaseOrder = gson.fromJson(obj, PurchaseOrder.class);
            purchaseOrder.setCustomer(customers);
            sesi.saveOrUpdate(purchaseOrder);
            tr.commit();
            sesi.close();
            po_id = 1;
        }catch ( Exception ex) {
            System.err.println("Save OR Update Error : " + ex);
        }finally {
            sesi.close();
        }

        resp.setContentType("application/json");
        resp.getWriter().write( "" + po_id );
    }

}
*/
