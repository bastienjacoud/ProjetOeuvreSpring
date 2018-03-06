<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
<jsp:attribute name="head">
<meta http-equiv="refresh" content="0;URL=javascript:fermer();">
<title>Expo : Médiathèque De POLYTECH</title>
</jsp:attribute>

<jsp:attribute name="scripts">
<script language="JavaScript">
	function fermer() {

	}
</script>
</jsp:attribute>

<jsp:body>
<div class="row">
	<h2 class="col-xs-12 center">
		Gestion de l'exposition 2016
	</h2>
</div>

<br />
<br />

<div class="row">
	<div class="col-xs-10 col-xs-offset-1">
		<div class="row">
			<div class="col-md-3 col-sm-6 col-xs-12">
				<a href="ajouterAdherent.htm">
					<div class="pan pan-green">
						<div class="pan-body"><span class="glyphicon glyphicon-plus"></span></div>
						<div class="pan-footer">Ajouter un Adhérent</div>
					</div>
				</a>
			</div>
			<div class="col-md-3 col-sm-6 col-xs-12">
                <a href="ajouterOeuvre.htm">
                    <div class="pan pan-green">
                        <div class="pan-body"><span class="glyphicon glyphicon-plus"></span></div>
                        <div class="pan-footer">Ajouter une Oeuvre</div>
                    </div>
                </a>
            </div>
			<div class="col-md-3 col-sm-6 col-xs-12">
				<a href="listerAdherent.htm">
					<div class="pan pan-blue">
						<div class="pan-body"><span class="glyphicon glyphicon-edit"></span></div>
						<div class="pan-footer">Lister les Adhérents</div>
					</div>
				</a>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3">
				<a href="Controleur?action=listerOeuvre">
					<div class="pan pan-blue">
						<div class="pan-body"><span class="glyphicon glyphicon-duplicate"></span></div>
						<div class="pan-footer">Lister les Oeuvres en vente</div>
					</div>
				</a>
			</div>
			<div class="col-md-3 col-sm-6 col-xs-12">
				<a href="javascript:fermer()">
					<div class="pan pan-red">
						<div class="pan-body"><span class="glyphicon glyphicon-remove-sign"></span></div>
						<div class="pan-footer">Fermer</div>
					</div>
					<!-- Does not work -->
				</a>
			</div>
		</div>
	</div>
</div>
</jsp:body>
</t:layout>