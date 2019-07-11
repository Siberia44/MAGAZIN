<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="login" method="post">
    <div class="form-group">
        <label>Name address</label>
        <input type="name" class="form-control" name="userName" aria-describedby="nameHelp" placeholder="Enter name">
        <small id="nameHelp" class="form-text text-muted">We'll never share your name with anyone else.</small>
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" name="userPassword" placeholder="Password">
    </div>
    <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="exampleCheck1">
        <label class="form-check-label" for="exampleCheck1">Check me out</label>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>