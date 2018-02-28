<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/resources/tags" %>

<t:layout>
	<jsp:attribute name="head">
		<title>Modifier une oeuvre</title>
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script language="Javascript" type="text/javascript"></script>
		<script type="text/javascript" src="/WEB-INF/resources/js/foncControle.js"></script>
	</jsp:attribute>


	<jsp:body>
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2">
				<h2 class="center">
					Formulaire de modifications d'oeuvres
				</h2>
			</div>
		</div>

		<div class="row center" style="text-align: left">
			<form name='identification' method="post" action="Controleur?action=modifierOeuvre" onsubmit="return testeOeuvre()">
				<div class="col-xs-10 col-xs-offset-1">
					<!-- Champ caché -->
					<input type="hidden" name="idOeuvre" value="${oeuvre.idOeuvrevente}">

					<div class="row">
						<div class="col-xs-4">
							<label for="titre">
								Titre de l'œuvre :
							</label>
						</div>
						<div class="col-xs-8">
							<input class="form-control" type="text" name="txttitre" value="${oeuvre.titreOeuvrevente}" id ="titre">
						</div>
					</div>

					<div class="row">
						<div class="col-xs-4">
							<label for="prix">
								Prix :
							</label>
						</div>
						<div class="col-xs-8">
							<input class="form-control" type="text" name="txtprix" value="${oeuvre.prixOeuvrevente}" id ="prix">
						</div>
					</div>

					<div class="row">
						<div class="col-xs-4">
							<label for="proprio">
								Propriétaire :
							</label>
						</div>
						<div class="col-xs-8">
							<select name="prop" class="form-control" id="proprio">
								<c:forEach items="${mesProp}" var="item">
									<option value="${item.idProprietaire}">${item.nomProprietaire}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<br />
					
					<div class="row">
						<div class="col-xs-4 col-xs-offset-1">
							<!-- Boutons Ajouter -->
							<input type="submit" name="bt" class="btn btn-info btn-lg" value="Ajouter" />
						</div>
						<div class="col-xs-4 col-xs-offset-2">
							<a class="btn btn-light btn-lg" href="Controleur?action=listerOeuvre">Annuler</a>
						</div>
					</div>
				</div>
			</form>
		</div>
</jsp:body>
</t:layout>