<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>SOM Training Complete</title>
</head>
<body>
   Your option selected is <s:property value="criteriaOption"/> and your 
   SOM training is complete.
   
    <form action="visualSelection">
      <input type="submit" value="Click to go ahead with visualization"/>
   </form>
   
   
</body>
</html>