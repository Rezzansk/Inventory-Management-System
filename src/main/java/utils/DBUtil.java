package utils;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DBUtil {

    SessionFactory sf = HibernateUtil.getSessionFactory();

    public Admin login(String email, String password, String remember, HttpServletRequest req, HttpServletResponse resp) {
        Session sesi = sf.openSession();
        Admin adm = null;
        try {
            String sql = "from Admin Where email = ?1 and password = ?2";
            // ? yanına hangi degerin hangi soru işareti yerine geleceği belirlemek için, ? yanına degeri giriyoruz   --> içinde a geçenleri arıcaz
            adm = (Admin) sesi
                    .createQuery(sql)
                    .setParameter(1, email)
                    .setParameter(2, Util.MD5(password))
                    .getSingleResult();

            System.out.println("adm-email : " + adm.getEmail());

            if(adm != null){
                int aid = adm.getAid();
                String fullName = adm.getFullName();

                req.getSession().setAttribute("aid", aid);
                req.getSession().setAttribute("fullName", fullName);

                // cookie create de bunun altına gelecek ve remember bu cookie de kullanılacak
                if ( remember != null && remember.equals("on")) {
                    fullName = fullName.replaceAll(" ", "_");
                    String val = aid+"_"+fullName;
                    Cookie cookie = new Cookie("admin", val);
                    cookie.setMaxAge( 60*60 );
                    resp.addCookie(cookie);
                }
            }

        } catch (Exception e) {
            System.err.println("addLogin Error : " + e);
        } finally {
            sesi.close();
        }
        return adm;
    }

    // all Customer
    public List<Customer> allCustomer(){
        Session sesi = sf.openSession();
        List<Customer> ls = sesi.createQuery("from Customer").getResultList();
        sesi.close();
        return ls;
    }

    // all product
    public List<Product> allProduct(){
        Session sesi = sf.openSession();
        List<Product> ls = sesi.createQuery("from Product").getResultList();
        sesi.close();
        return ls;
    }
/*
    // find product id
    public List<Product> findProductId(){
        Session sesi = sf.openSession();
        int p_id = sesi.createQuery("select p_id FROM product WHERE p_code = 711400324").getResultList();
        sesi.close();
        return ls;
    }*/

    // customer account count
    public int viewCustomerCount(){
        Session sesi = sf.openSession();
        int cu_count = 0;

        try{
            ViewsCustomerCount viewsCustomerCount = (ViewsCustomerCount) sesi
                    .createQuery("from ViewsCustomerCount")
                    .getSingleResult();

            cu_count = viewsCustomerCount.getCu_count();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex );
        }
        sesi.close();
        return cu_count;
    }

    // total order count
    public int viewOrderCount(){
        Session sesi = sf.openSession();
        int or_count = 0;

        try{
            ViewsOrderCount viewsOrderCount = (ViewsOrderCount) sesi
                    .createQuery("from ViewsOrderCount")
                    .getSingleResult();

            or_count = viewsOrderCount.getOr_count();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex );
        }
        sesi.close();
        return or_count;
    }

    // total products in stock
    public int viewProductCount(){
        Session sesi = sf.openSession();
        int pr_count = 0;

        try{
            ViewsProductCount viewsProductCount = (ViewsProductCount) sesi
                    .createQuery("from ViewsProductCount")
                    .getSingleResult();

            pr_count = viewsProductCount.getPr_count();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex );
        }
        sesi.close();
        return pr_count;
    }


    // total products in stock
    public int viewStockCount(){
        Session sesi = sf.openSession();
        int p_total_price = 0;

        try{
            ViewsStockCount viewsStockCount = (ViewsStockCount) sesi
                    .createQuery("from ViewsStockCount")
                    .getSingleResult();

            p_total_price = viewsStockCount.getP_total_price();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex );
        }
        sesi.close();
        return p_total_price;
    }

    // total sale value in stock
    public int viewsTotalSaleValue(){
        Session sesi = sf.openSession();
        int p_total_sale = 0;

        try{
            ViewsTotalSaleValue viewsTotalSaleValue = (ViewsTotalSaleValue) sesi
                    .createQuery("from ViewsTotalSaleValue")
                    .getSingleResult();

            p_total_sale = viewsTotalSaleValue.getP_total_sale();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex );
        }
        sesi.close();
        return p_total_sale;
    }

    // total amount in stock
    public int viewTotalAmount(){
        Session sesi = sf.openSession();
        int p_total_amount = 0;

        try{
            ViewsTotalAmount viewsTotalAmount = (ViewsTotalAmount) sesi
                    .createQuery("from ViewsTotalAmount")
                    .getSingleResult();

            p_total_amount = viewsTotalAmount.getP_total_amount();
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex );
        }
        sesi.close();
        return p_total_amount;
    }

}
