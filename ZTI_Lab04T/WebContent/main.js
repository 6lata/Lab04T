class UI {
	static addRecordToList(record) {
		const folksList = document.getElementById("folks-list");
		const newRecord = document.createElement("li");
		
		const removeButton = document.createElement("button");
		removeButton.innerHTML = "X";
		removeButton.className = "remove-button";
		
		newRecord.innerHTML = record;
		newRecord.appendChild(removeButton);
		folksList.appendChild(newRecord);
		
	}
	
	static deleteRecord(record) {
		record.parentElement.remove();
	}
}

let response = 0;
const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
	if (xhr.readyState == 4) {
		if (xhr.status == 200) {
			
			response = JSON.parse(xhr.responseText);
			response.forEach(record => {
				UI.addRecordToList(JSON.stringify(record))
;			});
			const remover = document.querySelectorAll(".remove-button");
			remover.forEach((rem) => {rem.addEventListener("click", () => {UI.deleteRecord(rem); 
				const xhr2 = new XMLHttpRequest();
				let idToRemove = JSON.parse(rem.parentElement.innerHTML.substring(0, rem.parentElement.innerHTML.length - 40)).id;
				console.log(idToRemove);
				xhr2.open('DELETE', `http://localhost:8081/ZTI_Lab04T/rest/jpa/person/${idToRemove}`, true);
				xhr2.send();
			})});
		} else if (xhr.status == 404) {
			console.log("Resource not found");
		}
	}
}

xhr.open('get', 'http://localhost:8081/ZTI_Lab04T/rest/jpa/person', true);
xhr.send();

const submitBtn = document.getElementById("submit-btn");
submitBtn.addEventListener('click', () => {
	const fname = document.getElementById("fname");
	const lname = document.getElementById("lname");
	const email = document.getElementById("email");
	const city = document.getElementById("city");
	const tel = document.getElementById("tel");
	
	
	if(fname.value === "" || lname.value === "" || email.value === "" || city.value === "" || tel.value === "") {
		alert("Uzupelnij puste pola. ");
	} else {
		let newRec = {
				fname: fname.value, 
				lname: lname.value,
				email: email.value,
				city:  city.value,
				tel: tel.value
		}
		let newRecJSON = JSON.stringify(newRec);
		const xhr3 = new XMLHttpRequest();
		xhr3.open('POST', "http://localhost:8081/ZTI_Lab04T/rest/jdbc/person", true);
		xhr3.setRequestHeader('Content-type', 'application/json');
		console.log(typeof (tel.value));
		xhr3.onload = function() {
			console.log(xhr3.response);
		}
		xhr3.send(newRecJSON);
	}
})






