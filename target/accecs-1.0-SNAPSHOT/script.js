var cptVar=0;

function printFormVariable() {
	return "<label> var : </label> <textarea rows=1 class=\"variableName\">name</textarea>" +
	"<label> type : </label> <select class=\"variableType\"> <option> Integer </option> <option> Float </option> <option> Boolean </option>  </select> " +
	"<label> role : </label> <select class=\"variableRole\"> <option> input </option> <option> output </option> <option> control </option>  <option> internal </option> </select>" +
	"<label> Lower bound : </label> <textarea class=\"variableMin\"></textarea>"+ 
	"<label> Upper bound : </label> <textarea class=\"variableMax\"></textarea>" +
	"<label> Default value (optional) </label> <textarea  class=\"variableInit\"></textarea>" + 
  "<input type=\"button\" value=\"DELETE\" onclick=\"removeVariable( " + cptVar  +")\">" +
	"<br/> ";

}

function newVariable(){
  var div = document.createElement('div');
  div.id = "variable" + cptVar;
  div.innerHTML = printFormVariable();
	document.getElementById("variables").appendChild(div);
	cptVar++; 
}

function removeVariable(rankVar){
	document.getElementById("variables").removeChild(document.getElementById("variable" + rankVar));
}

function sendJSON(data) {
  var url = "http://localhost:8080/accecs/dataServlet"
  xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
 // xhr.setRequestHeader("Content-type", "application/json");
  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhr.send("data="+data);
}

function generateModelJSON(){
  var data = { variables : [], properties : ""};
  for(i = 0 ; i<document.getElementsByClassName("variableName").length ; i++) {
    var variable = {};
    variable.variableName =  document.getElementsByClassName("variableName")[i].value;
    variable.variableType =  document.getElementsByClassName("variableType")[i].value;
    variable.variableRole =  document.getElementsByClassName("variableRole")[i].value;
    variable.variableMin =  document.getElementsByClassName("variableMin")[i].value;
    variable.variableMax =  document.getElementsByClassName("variableMax")[i].value;
    variable.variableInit =  document.getElementsByClassName("variableInit")[i].value;

    data.variables.push(variable);
  }

  data.properties = document.getElementsByClassName("textAreaProperties")[0].value;
	
	console.log(data);
	console.log(data.variables[0]);
	console.log(data.variables[1]);
	console.log(data.properties);
	
	sendJSON(JSON.stringify(data));
}


