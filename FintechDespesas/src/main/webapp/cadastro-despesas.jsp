<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cadastro de Despesas</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <div class="container">
        <h1>Cadastro de Despesas</h1>
        <c:if test="${not empty msg }">
            <div class="alert alert-success">${msg}</div>
        </c:if>
        <c:if test="${not empty erro }">
            <div class="alert alert-danger">${erro}</div>
        </c:if>
        <form action="despesas" method="post">
            <input type="hidden" value="cadastrar" name="acao">
            <div class="form-group">
                <label for="id-descricao">Descrição</label>
                <input type="text" name="descricao" id="id-descricao" class="form-control">
            </div>
            <div class="form-group">
                <label for="id-valor">Valor</label>
                <input type="text" name="valor" id="id-valor" class="form-control">
            </div>
            <div class="form-group">
                <label for="id-nome">Nome</label>
                <input type="text" name="nome" id="id-nome" class="form-control">
            </div>
            <div class="form-group">
                <label for="id-data">Data</label>
                <input type="text" name="data" id="id-data" class="form-control">
            </div>
            <input type="submit" value="Salvar" class="btn btn-primary">
        </form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>

