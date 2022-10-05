<#--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 2022/10/4
  Time: 19:14
  To change this template use File | Settings | File Templates.
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>$Title$</title>
    <script>
        function login() {
            fetch('/auth/register/',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        username: document.getElementById('username').value,
                        password: document.getElementById('password').value
                    })
                }).then(resp => {
                return resp.json();
            }).then(data => {
                console.log(data);
            })
        }
    </script>
</head>
<body>

<h1>register page</h1>
<form>
    Username: <label for="username">
        <input id="username" name="username" type="text"/>
    </label>
    Password: <label>
        <input id="password" name="password" type="password"/>
    </label><br/><br/>
    <button type="button" onclick="register()">register</button>
</form>
<#--$END$-->
</body>
</html>