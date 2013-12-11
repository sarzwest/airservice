/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $("#submitAddReservation").click(function() {
        addReservation();
    });
});

function addReservation() {
    var id = $("#addReservationFlightID").val();
    
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: "rest/flight/" + id + "/reservation",
        dataType: "json",
        data: formToJSONAddReservation(),
        success: function(data, textStatus, jqXHRm){
            var XPassword = jqXHRm.getResponseHeader("X-Password");
            renderDetailsReservationAdd(data, XPassword);
            alert('Reservation created successfully');    
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('addReservation error: ' + jqXHR.responseText);
        }
    });
};


function formToJSONAddReservation() {
    return JSON.stringify({ 
        "seats": $("#addReservationSeats").val()
        });
};


function renderDetailsReservationAdd(data, XPassword) {
    	$('#addReservationTable tr').remove();
        $('#addReservationTable th').remove();
        $('#addReservationTable td').remove();
	$('#addReservationTable').append(
            "<tr><td>ID: </td><td>" + data.id + "</td></tr>" +
            "<tr><td>Flight from: </td><td> " + data.flight.from.name + "</td></tr>" +
            "<tr><td>Flight to: </td><td> " + data.flight.to.name + "</td></tr>" +
            "<tr><td>Created: </td><td> " +  data.created + "</td></tr>" + 
            "<tr><td>Seats:</td><td> " + data.seats + "</td></tr>" + 
            "<tr><td>State:</td><td> " + data.state + "</td></tr>" +
            "<tr><td>X-Password:</td><td> " + XPassword + "</td></tr>"
           );
}