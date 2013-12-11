/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var destination;

$(document).ready(function() {
    $("#submitEditReservation").click(function() {
        editReservation();
    });
});


function editReservation() {
    var id = $("#editReservationId").val();
    var flightId = $("#editReservationFlightID").val();
    var XPassword = $("#editReservationXPassword").val();
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: "rest/flight/" + flightId + "/reservation/" + id,
        dataType: "json",
        data: formToJSONReservationEdit(),
        headers: {
            'X-Password' : XPassword
        },
        success: function(data) {
            alert('Edit reservation successfully');
            destination = data;
            renderDetailsReservationEdit(destination, XPassword);
            
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('editFlight error: ' +  jqXHR.responseText );
        }
    });
}


function formToJSONReservationEdit() {
    return JSON.stringify({
        "state": $("#editReservationState").val()
        });
};


function renderDetailsReservationEdit(data, XPassword) {
    	$('#editReservationTable tr').remove();
        $('#editReservationTable th').remove();
        $('#editReservationTable td').remove();
	$('#editReservationTable').append(
            "<tr><td>ID: </td><td>" + data.id + "</td></tr>" +
            "<tr><td>Flight from: </td><td> " + data.flight.from.name + "</td></tr>" +
            "<tr><td>Flight to: </td><td> " + data.flight.to.name + "</td></tr>" +
            "<tr><td>Created: </td><td> " +  data.created + "</td></tr>" + 
            "<tr><td>Seats:</td><td> " + data.seats + "</td></tr>" + 
            "<tr><td>State:</td><td> " + data.state + "</td></tr>" +
            "<tr><td>X-Password:</td><td> " + XPassword + "</td></tr>"                  
            );
}