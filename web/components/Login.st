<html>
<head>
    <title>$title$</title>
</head>
<body>

<h1>Please enter your username and password</h1>

<form action="/login" method="POST">
    <label for="name">Username</label>
    <input name="username" id="username" type="text"></input>
    <br/>
    <label for="password">Password</label>
    <input name="password" id="password" type="password" maxlength="20"></input>
    <input type="submit" name="login"></input>
</form>
</body>
</html>
