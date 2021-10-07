// Product
// add - start
let select_id = 0
$('#product_add_form').submit( ( event ) => {
    event.preventDefault();

    const ptitle = $("#ptitle").val()
    const aprice = $("#aprice").val()
    const oprice = $("#oprice").val()
    const pcode = $("#pcode").val()
    const ptax = $("#ptax").val()
    const psection = $("#psection").val()
    const size = $("#size").val()
    const pdetail = $("#pdetail").val()


    const obj = {
        p_title: ptitle,
        p_purchase_price: aprice,
        p_sale_price: oprice,
        p_code: pcode,
        p_tax: ptax,
        p_unit: psection,
        p_amount: size,
        p_detail: pdetail,
    }

    if ( select_id != 0 ) {
        // update
        obj["p_id"] = select_id;
    }

    $.ajax({
        url: './product-post',
        type: 'POST',
        data: { obj: JSON.stringify(obj) },
        dataType: 'JSON',
        success: function (data) {
            if ( data > 0 ) {
                alert("Update Başarılı")
                fncReset();
            }else {
                alert("Update sırasında hata oluştu!");
            }
        },
        error: function (err) {
            console.log(err)
            alert("Update sırısında bir hata oluştu!");
        }
    })


})

// all product list - start
function allProduct() {

    $.ajax({
        url: './product-get',
        type: 'GET',
        dataType: 'Json',
        success: function (data) {
            createRow(data);
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function selectedTax(data){
    let tax = "";
    switch (data) {
        case 0: tax = "Dahil"; break;
        case 1: tax = "1"; break;
        case 2: tax = "8"; break;
        case 3: tax = "18"; break;
    }
    return tax;
}
function selectedUnit(data){
    let unit = "";
    switch (data) {
        case 0: unit = "Adet"; break;
        case 1: unit = "KG"; break;
        case 2: unit = "Metre"; break;
        case 3: unit = "Paket"; break;
        case 4: unit = "Litre"; break;
    }
    return unit;
}

let globalArr = []
function createRow( data ) {
    globalArr = data;
    let html = ``
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
        let tax = selectedTax(itm.p_tax);
        let unit = selectedUnit(itm.p_unit);
        html += `<tr role="row" class="odd">
            <td>`+itm.p_id+`</td>
            <td>`+itm.p_title+`</td>
            <td>`+itm.p_purchase_price+`</td>
            <td>`+itm.p_sale_price+`</td>
            <td>`+itm.p_code+`</td>
            <td>`+ tax +`</td>
            <td>`+ unit +`</td>
            <td>`+itm.p_amount+`</td>
            <td>`+itm.p_detail+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncProductDelete(`+itm.p_id+`)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="fncProductDetail(`+i+`)" data-bs-toggle="modal" data-bs-target="#productDetailModel" type="button" class="btn btn-outline-primary "><i class="far fa-file-alt"></i></button>
                <button onclick="fncProductUpdate(`+i+`)" type="button" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
              </div>
            </td>
          </tr>`;
    }
    $('#tableRow').html(html);
}

function codeGenerator() {
    const date = new Date();
    const time = date.getTime();
    const key = time.toString().substring(4);
    $('#pcode').val( key )
}

//all product list
allProduct();

// reset fnc
function fncReset() {
    select_id = 0;
    $('#product_add_form').trigger("reset");
    codeGenerator();
    allProduct();
}

// product delete - start
function fncProductDelete( p_id ) {
    let answer = confirm("Silmek istediğinizden emin misiniz?");
    if ( answer ) {

        $.ajax({
            url: './product-delete?p_id='+p_id,
            type: 'DELETE',
            dataType: 'text',
            success: function (data) {
                if ( data != "0" ) {
                    fncReset();
                }else {
                    alert("Silme sırasında bir hata oluştu!");
                }
            },
            error: function (err) {
                console.log(err)
            }
        })
    }
}
// product delete - end

// product detail - start
function fncProductDetail(i){
    const itm = globalArr[i];
    $("#p_title").text(itm.p_title +  " - " + itm.p_id);
    $("#p_purchase_price").text(itm.p_purchase_price == "" ? '------' : itm.p_purchase_price);
    $("#p_sale_price").text(itm.p_sale_price == "" ? '------' : itm.p_sale_price);
    $("#p_code").text(itm.p_code == "" ? '------' : itm.p_code);
    $("#p_tax").text(itm.p_code == 1 ? '------' : selectedTax(itm.p_tax));
    $("#p_unit").text(itm.p_unit == 1 ? '------' : selectedUnit(itm.p_unit));
    $("#p_amount").text(itm.p_amount == "" ? '------' : itm.p_amount);
    $("#p_detail").text(itm.p_detail == "" ? '------' : itm.p_detail);
}
// product detail - end

// product update select
function fncProductUpdate( i ) {
    const itm = globalArr[i];
    select_id = itm.p_id
    $("#ptitle").val(itm.p_title)
    $("#aprice").val(itm.p_purchase_price)
    $("#oprice").val(itm.p_sale_price)
    $("#pcode").val(itm.p_code)
    $("#ptax").val(itm.p_tax)
    $("#psection").val(itm.p_unit)
    $("#size").val(itm.p_amount)
    $("#pdetail").val(itm.p_detail)
}