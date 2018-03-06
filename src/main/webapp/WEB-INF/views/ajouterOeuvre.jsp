<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html xmlns:c="http://java.sun.com/jsp/jstl/core">

<t:layout>
        <jsp:attribute name="head">
            <title>Ajouter une Oeuvre</title>
        </jsp:attribute>

    <jsp:attribute name="scripts">
            <script type="text/javascript" src="/resources/js/fonctControle.js"></script>
        </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-xs-2">
                <t:arrow></t:arrow>
            </div>
            <div class="col-xs-8">
                <h2 class="center">
                    Ajout d'une Oeuvre
                </h2>
            </div>
        </div>

        <br/>

        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <form name='ajouterOeuvre' method="post" action="insererOeuvre.htm">
                    <div class="row">
                        <div class="col-xs-4">
                            <label for="titre">Titre de l'oeuvre </label>
                        </div>
                        <div class="col-xs-8">
                            <input name="titre" class="form-control" id="titre" value="">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-4">
                            <label for="prix">Prix </label>
                        </div>
                        <div class="col-xs-8">
                            <input type="number" name="prix" class="form-control" id="prix" value="">

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-4">
                            <label for="proprietaire">Propriétaire </label>
                        </div>
                        <div class="col-xs-8">
                            <select name="idProprietaire" class="form-control" id="proprietaire" required>
                                <c:forEach items="${mesProprietaires}" var="prop">
                                    <option value="${prop.idProprietaire}">${prop.prenomProprietaire} ${prop.nomProprietaire}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <br/>

                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-4">
                            <!-- Bouton Ajouter -->
                            <input class="btn btn-info btn-lg" type="submit" name="bt" value="Ajouter">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:layout>

</html>