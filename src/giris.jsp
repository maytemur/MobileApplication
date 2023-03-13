<html>
<head><title>GIRIS</title></head>
<body>
<%
String isim=request.getParameter("isim");
String pazar=request.getParameter("pazar");
 
if(!isim.equals(pazar)){
    
pageContext.forward("hata.jsp");

}
%>
<table><tr><td>
    <h2>Hosgeldiniz</h2></td></tr>
    <tr><td align="center">
    <h2><%=isim%></h2></td></tr>
    <tr><td align="center">
    <h2><%=pazar%></h2></td></tr>
    </body>
</html>
