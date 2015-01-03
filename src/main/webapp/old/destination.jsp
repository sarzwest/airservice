<%-- 
    Document   : destination
    Created on : 16.10.2013, 14:04:49
    Author     : Banjo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>

        <script charset="UTF-8" src="JavaScripts/Desetination/deleteDestination.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/Desetination/getDestination.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/Desetination/getAllDestination.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/Desetination/editDestination.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/Desetination/addDestination.js" type="text/javascript"></script>

        <!--  
        <script charset="UTF-8" src="JavaScripts/editDestination.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script charset="UTF-8" src="JavaScripts/addDestination.js"></script>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>-->


        <title>Destination</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/errorFragment.jspf"/>
        <h1>Destination</h1>
        <div>
            <ul>
                <li><a href="destination.jsp">Destination</a></li>
                <li><a href="flight.jsp">Flight</a></li>
                <li><a href="reservationResource.jsp">Reservation Resource</a></li>
            </ul>
        </div>
        <div>
            <fieldset>
                <legend>Add destination</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="nameDest">Name of destination:</label>
                        </td><td>
                            <input type="text" id="nameDest"/>
                        </td></tr>
                </table>
                <button id="submitName">Submit</button>
            </fieldset>
            <fieldset>
                <legend>Get destination</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="idDest">ID of destination</label>
                        </td><td>
                            <input type="text" id="idDest"/>
                        </td></tr>
                </table>
                <button id="submitID">Get</button>
                <br/>
                <p id="idDestName"></p>

            </fieldset>
            <fieldset>
                <legend>Edit destination</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="idDestEdit">ID of destination</label>
                        </td><td>
                            <input type="text" id="idDestEdit"/>
                        </td></tr>
                    <tr><td> 
                            <label for="nameDestEdit">New name destination</label>
                        </td><td>
                            <input type="text" id="nameDestEdit"/>
                        </td></tr>
                </table>
                <button id="submitEdit">Edit</button>
            </fieldset>
            <fieldset>
                <legend>Delete destination</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="idDestDelete">ID of destination</label>
                        </td><td>
                            <input type="text" id="idDestDelete"/>
                        </td></tr>
                </table>
                <button id="submitDelete">Delete</button>
            </fieldset>
            <fieldset>
                <legend>Get all destination</legend>
                <button id="submitAllDest">All destination</button>
                <table id="tableAllDestination" class="printResultTable">
                </table>
            </fieldset>
        </div>
    </body>
</html>
