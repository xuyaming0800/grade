function toLogin(path){
	var p=window.parent;
	if(p!=null&&p!=undefined){
		p.location.href=path+"/login";
	}else{
		location.href=path+"/login";
	}
}