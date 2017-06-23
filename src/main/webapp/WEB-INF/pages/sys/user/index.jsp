<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <%@ include file="/static/common/taglib.jsp"%>
</head>
<body>
<div class="col-sm-12">
    <!-- Example Events -->
    <div class="example-wrap">
        <h4 class="example-title">事件</h4>
        <div class="example">
            <div class="alert alert-success" id="userTableResult" role="alert">
                事件结果
            </div>
            <div class="btn-group hidden-xs" id="userTableToolBar" role="group">
                <button type="button" class="btn btn-outline btn-default">
                    <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                </button>
                <button type="button" class="btn btn-outline btn-default">
                    <i class="glyphicon glyphicon-heart" aria-hidden="true"></i>
                </button>
                <button type="button" class="btn btn-outline btn-default">
                    <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                </button>
            </div>
            <table id="userTable" data-url="${ctx}/sys/user/queryPage" data-height="400" data-mobile-responsive="true">
                <thead>
                <tr>
                    <th data-field="id" data-checkbox="true"></th>
                    <th data-formatter="indexCount">序号</th>
                    <th data-field="ssoId">登陆名</th>
                    <th data-field="email">邮箱</th>
                    <th data-field="active">状态</th>
                    <th data-formatter="operate">操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <!-- End Example Events -->
</div>
<%@ include file="/static/common/jslib.jsp" %>
<script>
    function indexCount(value,row,index)
    {
        return  (index+1) ;
    }

    function operate() {
        return ' <button type="button" title="编辑角色" class="btn btn-outline btn-default">'
            +'<i class="glyphicon glyphicon-user" aria-hidden="true"></i>'
            +'</button>';
    }

    $('#userTable').on('all.bs.table', function(e, name, args) {
        console.log('Event:', name, ', data:', args);
    })
            .on('click-row.bs.table', function(e, row, $element) {
            $result.text('Event: click-row.bs.table');
        })
            .on('dbl-click-row.bs.table', function(e, row, $element) {
            $result.text('Event: dbl-click-row.bs.table');
        })
            .on('sort.bs.table', function(e, name, order) {
            $result.text('Event: sort.bs.table');
        })
            .on('check.bs.table', function(e, row) {
            $result.text('Event: check.bs.table');
        })
            .on('uncheck.bs.table', function(e, row) {
            $result.text('Event: uncheck.bs.table');
        })
            .on('check-all.bs.table', function(e) {
            $result.text('Event: check-all.bs.table');
        })
            .on('uncheck-all.bs.table', function(e) {
            $result.text('Event: uncheck-all.bs.table');
        })
            .on('load-success.bs.table', function(e, data) {
            $result.text('查询成功');
        })
            .on('load-error.bs.table', function(e, status) {
            $result.text('Event: load-error.bs.table');
        })
            .on('column-switch.bs.table', function(e, field, checked) {
            $result.text('Event: column-switch.bs.table');
        })
            .on('page-change.bs.table', function(e, size, number) {
            $result.text('Event: page-change.bs.table');
        })
            .on('search.bs.table', function(e, text) {
            $result.text('Event: search.bs.table');
        });

    $('#userTable').bootstrapTable({
        search: true,
        showRefresh: true,
        showToggle: true,
        showColumns: true,
        toolbar: '#userTableToolBar',
        iconSize: 'outline',
        pagination:true,
        queryParamsType:'',
        sidePagination:'server',
        pageNumber:1,
        search:true,
        pageSize:3,
        icons: {
            refresh: 'glyphicon-repeat',
            toggle: 'glyphicon-list-alt',
            columns: 'glyphicon-list'
        }
    });

    var $result = $('#userTableResult');
</script>
</body>
<head>
    <title>Title</title>
    <%@ include file="/static/common/taglib.jsp"%>
</head>
</html>
