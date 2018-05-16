<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

 <!-- Bootstrap core CSS -->
    <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style2.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <form action="SendingServlet" method="post">
    <form class="form-signin">
      <img class="mb-4" src="https://tmpfilecdn.freelogodesign.org/87ca5115-7e79-4ae3-abcc-a0ceff2f7893.png" alt="" width="250" height="200">
      <p><em>Un lien sera envoyé sur votre adresse mail afin d'accéder au formulaire d'inscription</em></p>
      
      <h1 class="h3 mb-3 font-weight-normal">Inscrivez-vous ici</h1>
      <label for="inputEmail" class="sr-only">Adresse mail:</label>
      <input type="email" name="recipient" id="inputEmail" class="form-control" placeholder="Email address" required autofocus><br><br>
      
      <button class="btn btn-lg btn-primary btn-block" type="submit">Envoi</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
    </form>
  </body>
</html>