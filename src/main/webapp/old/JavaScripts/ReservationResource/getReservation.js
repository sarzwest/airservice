/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var destination;


$(document).ready(function() {
    $("#submitGetReservation").click(function() {
        getReservation();
    });
});


function getReservation() {
    var id = $("#getReservationID").val();
    var flightId = $("#getReservationFlightID").val();
    var XPassword = $("#getReservationXPassword").val();
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: "rest/flight/" + flightId + "/reservation/" + id,
        dataType: "json",
        headers: {
            'X-Password' : XPassword
        },
        success: function(data) {
            destination = data;
            renderDetailsReservationGet(destination, XPassword);
            
            
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('getReservation error: ' + jqXHR.responseText );
        }
    });
}



function renderDetailsReservationGet(data, xpassword) {
    	$('#getReservationTable tr').remove();
        $('#getReservationTable th').remove();
        $('#getReservationTable td').remove();
	$('#getReservationTable').append(
            "<tr><td>ID: </td><td>" + data.id + "</td></tr>" +
            "<tr><td>Flight from: </td><td> " + data.flight.from.name + "</td></tr>" +
            "<tr><td>Flight to: </td><td> " + data.flight.to.name + "</td></tr>" +
            "<tr><td>Created: </td><td> " +  data.created + "</td></tr>" + 
            "<tr><td>Seats:</td><td> " + data.seats + "</td></tr>" + 
            "<tr><td>State:</td><td> " + data.state + "</td></tr>" +
            "<tr><td>X-Password:</td><td> " + xpassword + "</td></tr>" 
           );
}