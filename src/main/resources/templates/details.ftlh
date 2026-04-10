<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chaldeas</title>
    <link rel="icon" type="image/x-icon" href="servants/sq.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="https://github.com/seihaisensou"><img src="/servant_pfp/sq.webp" alt="quartz" width="30" height="30"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="all">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="about">About</a>
        </li>        
        <li class="nav-item">
          <a class="nav-link active" href="add">Summon a new servant</a>
        </li> 
        <li class="nav-item">
          <a class="nav-link active" type="button" href="updateForm/${foundCharacter.servantId}">Alter this servant's saint graph</a>
        </li>        
        <li class="nav-item">
          <a class="nav-link active" type="button" href="delete/${foundCharacter.servantId}">Delete this servant</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

    <center><h1>${foundCharacter.name}: Details</h1></center>

     
    

<div class="container text-center">
    <div class="row">
    <div class="col">
      <p> <b>${foundCharacter.name}</b><br>
        Class: ${foundCharacter.type}<br>
        Species: ${foundCharacter.species}<br>
        Origin: ${foundCharacter.origin}<br>
        Description: ${foundCharacter.description}</p>
    <center><img src="/servant_pfp/${(foundCharacter.profilePicturePath)!'avatar.png'}" alt="oops"></li></center>
    </div>
  </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>


