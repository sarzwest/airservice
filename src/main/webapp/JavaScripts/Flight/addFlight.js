/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $("#submitAddFlight").click(function() {
        addFlight();
    });
    
    $( "#addFlightDate" ).datepicker();
    $("#addFlightTime").timepicker();
});


function addFlight() {
    var xcurrency = $("#addFlightCurrency").val();
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: "rest/flight/",
        dataType: "json",
        data: formToJSONFlight(),
        headers: {
            'X-Currency' : xcurrency
        },
        success: function(data, textStatus, jqXHR){          
          alert('Flight created successfully');
            
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('addFlight error: ' + jqXHR.responseText);
        }
    });
}
;



function formToJSONFlight() {
     
    return JSON.stringify({
        "dateOfDeparture": parseDateFormat($("#addFlightDate").val(), $("#addFlightTime").val(), $("#addFlightTimeZone").val()),
        "distance": $("#addFlightDistance").val,
        "from": $("#addFlightFrom").val(),
        "name": $("#addFlightName").val(),
        "price": $("#addFlightPrice").val(),
        "seats": $("#addFlightSeats").val(),
        "to": $("#addFlightTo").val()
    });
}
;

function parseDateFormat(d, t, z) {
    var partsDate = d.split('/');
    var partsTime = t.split(':');
    var newDate = partsDate[2] + "-" + partsDate[0] + "-" + partsDate[1] + "T" + partsTime[0] + ":" + partsTime[1] + ":00" + z; 
    return newDate;
};


function formToJSON2() {
    return JSON.stringify({
        "name": $("#addFlightName").val(),
        "dateOfDeparture": "2013-01-31T12:00:00+0100",
        "distance": "453.2",
        "from": "1",
        "price": "20000.0", 
        "seats": "100",
        "to": "2"
        });
};

