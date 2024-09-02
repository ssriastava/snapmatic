function login(){
	var username = document.getElementById("lusername").value;
	var pwd = document.getElementById("lpassword").value;
	
	console.log(username);
	
	if(username!=null && pwd!=null){
		let xhr = new XMLHttpRequest();
		xhr.open("POST", config.serviceurl+"/login");
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
  			if (xhr.readyState === 4 && this.status == 200) {
  			var responseObj = JSON.parse(this.responseText);
  			console.log(responseObj);
  			console.log(responseObj.success);
  			console.log(responseObj.token);
  			window.localStorage.setItem("session-id", responseObj.token);
    		window.open(config.pageurl+"/Page.html","_self");
  		}};
  		const obj ={
			  "username":username,
			  "password":pwd
		  };
		console.log(JSON.stringify(obj));
		xhr.send(JSON.stringify(obj));
	}
}