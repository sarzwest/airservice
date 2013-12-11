/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var destination;

$(document).ready(function() {
    $("#submitGetFlight").click(function() {
        getFlight();
    });
});


function getFlight() {
    var id = $("#getFlightID").val();
    var xcurrency = $("#getFlightCurrency").val();
    $.ajax({
        type: 'GET',
        url: "rest/flight/" + id,
        dataType: "json",
        headers: {
            'X-Currency' : xcurrency
        },
        success: function(data) {
            destination = data;
            renderDetailsFlightGet(destination, xcurrency);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('getFlight error: ' + ": " + jqXHR.responseText);
        }
    });
}



function renderDetailsFlightGet(data, xcurrency) {
    	$('#getFlightTable tr').remove();
        $('#getFlightTable th').remove();
        $('#getFlightTable td').remove();
	$('#getFlightTable').append(
            "<tr><td>ID: </td><td>" + data.id + "</td></tr>" +
            "<tr><td>Name: </td><td> " + data.name + "</td></tr>" + 
            "<tr><td>Date of departure: </td><td> " +  data.dateOfDeparture + "</td></tr>" + 
            "<tr><td>Distance:</td><td> " + data.distance + "</td></tr>" + 
            "<tr><td>Price:</td><td> " + data.price + " " + xcurrency + "</td></tr>" + 
            "<tr><td>Seats: </td><td> " + data.seats + "</td></tr>" + 
            "<tr><td>From: </td><td> " + data.from.name + "</td></tr>" + 
            "<tr><td>To: </td><td> " + data.to.name + "</td></tr>"                    
            );
}