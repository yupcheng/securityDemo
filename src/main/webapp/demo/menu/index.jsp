<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/static/common/taglib.jsp"%>
    <title>Title</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class ="row">
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单信息</h5>
                    <div class="ibox-tools">
                        <a class="btn btn-white btn-bitbucket" title="添加菜单">
                            <i class="fa fa-plus"></i>
                        </a>

                    </div>
                    <%--<div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="buttons.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="buttons.html#">选项1</a>
                            </li>
                            <li><a href="buttons.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>--%>
                </div>
                <div class="ibox-content">
                    <div id="treeview1" class="test"></div>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>编辑菜单</h5>

                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="menuForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">上级菜单</label>
                            <div class="col-sm-8">
                                <select data-placeholder="请选择上级菜单" class="chosen-select" style="width:100%">
                                    <option value="">无</option>
                                    <option value="110000" hassubinfo="true">北京</option>
                                    <option value="120000" hassubinfo="true">天津</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">菜单名称</label>
                            <div class="col-sm-8">
                                <input id="title" type="email" class="form-control" name="title" required="" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">菜单序号</label>
                            <div class="col-sm-8">
                                <input id="sort" type="number" class="form-control" name="sort">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">菜单URL</label>
                            <div class="col-sm-8">
                                <input id="url" type="url" class="form-control" name="url">
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary">保存</button>
        </div>
    </div>
    <%@ include file="/static/common/jslib.jsp"%>
    <script>

        $('#treeview1').treeview({
            data: ${jsonTree},
            onNodeSelected: function(event, data) {
               console.log(data);
                $('#menuForm').setForm(data);
            }
        });
        $(document).ready(function ()
        {
            var config = {
                '.chosen-select': {}
            }
            for (var selector in config) {
                $(selector).chosen(config[selector]);
            }
        })
    </script>

</body>
</html>
