<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	<link href="css/bootstrap.css" rel="stylesheet" />
	<link href="css/bootstrap-responsive.css" rel="stylesheet" />
	<link href="css/login.css" rel="stylesheet" />
	<script src="js/jquery.maskedinput.js" type="text/javascript"></script>
	
	<style>
		body {
			/*background-color:#D6E0E7; */
			background-color:#E8E8E8
		}
	</style>
	<script type="text/javascript">
		jQuery(function($) {
			$("#usuario").mask("999.999.999-99");
		});
		function focar() {
			document.getElementById("usuario").focus();
		}
	</script>
</head>
<body>
	<div class="container" align="center" style="margin-top: 70px">
	    <div class="row" style="width: 300px">
	        <div class="col-sm-6 col-md-4 col-md-offset-4">
	            <h1 class="text-center login-title"></h1>
	            <div class="account-wall" style="text-align: center">
	                <img src="imagens/ivolunteer.png" alt="logo" style="width: 200px"/>
	                <form class="form-signin" action="j_spring_security_check" method="post">
	                <%
						if (request.getParameter("msg") != null) {
						    out.print("<div class=\"col-md-12\"><span style='color: red;font-weight: bold;'>Usuário ou senha incorretos</span></div>");
						}
					%>
	                <div class="col-md-12" style="margin-bottom: 5px; margin-top: 10px;">
				 		<label for="j_username">Login:</label> 
						<input type="text" id="usuario" name="j_username" class="form-control" placeholder="Informe seu login" required="true" autofocus="true" />
					</div>
	                </br>
	                <div class="col-md-12" style="margin-bottom: 5px;">	  	
						<label for="j_password">SENHA:</label>
						<input name="j_password" class="form-control" type="password" placeholder="Informe sua senha."   />
					</div>
					<div>
	                	<button class="btn btn-xg btn-primary btn-block" type="submit">Login</button>
					</div>
	                <!-- 
	                <div class="col-md-6">
	                	<a href="#" onclick="usuarioExterno()" class="pull-left need-help" style="color: #05B856">Novo cadastro</a><span class="clearfix"></span>
	                </div>
	                 -->
	                <div class="col-md-6">
	                	<a href="#" onclick="recuperarSenha()" class="pull-right need-help">Recuperar senha</a><span class="clearfix"></span>
	                </div>
	                </br></br>
	                </form>
	            </div>
	            <a href="#" class="text-center new-account"></a>
	            <div class="col-md-12" style="margin-bottom: 50px; margin-top: 50px;">
					<div class="col-md-6">
					</div>
					<div class="col-md-6">
					</div>
		
				</div>
	        </div>
	    </div>
	</div>
</body>
</html>