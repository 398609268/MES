function MakeDataToTreeNodes(res){
    var TreeNode=new Array();
    var childList;
    $.each(res,function (index,data){
        childList=new Array();
        $.each(data.settings,function (index,child){
            if(child.checkInfo!=null){
                childList.push({
                    id:child.id,
                    name:child.checkInfo,
                    open:true,
                    parent:false,
                    pid:data.id
                })
            }
        })
        TreeNode.push({
            id:data.id,
            name:data.stationName,
            open:true,
            parent:true,
            pid:0,
            children:childList
        })
    })
    return TreeNode;

}