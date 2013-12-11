<%-- 
    Document   : reservationResource
    Created on : 21.10.2013, 10:21:24
    Author     : Banjo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script charset="UTF-8" src="JavaScripts/ReservationResource/deleteReservation.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/ReservationResource/getReservation.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/ReservationResource/getAllReservation.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/ReservationResource/editReservation.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/ReservationResource/addReservation.js" type="text/javascript"></script>
        <title>Reservation resource</title>
      <!--  <script>
            var XPassword;
        </script> -->
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/errorFragment.jspf"/>
        <h1>Reservation resource</h1>
        <div>
            <ul>
                <li><a href="destination.jsp">Destination</a></li>
                <li><a href="flight.jsp">Flight</a></li>
                <li><a href="reservationResource.jsp">Reservation Resource</a></li>
            </ul>
        </div>
        <div>
            <fieldset>
                <legend>Add reservation</legend>
                <table class="formTable">
                    <tr>
                        <td>
                            <label for="addReservationFightID">Flight ID: </label>
                        </td>
                        <td>
                            <input type="text" id="addReservationFlightID"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="addReservationSeats">Number of seats: </label>
                        </td>
                        <td>
                            <input type="text" id="addReservationSeats"/>
                        </td>
                    </tr>  

                </table>
                <button id="submitAddReservation">Submit</button>
                <table id="addReservationTable" class="formTable">        
                </table>
            </fieldset>
            <fieldset>
                <legend>Get reservation</legend>
                <table class="formTable">
                    <tr>
                        <td>
                            <label for="getReservationFlightID">Flight ID: </label>
                        </td>
                        <td>
                            <input type="text" id="getReservationFlightID"/>
                        </td>
                    </tr>    
                    <tr>
                        <td>
                            <label for="getReservationID">Reservation ID: </label>
                        </td>
                        <td>
                            <input type="text" id="getReservationID"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="getReservationXPassword">X-Password: </label>
                        </td>
                        <td>
                            <input type="text" id="getReservationXPassword"/>
                        </td>
                    </tr>
                </table>
                <button id="submitGetReservation">Get</button>
                <table id="getReservationTable" class="formTable">        
                </table>
            </fieldset>
            <fieldset>
                <legend>Edit reservation</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="editReservationFlightID">Flight ID: </label>
                        </td><td>
                            <input type="text" id="editReservationFlightID"/>
                        </td></tr>   
                        <tr><td>
                            <label for="editReservationId">Reservation ID: </label>
                        </td><td>
                            <input type="text" id="editReservationId"/>
                        </td></tr>
                        <tr><td>
                            <label for="editReservationState">New state: </label>
                        </td><td>
                            <select id="editReservationState">
                                <option value="NEW">NEW</option>
                                <option value="CANCELED">CANCELED</option>
                                <option value="PAID">PAID</option>
                            </select>
                        </td></tr>
                    <tr>
                        <td>
                            <label for="editReservationXPassword">X-Password: </label>
                        </td>
                        <td>
                            <input type="text" id="editReservationXPassword"/>
                        </td>
                    </tr>
                </table>
                <button id="submitEditReservation">Edit</button>
                <table id="editReservationTable" class="formTable">        
                </table>
            </fieldset>
            <fieldset>
                <legend>Delete reservation</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="deleteReservationFlightID">Flight ID: </label>
                        </td><td>
                            <input type="text" id="deleteReservationFlightID"/>
                        </td></tr>    
                        <tr><td>
                            <label for="deleteReservationID">Reservation ID: </label>
                        </td><td>
                            <input type="text" id="deleteReservationID"/>
                        </td></tr>
                    <tr>
                        <td>
                            <label for="deleteReservationXPassword">X-Password: </label>
                        </td>
                        <td>
                            <input type="text" id="deleteReservationXPassword"/>
                        </td>
                    </tr>
                </table>
                <button id="submitDeleteReservation">Delete</button>
            </fieldset>
            <fieldset>
                <legend>Get all reservation</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="getAllReservationFlightID">Flight ID: </label>
                        </td><td>
                            <input type="text" id="getAllReservationFlightID"/>
                        </td></tr>    
                </table>
                <button id="submitAllReservation">All reservation</button>
                <table id="tableAllReservation" class="printResultTable">
                </table>
            </fieldset>
        </div>
    </body>
</html>
