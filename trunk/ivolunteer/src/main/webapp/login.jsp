<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>IVOLUNTEER</title>

    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet" />

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<script>
		function focar() {
			document.getElementById("usuario").focus();
		}
	</script>
</head>
<body>
<form action="j_spring_security_check" method="post">
<div class="wrapper">
	<div class="header">
    	<div class="container">
        	<div class="row branding">
            	<div class="span6">
                	<h1 class="pull-left"><strong>IVOLUNTEER</strong></h1>
                </div>            	
            </div>  
            <div class="row navigation">
	           	<div class="span12">
                    <ul class="nav nav-tabs">
                      <li><a href="menu1.jsp">Saiba quem somos</a></li>
                      <li><a href="menu2.jsp">Menu 2</a></li>
                    </ul>
                </div>
            </div>
        </div>        
    </div>
	<div class="container content">
        <div class="row">
            <div class="span8 leftContent">
            	<h2>Quadro de Avisos</h2>
                <div class="row">
                	<div class="span4">
                    	<p class="cntPara simpleDesign" align="justify">
                        	<strong class="lead">Numero de Vagas Disponivies</strong>
							Conteúdo caixa 1                        	
                        </p>            	                                                
                    </div>
                    
                    <div class="span4">
                    	<p class="cntPara itsFree" align="justify">
                        	<strong class="lead">Numeros de Alunos</strong>
							Conteúdo caixa 2
                       </p>      
                    </div>                                        
                </div>
                <hr/>
                <div class="row">
                	<div class="span4">
                    	<p class="cntPara secureApp"  align="justify">
                        	<strong class="lead">Faça a sua Doação</strong>
							conteúdo caixa 3
                    </div>
                    
                    <div class="span4">
                    	<p class="cntPara easyUse">
                        	<strong class="lead">Atividades Realizadas</strong>
							conteúdo caixa 4
                         </p>      
                    </div>                                        
                </div>
                <hr/>   
                <div class="row">
                	<div class="span8">
                    	<h3 class="quickTour">Instituição Centro de Educação Infantil Francisca de Lima</h3>
                    </div>                                        
                </div>
            </div>
            
            <div class="span4 sidebar">
            	<!--  
                <h2><a href="#" class="btn btn-large btn-warning">Alguma ação</a></h2>
                -->
                <div class="well quickSignupForm">
                  <h3>Acesso ao Sistema</h3>
					<%
					if (request.getParameter("msg") != null) {
						out.print("<span style='color: red;font-weight: bold;'>Usuário ou senha incorretos</span>");
					}%>		
                  <label>Login</label>
                  <input type="text" id="usuario" name="j_username" class="span3"  />
                  <label>Senha</label>
                  <input name="j_password" type="password" class="span3" />                  
                  <input class="btn btn-large btn-success btnSignup" type="submit" value="Entrar" />
                </div>
                <!--  
                <h3>Titulo caixa</h3>
                <p>
					Conteúdo caixa 
                </p>
                -->                
            </div>
    	</div>
    </div>
</div>
</body>
</html>