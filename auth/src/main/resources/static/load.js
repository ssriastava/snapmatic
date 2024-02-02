function loadpage(){
	
	let xhr = new XMLHttpRequest();
		xhr.open("POST", config.url+"/refreshtoken");
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
  			if (xhr.readyState === 4) {
    		console.log("refreshed");
    		const obj=JSON.parse(xhr.responseText);
    		var title=document.title;
    		if(obj.success==false){
				if(title.localeCompare("Sign In / Sign Up")!=0){
					console.log("redirect");
					window.open(config.url+"/home.html","_self");
				}
			}
			else{
				if(title.localeCompare("Sign In / Sign Up")==0){
					console.log("redirect");
					window.open(config.url+"/Page.html","_self");
				}
			}
  		}};
		xhr.send();
}