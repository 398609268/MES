function getAuth(id){
    var list=new Array();
    $.ajax({
        url:"/operation/ids?id="+id,
        cache:false,
        async:false,
        type:"get",
        success:function (res)
        {
            if(res.code==0){
                for(var i=0;i<res.data.length;i++){
                    list.push(res.data[i].toString());
                }

            }
        },
        error:function (res)
        {
            console.log("数据获取失败"+res);
        }
    })
    $(".layui-btn-container").show();
    $(".btn-auth-controller").each(function (){
        var perm_this=$(this);
        perm_this.hide();
        if(list.length>0){
            if(list.indexOf(perm_this.attr("id"))>=0) {
                perm_this.show();
            }
        }
    })
}

function getPosType(){
    $.ajax({
        url:"/pos/postype",
        cache:false,
        async:true,
        type:"get",
        dataType:"json",
        success:function (res)
        {
            return res.data;
        },
        error:function (res)
        {
            console.log("数据获取失败"+res);
            return null;
        }
    })
}
function getCheckInfo(){
    var result=new Array();
    $.ajax({
        url:"/station/dataType",
        cache:false,
        async:false,
        type:"get",
        dataType:"json",
        success:function (res)
        {

            $.each(res.data, function(index,data){
                if(data.id<1000) {
                    result.push({
                        value: data.id,
                        title: data.name
                    })
                }
        })
        },
        error:function (res)
        {
            console.log("数据获取失败"+res);
        }
    })
    return result;
}
function InitData(res,ignoreNode){
    var result=new Array();
    $.each(res, function(index,data) {
        if(ignoreNode!=null){
            if(!ignoreNode.includes(data.id)){
                result.push({
                    id: data.id,
                    name: data.name,
                    open: data.open,
                    parent: data.parent,
                    pid: data.pid,
                    children:data.children,
                })
            }
        }else {
            result.push({
                id: data.id,
                name: data.name,
                open: data.open,
                parent: data.parent,
                pid: data.pid,
                children:data.children,
            })
        }
    });

    return result;
}
function InitChildData(res,ignoreNode){
    var result=new Array();
    $.each(res, function(index,data) {
            if(ignoreNode==null || !ignoreNode.includes(data.id)){
                result.push({
                    id: data.id,
                    name: data.name,
                    open: data.open,
                    parent: data.parent,
                    pid: -1,
                })
            }

    });

    return result;
}
function InitStationNameTree(ignoreNode)
{
    var result=new Array();
    $.ajax({
        url:"/station/name",
        cache:false,
        async:false,
        type:"get",
        dataType:"json",
        success:function (res)
        {

            result=InitData(res.data,ignoreNode);
        },
        error:function (res)
        {
            console.log("数据获取失败"+res);
        }

    })
    return result;
}
function zTreeonClick(e, treeId, treeNode) {

    var treeObj = $.fn.zTree.getZTreeObj(e.data.treeId);
    if (treeNode.isParent) {//如果不是叶子结点，结束
        treeObj.expandNode(treeNode);
        return;
    } else {
            treeObj.checkNode(treeNode,!treeNode.checked,true,true);


    }

};
function InitDataTypeTree(ignoreNode)
{
    var result=new Array();
    $.ajax({
        url:"/station/dataType",
        cache:false,
        async:false,
        type:"get",
        dataType:"json",
        success:function (res)
        {
            result=InitData(res.data,ignoreNode);
        },
        error:function (res)
        {
            console.log("数据获取失败"+res);
        }
    })

    return result;
}
function InitCheckDataTree(id)
{
    var result=new Array();
    $.ajax({
        url:"/pos/checkInfo?postype="+id,
        cache:false,
        async:false,
        type:"get",
        dataType:"json",
        success:function (res)
        {
            result=InitData(res.data,null);
        },
        error:function (res)
        {
            console.log("数据获取失败"+res);
        }
    })

    return result;
}
function getTreeNode(name,pid){
    var treeObj = $.fn.zTree.getZTreeObj(name);
    nodes=treeObj.getCheckedNodes(true);
    var TreeNode=new Array();
    for(var i=0;i<nodes.length;i++){
        if(!nodes[i].parent) {
            TreeNode.push({
                id:nodes[i].id,
                name:nodes[i].name,
                open:false,
                parent:false,
                pid:pid
            })
        }
    }

    return TreeNode;
}

function SendAjax(url,data,method){
    var result;
    $.ajax({
        url:url,
        cache:false,
        async:false,
        data: JSON.stringify(data),
        contentType: 'application/json; charset=UTF-8',
        dataType: "json",
        type:method,
        success:function (res)
        {
            if(res.code==0)
                result=true;
            else result=false;

        },
        error:function (res)
        {
            result=false;
            console.log("数据获取失败"+res);
        }
    })
    return result;
}
function SendAjaxNoData(url,method){
    var result;
    $.ajax({
        url:url,
        cache:false,
        async:false,
        dataType: "json",
        type:method,
        success:function (res)
        {
            result=res;

        },
        error:function (res)
        {
            result=false;
            console.log("数据获取失败"+res);
        }
    })
    return result;
}