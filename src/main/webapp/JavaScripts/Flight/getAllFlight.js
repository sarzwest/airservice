/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var destination;

$(document).ready(function() {
    $("#submitAllFlights").click(function() {
        getAllFlights();
    });
    $("#getAllFlightFilterSinceDate").datepicker();
    $("#getAllFlightFilterSinceTime").timepicker();
    $("#getAllFlightFilterToDate").datepicker();
    $("#getAllFlightFilterToTime").timepicker();
});


function getAllFlights() {
    var filterFrom = parseDateFormat( $('#getAllFlightFilterSinceDate').val() , $('#getAllFlightFilterSinceTime').val(), $('#getAllFlightFilterSinceTimeZone').val());
    var filterTo = parseDateFormat( $('#getAllFlightFilterToDate').val() , $('#getAllFlightFilterToTime').val(), $('#getAllFlightFilterToTimeZone').val());
    var base = $('#getAllFlightFilterBase').val();
    var offset = $('#getAllFlightFilterOffset').val();
    var order = $('#getAllFlightFilterOrder').val();
    var currency = $("#getAllFlightFilterCurrency").val();
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: "rest/flight/",
        dataType: "json",
        headers: {
            'X-Filter' : 'dateOfDepartureFrom=' + filterFrom + ',dateOfDepartureTo='+filterTo,
            'X-Base': base,
            'X-Offset': offset,
            'X-Order': order,
            'X-Currency': currency
        },
        success: function(data) {
            renderListAllFlights(data.flight, currency);  
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('getAllFlights error: ' + ": " + jqXHR.responseText );
        }
    });
}

function renderListAllFlights(data, currency) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#tableAllFlights tr').remove();
        $('#tableAllFlights th').remove();
        $('#tableAllFlights td').remove();
        $('#tableAllFlights').append("<tr><th>ID</th><th>Name</th><th>Date of departure</th><th>Distance</th><th>Price " + currency + "</th><th>Seats</th><th>From</th><th>To</th></tr>");
	$.each(list, function(index, flight) {
		$('#tableAllFlights').append('<tr><td>' + flight.id + '</td><td>' + flight.name + '</td><td>' + flight.dateOfDeparture + '</td><td>' + flight.distance + '</td><td>' + flight.price + '</td><td>' + flight.seats + '</td><td>' + flight.from.name + '</td><td>' + flight.to.name + '</td></tr>');
	});
}
