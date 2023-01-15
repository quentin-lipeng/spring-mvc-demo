<!DOCTYPE HTML>
<html lang="utf-8">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script>
        function login() {
            fetch('/auth/login/',
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
                if (data.status === "ok") {
                    window.location = "/home";
                }

                console.log(data);
            })
        }
    </script>
</head>
<body>
<p>Login page</p>
<form>
    Username: <label for="username">
        <input id="username" name="username" type="text"/>
    </label>
    Password: <label>
        <input id="password" name="password" type="password"/>
    </label><br/><br/>
    <button type="button" onclick="login()">Login</button>
</form>
</body>
</html>