/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var destination;

$(document).ready(function() {
    $("#submitEditFlight").click(function() {
        editFlight();
    });
});


function editFlight() {
    var id = $("#editFlightID").val();
    var name = $("#editFlightName").val();
    var xcurrency = $("#editFlightCurrency").val();
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: "rest/flight/" + id,
        dataType: "json",
        data: formToJSONFlightEdit(name),
        headers: {
            'X-Currency' : xcurrency
        },
        success: function(data) {
            alert("Edit flight name: " + data.name);
            destination = data;
            renderDetailsFlightEdit(destination, xcurrency);
            
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('editFlight error: ' + ": " + jqXHR.responseText);
        }
    });
}


function formToJSONFlightEdit(name) {
    return JSON.stringify({
        "name": name
        });
};


function renderDetailsFlightEdit(data, xcurrency) {
    	$('#editFlightTable tr').remove();
        $('#editFlightTable th').remove();
        $('#editFlightTable td').remove();
	$('#editFlightTable').append(
            "<tr><td>ID: </td><td>" + data.id + "</td></tr>" +
            "<tr><td>Name: </td><td> " + data.name + "</td></tr>" + 
            "<tr><td>Date of departure: </td><td> " +  data.dateOfDeparture + "</td></tr>" + 
            "<tr><td>Distance:</td><td> " + data.distance + "</td></tr>" + 
            "<tr><td>Price:</td><td> " + data.price + " " + xcurrency +"</td></tr>" + 
            "<tr><td>Seats: </td><td> " + data.seats + "</td></tr>" + 
            "<tr><td>From: </td><td> " + data.from.name + "</td></tr>" + 
            "<tr><td>To: </td><td> " + data.to.name + "</td></tr>"                    
            );
}