function loadpage(){
	
	let xhr = new XMLHttpRequest();
		xhr.open("POST", "http://localhost:8080/refreshtoken");
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
  			if (xhr.readyState === 4) {
    		console.log("refreshed");
    		const obj=JSON.parse(xhr.responseText);
    		if(obj.success=false){
				window.open("http://localhost:8080/Home.html","_self");
			}
			else{
				var title=document.title;
				if(title.localeCompare("Sign In / Sign Up")==0){
					console.log("redirect");
					window.open("http://localhost:8080/Page.html","_self");
				}
			}
  		}};
		xhr.send();
}