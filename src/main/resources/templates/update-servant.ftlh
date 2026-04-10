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
          <a class="nav-link active" aria-current="page" href="/servants/all">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="/servants/about">About</a>
        </li>        
        <li class="nav-item">
          <a class="nav-link active" href="/servants/add">Summon a new servant</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" type="button" href="/servants/delete/${foundCharacter.servantId}">Delete this servant</a>
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
    <center><img src="/servant_pfp/${(foundCharacter.profilePicturePath)!'avatar.png'}" alt="mikon"></li></center>
    </div>
  </div>
</div>

<center><form id="edit-servant" action="/servants/update/${foundCharacter.servantId}" method="post" enctype="multipart/form-data">
      <input type="text" id="servantId" name="servantId" value="${foundCharacter.servantId}" readonly />  
      <input type="text" id="name" name="name" placeholder="Servant Name" />
      <input type="text" id="type" name="type" placeholder="Servant Class Type" />
      <input type="text" id="species" name="species" placeholder="Servant Species" />
      <input type="text" id="origin" name="origin" placeholder="Servant Origin" />
      <br><label for="picture" class="form-label"> Picture:</label>
      <input type="file" id="picture" name="picture" /><br>Servant description:<br>
      <textarea id="description" name="description" placeholder="Servant Description" rows = "5" cols="80"></textarea>
      <br><br>
      <button type="submit" class="btn btn-primary">Save</button>
      <a href="/all" class="btn btn-outline-danger">Cancel</a>
    </form></center>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>


