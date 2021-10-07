package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.BasketProducts;
import entities.Customer;
import entities.Product;
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
import java.util.List;

@WebServlet(name = "basketProductsServlet", value = {"/basket-post", "/basket-get", "/basket-delete", "/purchase-post"})
public class BasketProductsServlet extends HttpServlet {

    SessionFactory sf = HibernateUtil.getSessionFactory();

    // basket-insert
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        int bpid = 0;

        String path = req.getServletPath();
        switch (path) {
            case "/basket-post":
                try {
                    System.out.println("1111111111111111111111111111111111111111");
                    String obj = req.getParameter("obj");

                    JsonObject object = (JsonObject) parser.parse(obj);
                    String customerId = object.get("bp_customer").getAsString();
                    String product_code = object.get("bp_product").getAsString();
                    System.out.println("customer id" + customerId);

                    Customer customers = (Customer) sesi.createQuery("from Customer where cu_id = ?1")
                            .setParameter(1,Integer.parseInt(customerId))
                            .getSingleResult();
                    Product product = (Product) sesi.createQuery("from Product where p_code = ?1")
                            .setParameter(1,Long.parseLong(product_code))
                            .getSingleResult();
                    BasketProducts basketProducts = gson.fromJson(obj, BasketProducts.class);
                    basketProducts.setCustomer(customers);
                    basketProducts.setProducts(product);

                    sesi.save(basketProducts);

                    bpid = 1;
                }catch ( Exception ex) {
                    System.err.println("Save OR Update Error : " + ex);
                }finally {

                }
                break;
            case "/purchase-post":
                String obj = req.getParameter("obj");

                System.out.println("Obje:" + obj);

                JsonObject object = (JsonObject) parser.parse(obj); // PATLIYOR

                String customerId = object.get("bp_control").getAsString();
                System.out.println(customerId);

                int completed = sesi.createQuery("update BasketProducts set bp_status = true where fk_cuId = ?1 and bp_status = false")
                        .setParameter(1,Integer.parseInt(customerId))
                        .executeUpdate();

                System.out.println("Eklenen sayı: " + completed);

                break;
            default:
                System.out.println("Url bulunamadı!");
        }

        tr.commit();
        sesi.close();

        String obj2 = req.getParameter("obj");
        System.out.println(obj2);

        resp.setContentType("application/json");
        resp.getWriter().write( "" + bpid );
    }

    // basket get
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Session sesi = sf.openSession();
        String stJson = "";

        try {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();

            String obj = req.getParameter("obj");
            JsonObject object = (JsonObject) parser.parse(obj);
            String customerId = object.get("customerId").getAsString();

            List<BasketProducts> ls = sesi.createQuery("from BasketProducts where fk_cuId = ?1 and bp_status = false")
                    .setParameter(1,Integer.parseInt(customerId))
                    .getResultList();

            for ( BasketProducts item : ls) {
                item.getCustomer().setBasketProducts(null);
                item.getProducts().setBasketProducts(null);
            }

            System.out.println(ls);
            stJson = gson.toJson(ls);

        }catch ( Exception ex) {
            System.err.println("Save OR Update Error : " + ex);
        }finally {
            sesi.close();
        }

        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }

    // basket-delete remove
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int return_id = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        try {
            int bp_id = Integer.parseInt( req.getParameter("bp_id") );
            BasketProducts basketProducts = sesi.load(BasketProducts.class, bp_id);
            sesi.delete(basketProducts);
            tr.commit();
            return_id = basketProducts.getBp_id();
        }catch (Exception ex) {
            System.err.println("Delete Error : " + ex);
        }finally {
            sesi.close();
        }

        resp.setContentType("application/json");
        resp.getWriter().write( ""+return_id );
    }

}