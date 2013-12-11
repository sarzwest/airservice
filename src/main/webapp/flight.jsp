<%-- 
    Document   : flight
    Created on : 20.10.2013, 17:12:16
    Author     : Banjo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="JavaScripts/jquery.ui.timepicker.js"></script>
        <script charset="UTF-8" src="JavaScripts/Flight/deleteFlight.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/Flight/getFlight.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/Flight/getAllFlight.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/Flight/editFlight.js" type="text/javascript"></script>
        <script charset="UTF-8" src="JavaScripts/Flight/addFlight.js" type="text/javascript"></script>
        <title>Flight</title>
  

    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/errorFragment.jspf"/>
        <h1>Flight</h1>
        <div>
            <ul>
                <li><a href="destination.jsp">Destination</a></li>
                <li><a href="flight.jsp">Flight</a></li>
                <li><a href="reservationResource.jsp">Reservation Resource</a></li>
            </ul>
        </div>
        <div>
            <fieldset>
                <legend>Add flight</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="addFlightName">Flight name: </label>
                        </td><td>
                            <input type="text" name="addNameOfFlight" id="addFlightName"/>
                            
                        </td></tr>
                    <tr><td>    
                            <label for="addFlightDate">Date of departure: </label>
                        </td><td>
                            <input type="text"  id="addFlightDate"/>
                            <!-- <label for="addFlightDate">Format: YYYY-MM-DDThh:mm:ssZ</label> -->                            
                        </td></tr>
                    <tr><td>    
                            <label for="addFlightDate">Time of departure: </label>
                        </td><td>
                            <input type="text" id="addFlightTime"/>
                         
                        </td></tr>
                    <tr><td>    
                            <label for="addFlightDate">Time zone: </label>
                        </td><td>
                            <select id="addFlightTimeZone" name="timeZones">
                                <option value="-1200">UTC-12:00</option>
                                <option value="-1100">UTC-11:00</option>
                                <option value="-1000">UTC-10:00</option>
                                <option value="-0900">UTC-09:00</option>
                                <option value="-0830">UTC-08:30</option>
                                <option value="-0800">UTC-08:00</option>
                                <option value="-0700">UTC-07:00</option>
                                <option value="-0600">UTC-06:00</option>
                                <option value="-0530">UTC-05:30</option>
                                <option value="-0500">UTC-05:00</option>
                                <option value="-0430">UTC-04:30</option>
                                <option value="-0400">UTC-04:00</option>
                                <option value="-0330">UTC-03:30</option>
                                <option value="-0300">UTC-03:00</option>
                                <option value="-0200">UTC-02:00</option>
                                <option value="-0100">UTC-01:00</option> 
                                <option value="+0000">UTC+00:00 London</option>
                                <option selected="" value="+0100">UTC+01:00 Praha</option>
                                <option value="+0200">UTC+02:00</option>
                                <option value="+0300">UTC+03:00</option>
                                <option value="+0330">UTC+03:30</option>
                                <option value="+0400">UTC+04:00</option>
                                <option value="+0430">UTC+04:30</option>
                                <option value="+0500">UTC+05:00</option>
                                <option value="+0530">UTC+05:30</option>
                                <option value="+0545">UTC+05:45</option>
                                <option value="+0600">UTC+06:00</option>
                                <option value="+0630">UTC+06:30</option>
                                <option value="+0700">UTC+07:00</option>
                                <option value="+0800">UTC+08:00</option>
                                <option value="+0900">UTC+09:00</option>
                                <option value="+0930">UTC+09:30</option>
                                <option value="+1000">UTC+10:00</option>
                                <option value="+1100">UTC+11:00</option>
                                <option value="+1200">UTC+12:00</option>
                                <option value="+1300">UTC+13:00</option>                          
                            </select>                         
                        </td></tr>
                    <tr><td>   
                            <label for="addFlightDistance">Distance: </label>
                        </td><td>
                            <input type="text" id="addFlightDistance"/>
                        </td></tr>
                    <tr><td>   
                            <label for="addFlightPrice">Price: </label>
                        </td><td>
                            <input type="text"  id="addFlightPrice"/>
                        </td></tr>
                    <tr><td>   
                            <label for="addFlightSeats">Seats: </label>
                        </td><td>
                            <input type="text"  id="addFlightSeats"/>
                        </td></tr>
                    <tr><td>   
                            <label for="addFlightFrom">From: </label>
                        </td><td>
                            <input type="text"  id="addFlightFrom"/>
                        </td></tr>
                    <tr><td>   
                            <label for="addFlightTo">To: </label>
                        </td><td>
                            <input type="text"  id="addFlightTo"/>
                        </td></tr>
                        <tr><td>   
                            <label for="addFlightCurrency">X-Currency: </label>
                        </td><td>
                            <input type="text"  id="addFlightCurrency"/>
                        </td></tr>
                </table>
                <button id="submitAddFlight">Submit</button>
            </fieldset>
            <fieldset>
                <legend>Get fight</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="getFlightID">Flight ID: </label>
                        </td><td>
                            <input type="text" name="getIdOfFlight" id="getFlightID"/>
                        </td></tr>     
                         <tr><td>
                            <label for="getFlightCurrency">X-Currency: </label>
                        </td><td>
                            <input type="text" name="getIdOfFlight" id="getFlightCurrency"/>
                        </td></tr> 
                </table>
                <button id="submitGetFlight">Get</button>
                <table id="getFlightTable" class="formTable">        
                </table>
            </fieldset>
            <fieldset>
                <legend>Edit fight</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="editFlightID">Flight ID: </label>
                        </td><td>
                            <input type="text" id="editFlightID"/>
                        </td></tr>   
                        <tr><td>
                            <label for="editFlightName">New name: </label>
                        </td><td>
                            <input type="text" id="editFlightName"/>
                        </td></tr>
                        <tr><td>
                            <label for="editFlightCurrency">X-Currency: </label>
                        </td><td>
                            <input type="text" id="editFlightCurrency"/>
                        </td></tr>
                </table>
                <button id="submitEditFlight">Edit</button>
                <table id="editFlightTable" class="formTable">        
                </table>
            </fieldset>
            <fieldset>
                <legend>Delete fight</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="deleteFlightID">Flight ID: </label>
                        </td><td>
                            <input type="text" id="deleteFlightID"/>
                        </td></tr>                
                </table>
                <button id="submitDeleteFlight">Delete</button>
            </fieldset>
            <fieldset>
                <legend>Get all flights</legend>
                <table class="formTable">
                    <tr><td>
                            <label for="getAllFlightFilterSince">Filter Since: </label>
                        </td><td>
                            <input type="text" id="getAllFlightFilterSinceDate"/>
                            <input type="text" id="getAllFlightFilterSinceTime"/>
                            <select id="getAllFlightFilterSinceTimeZone" name="timeZones">
                                <option value="-1200">UTC-12:00</option>
                                <option value="-1100">UTC-11:00</option>
                                <option value="-1000">UTC-10:00</option>
                                <option value="-0900">UTC-09:00</option>
                                <option value="-0830">UTC-08:30</option>
                                <option value="-0800">UTC-08:00</option>
                                <option value="-0700">UTC-07:00</option>
                                <option value="-0600">UTC-06:00</option>
                                <option value="-0530">UTC-05:30</option>
                                <option value="-0500">UTC-05:00</option>
                                <option value="-0430">UTC-04:30</option>
                                <option value="-0400">UTC-04:00</option>
                                <option value="-0330">UTC-03:30</option>
                                <option value="-0300">UTC-03:00</option>
                                <option value="-0200">UTC-02:00</option>
                                <option value="-0100">UTC-01:00</option> 
                                <option value="+0000">UTC+00:00 London</option>
                                <option selected="" value="+0100">UTC+01:00 Praha</option>
                                <option value="+0200">UTC+02:00</option>
                                <option value="+0300">UTC+03:00</option>
                                <option value="+0330">UTC+03:30</option>
                                <option value="+0400">UTC+04:00</option>
                                <option value="+0430">UTC+04:30</option>
                                <option value="+0500">UTC+05:00</option>
                                <option value="+0530">UTC+05:30</option>
                                <option value="+0545">UTC+05:45</option>
                                <option value="+0600">UTC+06:00</option>
                                <option value="+0630">UTC+06:30</option>
                                <option value="+0700">UTC+07:00</option>
                                <option value="+0800">UTC+08:00</option>
                                <option value="+0900">UTC+09:00</option>
                                <option value="+0930">UTC+09:30</option>
                                <option value="+1000">UTC+10:00</option>
                                <option value="+1100">UTC+11:00</option>
                                <option value="+1200">UTC+12:00</option>
                                <option value="+1300">UTC+13:00</option>                          
                            </select> 
                          <!--  <label for="addFlightDate">Format: YYYY-MM-DDThh:mm:ssZ</label> -->
                        </td></tr>     
                        <tr><td>
                            <label for="getAllFlightFilterTo">Filter To: </label>
                        </td><td>
                            <input type="text" id="getAllFlightFilterToDate"/>
                            <input type="text" id="getAllFlightFilterToTime"/>
                            <select id="getAllFlightFilterToTimeZone" name="timeZones">
                                <option value="-1200">UTC-12:00</option>
                                <option value="-1100">UTC-11:00</option>
                                <option value="-1000">UTC-10:00</option>
                                <option value="-0900">UTC-09:00</option>
                                <option value="-0830">UTC-08:30</option>
                                <option value="-0800">UTC-08:00</option>
                                <option value="-0700">UTC-07:00</option>
                                <option value="-0600">UTC-06:00</option>
                                <option value="-0530">UTC-05:30</option>
                                <option value="-0500">UTC-05:00</option>
                                <option value="-0430">UTC-04:30</option>
                                <option value="-0400">UTC-04:00</option>
                                <option value="-0330">UTC-03:30</option>
                                <option value="-0300">UTC-03:00</option>
                                <option value="-0200">UTC-02:00</option>
                                <option value="-0100">UTC-01:00</option> 
                                <option value="+0000">UTC+00:00 London</option>
                                <option selected="" value="+0100">UTC+01:00 Praha</option>
                                <option value="+0200">UTC+02:00</option>
                                <option value="+0300">UTC+03:00</option>
                                <option value="+0330">UTC+03:30</option>
                                <option value="+0400">UTC+04:00</option>
                                <option value="+0430">UTC+04:30</option>
                                <option value="+0500">UTC+05:00</option>
                                <option value="+0530">UTC+05:30</option>
                                <option value="+0545">UTC+05:45</option>
                                <option value="+0600">UTC+06:00</option>
                                <option value="+0630">UTC+06:30</option>
                                <option value="+0700">UTC+07:00</option>
                                <option value="+0800">UTC+08:00</option>
                                <option value="+0900">UTC+09:00</option>
                                <option value="+0930">UTC+09:30</option>
                                <option value="+1000">UTC+10:00</option>
                                <option value="+1100">UTC+11:00</option>
                                <option value="+1200">UTC+12:00</option>
                                <option value="+1300">UTC+13:00</option>                           
                            </select> 
                         <!--   <label for="addFlightDate">Format: YYYY-MM-DDThh:mm:ssZ</label> -->
                        </td></tr> 
                        <tr><td>
                            <label for="getAllFlightFilterBase">Filter X-Base: </label>
                        </td><td>
                            <input type="text" id="getAllFlightFilterBase"/>
                        </td></tr>
                        <tr><td>
                            <label for="getAllFlightFilterOffset">Filter X-Offset: </label>
                        </td><td>
                            <input type="text" id="getAllFlightFilterOffset"/>
                        </td></tr>
                        <tr><td>
                            <label for="getAllFlightFilterOrder">Filter X-Order: </label>
                        </td><td>
                            <input type="text" id="getAllFlightFilterOrder"/>
                        </td></tr>      
                        <tr><td>
                            <label for="getAllFlightFilterCurrency">X-Currency: </label>
                        </td><td>
                            <input type="text" id="getAllFlightFilterCurrency"/>
                        </td></tr>                         
                </table>
                <button id="submitAllFlights">All flight</button>
                <table id="tableAllFlights" class="printResultTable">
                </table>
            </fieldset>
        </div>
    </body>
</html>
