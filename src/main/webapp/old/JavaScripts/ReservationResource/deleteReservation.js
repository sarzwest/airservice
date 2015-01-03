/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $("#submitDeleteReservation").click(function() {
        deleteReservation();
    });
});


function deleteReservation() {
    var id = $("#deleteReservationID").val();
    var flightId = $("#deleteReservationFlightID").val();
    var XPassword = $("#deleteReservationXPassword").val();
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: "rest/flight/" + flightId + "/reservation/" + id,
        dataType: "json",
        headers: {
            'X-Password' : XPassword
        },        
        success: function(data) {
            alert("Delete reservation \nFlight ID:" + flightId + " \nID: " + id);           
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if(jqXHR.status === 200){
                alert("Deleted reservation \n " /*+ "Flight ID:" + flightId + " \nID: " + id*/);
            }else if(jqXHR.status === 400){
                alert("Reservation PAID or not created \n");           
            }
            else {
                alert('deleteFlight error: '+ jqXHR.responseText);    
            }

        }
    });
}