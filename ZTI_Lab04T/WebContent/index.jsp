<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lab04</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="styles.css">
	<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300&display=swap" rel="stylesheet">
</head>
<body>
    <nav>
	    <h1>ZTI, Lab 04</h1>
	    <h2 id="rest-header">RESTful - aplikacja klienta</h2>
    </nav>
    <section class="list">
    	<h2>Lista osób</h2>
    	<ul id="folks-list">
    	</ul>
    </section>
    
    <section class="record-addition">
    	<h2>Dodaj wpis: </h2>
    	<input type="text" id="fname" placeholder="input name ..."/>
    	<input type="text" id="lname" placeholder="input lastname ... "/>
    	<input type="text" id="email" placeholder="input email ... "/>
    	<input type="text" id="city" placeholder="input city ... "/>
    	<input type="text" id="tel" placeholder="input tel ... "/>
		<button type="submit" id="submit-btn">OK</button>
    </section>
    <script src="main.js"></script>
</body>
</html>