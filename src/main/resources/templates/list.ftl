<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserInfo Demo</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
    <script type="text/javascript"
            src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
</head>
<body>

<h2>User List</h2>
<br><br>

<form class="form-inline">
    <div class="form-group">
        <label for="sort">排序字段</label>
        <input type="text" class="form-control" id="sort" placeholder="id or loginname">
    </div>
    <div class="form-group">
        <label for="order">顺序(asc|desc)</label>
        <input type="text" class="form-control" id="order" placeholder="asc or desc">
    </div>
    <button type="submit" class="btn btn-default" id="search">search</button>
</form>

<table id="table" class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>LOGIN_NAME</th>
        <th>PASSWORD</th>
        <th>NAME</th>
    </tr>
    </thead>
</table>

</body>

<script>
    //
    var url = "/user/api/list";

    var table = $('#table').DataTable({
        "ajax": {
            "url": url,
            "dataSrc": ""
        },
        "columns": [
            {"data": "id"},
            {"data": "loginname"},
            {"data": "password"},
            {"data": "name"}
        ],
        // "ordering": false,
        "columnDefs": [{
            "targets": 2,
            "render": function (data, type, row, meta) {
                return '******';
            }
        }]
    });
    // });

    // $(document).ready(function () {
    //     $("#search").click(function () {
    //
    //         var sort = $("#sort").val();
    //         var order = $("#order").val();
    //         if (sort) {
    //             url = url + "?sort=" + sort;
    //         }
    //         if (order) {
    //             url = url + "&order=" + order;
    //         }
    //
    //         table.ajax.reload();
    //     });
    // });

</script>
</html>