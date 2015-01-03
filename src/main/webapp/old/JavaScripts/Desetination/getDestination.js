/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var destination;

$(document).ready(function() {
    $("#submitID").click(function() {
        getDestination();
    });
});


function getDestination() {
    var id = $("#idDest").val();
    $.ajax({
        type: 'GET',
        url: "rest/destination/" + id,
        dataType: "json",
        success: function(data) {
            destination = data;
            renderDetailsDestGet(destination);
            
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('getDestination error: ' + jqXHR.responseText );
        }
    });
}


function renderDetailsDestGet(data) {
	$('#idDestName').text("Name: " + data.name);
}