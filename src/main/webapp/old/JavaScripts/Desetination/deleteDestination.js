/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




$(document).ready(function() {
    $("#submitDelete").click(function() {
        deleteDestination();
    });
});


function deleteDestination() {
    var id = $("#idDestDelete").val();
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: "rest/destination/" + id,
        dataType: "json",
        success: function(data) {
            alert("Delete destination \nID: " + id);           
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('deleteDestination error: ' + ": " + jqXHR.responseText );
        }
    });
}

