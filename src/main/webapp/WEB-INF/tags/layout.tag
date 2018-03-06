<%@tag description="Simple Page with Polytech Logo" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<jsp:invoke fragment="head"/>
		<script src="resources/js/jQuery.js"></script>
		<script src="resources/js/bootstrap.js"></script>
		<script src="resources/js/custom.js"></script>
		<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-grid.min.css.css" />
		<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-reboot.min.css" />
		<link rel="stylesheet" type="text/css" href="resources/css/custom.css">
	</head>

	<jsp:invoke fragment="scripts"/>

	<body>
		<div class="container" style="margin-top: 15px;">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 center">
					<img class="col-xs-6 col-xs-offset-3 col-md-2 col-md-offset-0" style="vertical-align: middle;" src="resources/img/polytech.png">
					<h1 class="blue col-xs-12 col-md-10">Médiathèque de POLYTECH</h1>
				</div>
			</div>
			<jsp:doBody/>
		</div>
	</body>
</html>