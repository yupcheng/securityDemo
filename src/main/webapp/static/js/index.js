$(function(){
    //菜单点击
    $(".J_menuItem").on('click',function(){
        var url = $(this).attr('href');
        if(url == "/" || url == "")
        {
            window.location.reload();
            return false;
        }
        $("#J_iframe").attr('src',url);
        return false;
    });


});