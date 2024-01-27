function login(){
	var username = document.getElementById("lusername").value;
	var pwd = document.getElementById("lpassword").value;
	
	console.log(username);
	
	if(username!=null && pwd!=null){
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "http://localhost:8080/login");
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
  			if (xhr.readyState === 4) {
    		window.open("http://localhost:8080/Page.html","_self");
  		}};
  		const obj ={
			  "username":username,
			  "password":pwd
		  };
		console.log(JSON.stringify(obj));
		xhr.send(JSON.stringify(obj));
	}
}