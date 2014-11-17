/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var destination;

$(document).ready(function() {
    $("#submitAllReservation").click(function() {
        getAllReservation();
    });
});


function getAllReservation() {
    var flightId = $("#getAllReservationFlightID").val();
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: "rest/flight/" + flightId + "/reservation/",
        dataType: "json",
        success: function(data) {
            renderListAllReservation(data);  
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('getAllReservation error: ' +  jqXHR.responseText );
        }
    });
}

function renderListAllReservation(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
        
	$('#tableAllReservation tr').remove();
        $('#tableAllReservations th').remove();
        $('#tableAllReservation td').remove();
        $('#tableAllReservation').append("<tr><th>ID</th><th>Flight from</th><th>Flight to</th><th>Created</th><th>Seats</th><th>State</th></tr>");
	$.each(list, function(index, reservation) {
		$('#tableAllReservation').append('<tr><td>' + reservation.id + '</td><td>' + reservation.flight.from.name + '</td><td>'+ reservation.flight.to.name +'</td><td>' + reservation.created + '</td><td>' + reservation.seats + '</td><td>' + reservation.state + '</td></tr>');
	});
}