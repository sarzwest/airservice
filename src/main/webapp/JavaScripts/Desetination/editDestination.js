/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $("#submitEdit").click(function() {
    editDestination();
    });
});

function editDestination() {
    var id = $("#idDestEdit").val();
    var name = $("#nameDestEdit").val();
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: "rest/destination/" + id,
        dataType: "json",
        data: formToJSONDestEdit(name),
        success: function(data, textStatus, jqXHR){
            alert('Edit successfully' + "\nEdited destination ID: " + id + "\nEdited destination name: " + name);
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('editDestination error: '  + jqXHR.responseText);
        }
    });
};
 
 

 
function formToJSONDestEdit(name) {
    return JSON.stringify({
        "name": name
        });
};
