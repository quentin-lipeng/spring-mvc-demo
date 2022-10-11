<#--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 2022/10/10
  Time: 21:00
  To change this template use File | Settings | File Templates.
-->
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>resource</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script>
        window.onload = () => {
            fetch('/resource/list',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).then(resp => {
                return resp.json();
            }).then(json => {
                let data = json.data;
                let table = document.getElementById("resource-table")
                let resourcesLen = data.length;
                for (let i = 0; i < resourcesLen; i++) {
                    let tr = document.createElement('tr');
                    table.appendChild(tr);

                    let tdName = document.createElement('td');
                    tdName.innerHTML = data[i]['resourceName'];
                    tr.appendChild(tdName);
                    let tdInfo = document.createElement('td');
                    tdInfo.innerHTML = data[i]['resourceInfo'];
                    tr.appendChild(tdInfo);
                }
                console.log(json);
            })
        }
    </script>
</head>
<body>
<h2>Resource</h2>
<table id="resource-table">
    <tbody>
    <tr>
        <th>资源路径</th>
        <th>资源信息</th>
    </tr>
    </tbody>

</table>
</body>
</html>