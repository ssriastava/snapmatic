function loadpage(){
	
	let xhr = new XMLHttpRequest();
		xhr.open("POST", config.serviceurl+"/refreshtoken");
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
  			if (xhr.readyState === 4) {
    		console.log("refreshed");
    		const obj=JSON.parse(xhr.responseText);
    		console.log(obj);
    		var title=document.title;
    		if(xhr.status==200 && obj.success){
    		    console.log(title.localeCompare("Sign In / Sign Up"))
				if(title.localeCompare("Sign In / Sign Up")==0){
					console.log("redirect1");
					window.open(config.pageurl+"/Page.html","_self");
				}
			}
			else{
				if(title.localeCompare("Sign In / Sign Up")!=0){
					console.log("redirect2");
					window.open(config.pageurl+"/home.html","_self");
				}
			}
  		}};
  		var token=window.localStorage.getItem("session-id");
  		const obj = {"token":token};
  		console.log(JSON.stringify(obj));
		xhr.send(JSON.stringify(obj));
}