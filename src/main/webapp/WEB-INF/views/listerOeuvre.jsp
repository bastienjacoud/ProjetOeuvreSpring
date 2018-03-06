<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html xmlns:c="http://java.sun.com/jsp/jstl/core"
xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">

<t:layout>
		<jsp:attribute name="head">
			<title>Affichage de toutes les oeuvres en vente</title>
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
                    Listing des Œuvres
                </h2>
            </div>
        </div>

        <br/>

        <div class="row">
            <table class="table tableCenter">
                <tr>
                    <th>
                        Titre
                    </th>
                    <th>
                        Prix
                    </th>
                    <th class="center">
                        Prénom Propriétaire
                    </th>
                    <th>
                        Nom Propriétaire
                    </th>
                    <th>
                        Réserver - Modifier
                    </th>
                </tr>

                <c:forEach items="${mesOeuvresV}" var="item">
                    <tr>
                        <td>
                                ${item.titreOeuvrevente}
                        </td>
                        <td>
                                ${item.prixOeuvrevente}
                        </td>
                        <td>
                                ${item.proprietaire.prenomProprietaire}
                        </td>
                        <td>
                                ${item.proprietaire.nomProprietaire}
                        </td>
                        <td>
                            <a href="reserverMenu.htm?idOeuvre=${item.idOeuvrevente}">
                                <input type="button" class="btn btn-info btn-lg" value="Réserver"/>
                            </a>
                            &nbsp;
                            &nbsp;
                            <a href="form_modifierOeuvre.htm?idOeuvre=${item.idOeuvrevente}">
                                <input type="button" class="btn btn-info btn-lg" value="Modifier"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:body>
</t:layout>