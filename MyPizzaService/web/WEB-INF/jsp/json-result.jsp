<%-- 
    Document   : Prints data to Json format
    Author     : ProvenSoft
--%>
<%@page import="proven.mypizza.servlets.MyPizzaRequestResult"%>
<%@page import="com.google.gson.Gson"%>
<% 
    MyPizzaRequestResult result = (MyPizzaRequestResult) request.getAttribute("result"); 
//// Method 1
//    int resultCode = result.getResultCode();
//    if (resultCode ==1) {
//        out.print(new Gson().toJson(result.getData()));        
//    } else {
//        out.print(new Gson().toJson(new Integer(resultCode)));  
//    }
//// Method 2
//    Object data = result.getData();
//    if (data != null) {
//        out.print(new Gson().toJson(result.getData()));      
//    }
//    int resultCode = result.getResultCode();
//    out.print(new Gson().toJson(resultCode));  
//// Method 3
    out.print(new Gson().toJson(result));
%>