<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/errorFragment.jspf"/>
      <h1>AirService</h1>
        <div>
            <ul>
                <li><a href="security/security.jsp">Login</a></li>
                <li><a href="destination.jsp">Destination</a></li>
                <li><a href="flight.jsp">Flight</a></li>
                <li><a href="reservationResource.jsp">Reservation</a></li>
            </ul>
        </div>
    </body>
</html>
