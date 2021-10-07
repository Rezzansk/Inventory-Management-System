
// product get

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
          </tr>`;
    }
    $('#tableRow').html(html);
}

function allProduct() {

    $.ajax({
        url: './product-get',
        type: 'GET',
        dataType: 'Json',
        success: function (data) {
            createRow(data);
        },
        error: function (err) {
            console.log("Error" + err)
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

allProduct();

