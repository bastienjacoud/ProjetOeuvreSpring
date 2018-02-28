<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="head">
		<title>Affichage des Adhérents</title>
	</jsp:attribute>

	<jsp:attribute name="scripts">
	</jsp:attribute>

	<jsp:body>
		<div class="row">
			<div class="col-xs-2">
				<t:arrow></t:arrow>
			</div>
			<div class="col-xs-8">
				<h2 class="center">
					Listing des Adhérents
				</h2>
			</div>
		</div>

		<br />

		<div class="row">
			<table class="table tableCenter">
				<tr>
					<th>Numero</th>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Ville</th>

				</tr>

				<c:forEach items="${mesAdherents}" var="item">
					<tr>
						<td>${item.idAdherent}</td>
						<td>${item.nomAdherent}</td>
						<td>${item.prenomAdherent}</td>
						<td>${item.villeAdherent}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</jsp:body>
</t:layout>