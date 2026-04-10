<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chaldeas</title>
    <link rel="icon" type="image/x-icon" href="servants/sq.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <style>
* {
  box-sizing: border-box;
    }

#myInput { 
  background-image: url('/css/searchicon.png'); /* Code taken from W3 https://www.w3schools.com/howto/howto_js_filter_lists.asp */
  background-position: 10px 12px;
  background-repeat: no-repeat;
  width: 50%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
  margin: auto;
  display: block;
}

#roster {
  list-style-type: none;
  padding:  0;
  margin: 0;
}

#roster li a {
  border: 1px solid #ddd;
  margin-top: -1px; 
  background-color: #f6f6f6;
  padding: 12px;
  text-decoration: none;
  font-size: 18px;
  color: black;
  display: block
}

#roster li a:hover:not(.header) {
  background-color: #eee;
}


</style>
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
      </ul>
    </div>
  </div>
</nav>


<center><h1>A gallery of F/GO servants.</h1></center>

<div class="d-flex gap-2 justify-content-center py-5"> 
  <a span class="badge text-bg-primary rounded-pill" href="/servants/all/Saber">Saber</span></a>
   <a span class="badge text-bg-secondary rounded-pill" href="/servants/all/Archer">Archer</span></a>
    <a span class="badge text-bg-success rounded-pill"  href="/servants/all/Lancer">Lancer</span></a>
     <a span class="badge text-bg-danger rounded-pill"  href="/servants/all/Rider">Rider</span></a>
      <a span class="badge text-bg-warning rounded-pill"  href="/servants/all/Caster">Caster</span></a>
       <a span class="badge text-bg-info rounded-pill"  href="/servants/all/Assassin">Assassin</span></a>
        <a span class="badge text-bg-light rounded-pill" href="/servants/all/Berserker">Berserker</span> </a>
  <a span class="badge text-bg-dark rounded-pill"  href="/servants/all/Avenger">Avenger</span></a>
</div>
    
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for details.." title="Type details"><br>

<ul id="roster">
    

<div class="container text-center">
  <div class="row row-cols-2">
    <#list servantRoster as servant>
    <div class="col"><li><a href="${servant.servantId}"><p>${servant.name}<br>
        Class: ${servant.type}<br>
        Species: ${servant.species}<br>
        Origin: ${servant.origin}</p>
    <img src="/servant_pfp/${(servant.profilePicturePath)!'avatar.png'}" alt="oops" width="256" height="362"></a></li></div>
    </#list>
  </div>
</div>


    
   
    
    
    
</ul>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<script>
function myFunction() {
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("roster");
    li = ul.getElementsByTagName("li"); /* Code taken from W3 https://www.w3schools.com/howto/howto_js_filter_lists.asp */
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}
</script>
</body>
</html>


