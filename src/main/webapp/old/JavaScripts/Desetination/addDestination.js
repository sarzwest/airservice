/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#submitName").click(function() {
        addDestination();
    });
});

function addDestination() {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: "rest/destination/",
        dataType: "json",
        data: formToJSONDestAdd(),
        success: function(data, textStatus, jqXHR){
          alert('Destination created successfully')
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('addDestination error: ' + jqXHR.responseText);
        }
    });
};

 
function formToJSONDestAdd() {
    return JSON.stringify({
        "name": $("#nameDest").val()
        });
};
         