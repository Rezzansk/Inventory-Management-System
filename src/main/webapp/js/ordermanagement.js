// Order
// add - start


let customerId = "";
$('#cname').change(function () {
    var selectedItem = $("#cname option:selected").val();
    customerId = selectedItem;
    console.log("customerId : " + customerId);

    const obj = {
        customerId: customerId,
    }

    $.ajax({
        url: './basket-get',
        type: 'GET',
        data: { obj: JSON.stringify(obj) },
        dataType: 'Json',
        success: function (data) {
            createRow(data);
        },
        error: function (err) {
            console.log(err)
        }
    })

})

let customerCode = '';
let select_id = 0
var customer_control = 0  // customer control
$('#order_add_form').submit( ( event ) => {
    event.preventDefault();
    const cname = $("#cname").val()
    const pname = $("#pname").val()
    const amount = $("#amount").val()
    const bcode = $("#bNo").val()
    var bpstatus = false


    const obj = {
        bp_customer: cname,
        bp_product: pname,
        bp_amount: amount,
        bp_code: bcode,
        bp_status: bpstatus
    }

    if ( select_id != 0 ) {
        // update
        obj["bp_id"] = select_id;
    }

    $.ajax({
        url: './basket-post',
        type: 'POST',
        data: { obj: JSON.stringify(obj) },
        dataType: 'JSON',
        success: function (data) {
            if ( data > 0 ) {
                alert("Update Başarılı")
            }else {
                alert("Ekleme sırasında hata oluştu!");
            }
        },
        error: function (err) {
            console.log(err)
            alert("Ekleme sırısında bir hata oluştu2!");
        }
    })
    fncReset();

})

let globalArr = []
function createRow( data ) {
    // debugger;
    globalArr = data;
    let html = ``
    customer_control = data[0].bp_customer; // customer control
    console.log( "customer_control: "  +  customer_control);
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
        html += `<tr role="row" class="odd">
            <td>`+itm.bp_id+`</td>
            <td>`+itm.customer.cu_name+`</td>
            <td>`+itm.products.p_title+`</td>
            <td>`+itm.bp_amount+`</td>
            <td>`+itm.bp_code+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncOrderDelete(`+itm.bp_id+`, 1)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="fncCustomerDetail(`+i+`)" data-bs-toggle="modal" data-bs-target="#customerDetailModel" type="button" class="btn btn-outline-primary "><i class="far fa-file-alt"></i></button>
                <button onclick="fncCustomerUpdate(`+i+`)" type="button" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
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
    $('#bNo').val( key )
}

// reset fnc
function fncReset() {
    select_id = 0;
    $('#order_add_form').trigger("reset");
    $('#cname').selectpicker("refresh"); // customer selectpicker refresh
    $('#pname').selectpicker("refresh"); // product selectpicker refresh
    codeGenerator();
    //allBasketProduct();
}

// customer delete - start
function fncOrderDelete( bp_id, delete_question ) {

    let answer = false;
    if(delete_question == 0 ) { answer = true }
    else {  answer = confirm("Silmek istediğinizden emin misiniz?");}

    if ( answer ) {
        $.ajax({
            url: './basket-delete?bp_id='+bp_id,
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
// customer delete - end

// purchase order complete
const objCustomer = null
function fncCompleteOrder() {

    const cname = $("#cname").val()

    // fiş kesilecek müşteri
    const objCustomer = {
        bp_control: cname
    }

    console.log(objCustomer.bp_control)

    $.ajax({
        url: './purchase-post',
        type: 'POST',
        data: { obj: JSON.stringify(objCustomer) },
        dataType: 'JSON',
        success: function (objCustomer) {
            if ( data > 0 ) {
                alert("Update Başarılı")
            }else {
                alert("Sipariş sırasında hata oluştu!");
            }
            fncReset();
        },
        error: function (err) {
            console.log(err)
            alert("Sipariş sırısında bir hata oluştu!");
        }
    })

}

// purchase order complete - end