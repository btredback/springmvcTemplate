//easyui标签内iframe[url:跳转连接/action]
function createFrame(url){
	var content;
	if (url){
		//工程内跳转
        content = '<iframe name="_link" scrolling="auto" frameborder="0"  src="'+url+'" style="border:0;width:100%;height:100%;"></iframe>'; 		
    } else {  
        content = '未实现';  
    }  
	return content;
}