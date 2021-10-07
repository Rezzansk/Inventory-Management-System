<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="DBUtil" class="utils.DBUtil"></jsp:useBean>
<jsp:useBean id="util" class="utils.Util"></jsp:useBean>
<%@ page import="entities.Admin" %>
<% Admin adm = util.isLogin(request, response); %>
<!doctype html>
<html lang="en">

<head>
    <title>Yönetim</title>
    <jsp:include page="inc/css.jsp"></jsp:include>
</head>

<body>
<div class="wrapper d-flex align-items-stretch">
    <jsp:include page="inc/sideBar.jsp"></jsp:include>
    <!-- Page Content  -->
    <div id="content" class="p-4 p-md-5">
        <jsp:include page="inc/topMenu.jsp"></jsp:include>
        <h3 class="mb-3">
            Satış Yap
            <small class="h6">Satış Yönetim Paneli</small>
        </h3>

        <div class="main-card mb-3 card mainCart">
            <div class="card-header cardHeader">Yeni Satış</div>

            <form class="row p-3"id="order_add_form">

                <div class="col-md-3 mb-3">
                    <label for="cname" class="form-label">Müşteriler</label>
                    <select id="cname" class="selectpicker" data-width="100%" data-show-subtext="true" data-live-search="true" required>
                        <option data-subtext="">Seçim Yapınız</option>
                        <c:if test="${DBUtil.allCustomer().size()>0}">
                            <c:forEach items="${DBUtil.allCustomer()}" var="item">
                                <option data-subtext="${item.cu_name}">${item.cu_id}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>

                <div class="col-md-3 mb-3">
                    <label for="pname" class="form-label">Ürünler</label>
                    <select id="pname" class="selectpicker" data-width="100%" data-show-subtext="true" data-live-search="true" required>
                        <option data-subtext="">Seçim Yapınız</option>
                        <c:if test="${DBUtil.allProduct().size()>0}"></c:if>
                        <c:forEach items="${DBUtil.allProduct()}" var="item">
                            <option data-subtext="${item.p_title}">${item.p_code}</option>
                        </c:forEach>
                    </select>
                </div>


                <div class="col-md-3 mb-3">
                    <label for="amount" class="form-label">Adet</label>
                    <input type="number" min="1" max="100" name="amount" id="amount" class="form-control" />
                </div>

                <div class="col-md-3 mb-3">
                    <label for="bNo" class="form-label">Fiş No</label>
                    <input type="number" name="bNo" id="bNo" class="form-control" />
                </div>

                <div class="btn-group col-md-3 " role="group">
                    <button type="submit" class="btn btn-outline-primary mr-1">Ekle</button>
                    <button onclick="fncReset()" type="reset" class="btn btn-outline-primary">Temizle</button>
                </div>
            </form>
        </div>


        <div class="main-card mb-3 card mainCart">
            <div class="card-header cardHeader">Sepet Ürünleri</div>

                <div class="table-responsive">
                    <table class="align-middle mb-0 table table-borderless table-striped table-hover">
                        <thead>
                        <tr>
                            <th>BPId</th>
                            <th>Müşteri</th>
                            <th>ürün</th>
                            <th>Adet</th>
                            <th>Fiş</th>
                            <th class="text-center" style="width: 55px;" >Yönetim</th>
                        </tr>
                        </thead>
                        <tbody id="tableRow">
                        <!-- for loop  -->
                        </tbody>
                    </table>
                </div>
        </div>

        <div class="btn-group col-md-3 " role="group">
            <button onclick="fncCompleteOrder()" type="button" class="btn btn-outline-primary mr-1">Satışı Tamamla</button>
        </div>
    </div>
</div>


<jsp:include page="inc/js.jsp"></jsp:include>
<script src="js/ordermanagement.js"></script>
</body>

</html>