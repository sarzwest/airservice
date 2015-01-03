/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var destination;

$(document).ready(function() {
    $("#submitAllDest").click(function() {
        getAllDestination();
    });
});


function getAllDestination() {
    $.ajax({
        type: 'GET',
        url: "rest/destination/",
        dataType: "json",
        success: function(data) {
            renderListAllDest(data);   
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('getAllDestination error: ' + ": " + jqXHR.responseText);
        }
    });
}

function renderListAllDest(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#tableAllDestination tr').remove();
        $('#tableAllDestination th').remove();
        $('#tableAllDestination td').remove();
        $('#tableAllDestination').append("<tr><th>ID</th><th>Name</th></tr>")
	$.each(list, function(index, destination) {
		$('#tableAllDestination').append('<tr><td>' + destination.id + '</td><td>' + destination.name + '</td></tr>');
	});
}