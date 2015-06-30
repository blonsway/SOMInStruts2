<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Self Organizing Map Generator</title>
</head>
<body>
   <h1>Train your own Data using our Self Organizing Map</h1>
   <form action="criteriaSelection">
      <label for="name">Please enter your option</label><br/>
      <select  name="criteriaOption">
      	<option value = "9">Situation Description</option>
      	<option value = "10">Mission Statement and Situation Description</option>
      </select>
      <input type="submit" value="Train Your SOM"/>
   </form>
</body>
</html>