function signup(){
	var username = document.getElementById("username").value;
	var email = document.getElementById("email").value;
	var pwd = document.getElementById("password").value;
	var cpwd = document.getElementById("cpassword").value;
	
	if(pwd==cpwd){
		let xhr = new XMLHttpRequest();
		xhr.open("POST", config.serviceurl+"/signup");
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
  			if (xhr.readyState === 4) {
    		console.log(xhr.status);
    		console.log(xhr.responseText);
  		}};
  		const obj ={
			  "username":username,
			  "password":pwd,
			  "email":email
		  };
		 console.log(JSON.stringify(obj));
		 xhr.send(JSON.stringify(obj));
		 document.getElementById("message").innerHTML = "<p style='color:green'>Account created successfully go to login page</p>";

	} else {
		document.getElementById("message").innerHTML = "<p style='color:red'>Passwords donot match</p>";
		
	}

}


function login(){
	
}