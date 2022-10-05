<#--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 2022/10/2
  Time: 23:31
  To change this template use File | Settings | File Templates.
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>$Title$</title>
    <script>
        function logout() {
            fetch("/auth/logout", {method: "POST"}).then(resp => {
                return resp.json();
            }).then(data => {
                console.log(data);
            })
        }
    </script>
</head>
<body>
<h1>Hello index</h1>
<button onclick="logout()">LogOut</button>
<#--$END$-->
</body>
</html>