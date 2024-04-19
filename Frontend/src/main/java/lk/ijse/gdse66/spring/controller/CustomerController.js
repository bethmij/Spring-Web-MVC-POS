import {setCusID} from "./OrderController.js";
import {customer} from "../model/Customer.js";
import {setCustomerCount} from "./DashboardController.js";


let cusId = $("#txtCusID");
let cusName = $("#txtCusName");
let cusAddress = $("#txtCusAddress");
let cusSalary = $("#txtCusSalary");
let btnCustomerSave = $('#btnSave');


$(document).on('keydown', function(event) {
    if (event.keyCode === 9) {
        event.preventDefault();
    }
});

getAll();
btnCustomerSave.click(function (event){
    let newCustomer = Object.assign({}, customer);
    newCustomer.cusID = cusId.val();
    newCustomer.cusName = cusName.val();
    newCustomer.cusAddress = cusAddress.val();
    newCustomer.cusSalary = cusSalary.val();

    if(btnCustomerSave.text()==="Save ") {
        event.preventDefault();

        swal("Do you want to save the customer?", {
            buttons: {
                cancel1: {
                    text: "Cancel",
                    className: "custom-cancel-btn",
                },
                ok: {
                    text: "OK",
                    value: "catch",
                    className: "custom-ok-btn",
                }
            },
        }).then((value) => {
            if (value === "catch" ) {
                    getCusIDList( function (IDList) {
                        if (!(IDList.includes(cusId.val()))) {
                            const formData = new FormData($("#cusForm")[0]);
                            $.ajax({
                                url: "http://localhost:8080/backend/api/v1/customers",
                                method: "POST",
                                data: formData,
                                processData: false,
                                contentType: false,
                                success: function (resp, status, xhr) {
                                    if (xhr.status === 201) {
                                        swal("Saved", "Customer ID : "+resp.id+" Saved Successfully!", "success");
                                        getAll();
                                        deleteDetail();
                                        setFeilds();
                                        clearAll(event);
                                        getCusIDList(function (IDList) {
                                            setCusID(IDList);
                                        });
                                        btnCustomerSave.attr("disabled", true);
                                        setCustomerCount();
                                    }
                                },
                                error: function (xhr) {
                                    swal("Error", xhr.responseText, "error");
                                }
                            });
                        } else {
                            swal("Error", "Duplicate customer ID!", "error");
                        }
                    });
                }
            });


    }else if(btnCustomerSave.text()==="Update ") {
        swal("Do you want to update the customer?", {
            buttons: {
                cancel1: {
                    text: "Cancel",
                    className: "custom-cancel-btn",
                },
                ok: {
                    text: "OK",
                    value: "catch",
                    className: "custom-ok-btn",
                }
            },
        }).then((value) => {
            if (value === "catch") {

                let newCustomer = Object.assign({}, customer);
                newCustomer.id = cusId.val();
                newCustomer.name = cusName.val();
                newCustomer.address = cusAddress.val();
                newCustomer.salary = cusSalary.val();

                $.ajax({
                    url: "http://localhost:8080/backend/api/v1/customers",
                    method: "PATCH",
                    contentType: "application/json",
                    data: JSON.stringify(newCustomer),
                    success: function (resp, status, xhr) {

                        if (xhr.status === 204) {
                            console.log(xhr.status)
                            console.log(resp)
                            swal("Updated", "Customer ID : "+cusId.val()+" Saved Successfully!", "success");
                            getAll();
                            clearAll(event);
                            btnCustomerSave.text("Save ");
                            btnCustomerSave.attr("disabled", true);
                            cusId.attr("disabled", false);
                        }
                    },
                    error: function (xhr) {
                        swal("Error", xhr.responseText, "error");
                    }
                });
            }
        });
    }

    event.preventDefault();
})

$('#clear').click(function (event){
    clearAll(event);
})

function clearAll(event) {
    cusId.val("");
    cusName.val("");
    cusAddress.val("");
    cusSalary.val("");
    $('#txtCusID').css("border","1px solid white");
    $('#cusIDPara').text("");
    $('#txtCusName').css("border","1px solid white");
    $('#cusNamePara').text("");
    $('#txtCusAddress').css("border","1px solid white");
    $('#cusAddressPara').text("");
    $('#txtCusSalary').css("border","1px solid white");
    $('#cusSalaryPara').text("");
    btnCustomerSave.text("Save ");
    btnCustomerSave.attr("disabled", true);
    event.preventDefault();
    cusId.attr("disabled", false);
}



$('#getAll').click(function (){
    getAll();
})

function getAll() {

    $.ajax({
        url: "http://localhost:8080/backend/api/v1/customers/getAll",
        method: "GET",
        success: function (resp, status, xhr) {

            if(xhr.status===200) {
                let cusBody = $("#cusTBody");
                cusBody.empty();
                for (let customer of resp) {
                    cusBody.append(`
                        <tr>
                            <th scope="row">${customer.id}</th>
                            <td>${customer.name}</td>
                            <td>${customer.address}</td>
                            <td>${customer.salary}</td>
                            <td style="width: 10%;"><img  class="delete"  src="../src/main/resources/assets/img/icons8-delete-96.png" alt="Logo" width="40%" style="opacity: 100%;" "></td>
                        </tr>`);
                    deleteDetail();
                    setFeilds();
                }
            }
        },
        error: function (xhr){
            swal("Error", xhr.responseText, "error");
        }
    })
}

setFeilds();

function setFeilds() {
    $('#cusTBody>tr').click(function () {
        cusId.val($(this).children(':eq(0)').text());
        cusName.val($(this).children(':eq(1)').text());
        cusAddress.val($(this).children(':eq(2)').text());
        cusSalary.val($(this).children(':eq(3)').text());
        btnCustomerSave.text("Update ");
        btnCustomerSave.attr("disabled", false);
        cusId.attr("disabled", true);
    })
}

deleteDetail();

function deleteDetail() {
    let btnDelete = $('.delete');
    btnDelete.on("mouseover", function () {
            $(this).css("cursor", "pointer")
        }
    )

    btnDelete.click(function (event) {

        swal("Do you want to delete the customer?", {
            buttons: {
                cancel1: {
                    text: "Cancel",
                    className: "custom-cancel-btn",
                },
                ok: {
                    text: "OK",
                    value: "catch",
                    className: "custom-ok-btn",
                }
            },
        }).then((value) => {
            if (value === "catch") {

                let deleteRow = $(this).parents('tr');
                let cusID = $(deleteRow.children(':nth-child(1)')).text();

                $.ajax({
                    url: "http://localhost:8080/backend/api/v1/customers/" + cusID,
                    method: "DELETE",
                    success: function (resp, status, xhr) {
                        if (xhr.status === 204) {
                            swal("Deleted", "Customer ID : "+cusID+" Deleted Successfully!", "success");
                            deleteRow.remove();
                            clearAll(event);
                            getCusIDList(function (IDList) {
                                setCusID(IDList)
                            });
                            setCustomerCount();
                        }
                    },
                    error: function (xhr) {
                        swal("Error", xhr.responseText, "error");
                    }
                });
                setCusID();
            }
        });
    })
}

export function getCustomerList(id, callback) {
    $.ajax({
        url: "http://localhost:8080/backend/api/v1/customers/" + id,
        method: "GET",
        success: function (resp, status, xhr) {
            callback(resp, xhr);
        },
        error: function (xhr) {
            swal("Error", xhr.responseText, "error");
        }
    });
}

$('#btnSearch').click(function (){
    let id = $('#txtSearch').val();
    let cusTBody = $('#cusTBody');

    if(id.length!==0) {
        getCusIDList( function (IDList) {
            if (IDList.includes(id)) {
                $.ajax({
                    url: "http://localhost:8080/backend/api/v1/customers/" + id,
                    method: "GET",
                    success: function (resp, status, xhr) {
                        if (xhr.status === 200) {
                            cusTBody.empty();
                            cusTBody.append(`
                                <tr>
                                   <th scope="row">${resp.id}</th>
                                   <td>${resp.name}</td>
                                   <td>${resp.address}</td>
                                   <td>${resp.salary}</td>
                                   <td style="width: 10%;"><img  class="delete"  src="../src/main/resources/assets/img/icons8-delete-96.png" alt="Logo" width="50%" class="opacity-75"></td>
                                </tr>`)

                            setFeilds();
                            deleteDetail();
                        }
                    },
                    error: function (xhr) {
                        swal("Error", xhr.responseText, "error");
                    }
                })
            } else {
                swal("Error", "Invalid customer ID! Please try again", "error");
            }
        });
    }else {
        swal("Error", "Please enter the customer ID!", "error");
    }

});

export function getCusIDList(callback) {
    let cusIDList = [];
    $.ajax({
        url: "http://localhost:8080/backend/api/v1/customers/getIds",
        method: "GET",
        success: function (resp, status, xhr) {
            if(xhr.status === 200) {
                cusIDList = resp
                callback(cusIDList);
            }
        },
        error: function (xhr) {
            swal("Error", xhr.responseText, "error");
        }
    });
}
