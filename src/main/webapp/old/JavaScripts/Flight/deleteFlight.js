/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $("#submitDeleteFlight").click(function() {
        deleteFlight();
    });
});


function deleteFlight() {
    var id = $("#deleteFlightID").val();
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: "rest/flight/" + id,
        dataType: "json",
        success: function(data) {
            alert("Delete flight \nID: " + id);           
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('deleteFlight error: ' + ": " + jqXHR.responseText);
        }
    });
}