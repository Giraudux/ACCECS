var cptVar=0;

function printFormVariable() {
	return "<label> var : </label> <textarea rows=1 class=\"variableName\"> name </textarea>" +
	"<label> type : </label> <select class=\"variableType\"> <option> Integer </option> <option> Float </option> <option> Boolean </option>  </select> " +
	"<label> role : </label> <select class=\"variableRole\"> <option> input </option> <option> output </option> <option> control </option>  <option> internal </option> </select>" +
	"<label> Lower bound : </label> <textarea class=\"variableMin\">  </textarea>"+ 
	"<label> Upper bound : </label> <textarea class=\"variableMax\">  </textarea>" +
	"<label> Default value (optional) </label> <textarea  class=\"variableInit\"> </textarea>" + 
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
  xhr = new XMLHttpRequest();
  xhr.open("POST", "/src/main/java/controleur/dataServlet.java", true);
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.send(data);
}

function generateModelJSON(){
  var data = { variables : [], properties : ""};
  for(i = 0 ; i<document.getElementsByClassName("variableName").length ; i++) {
    var variable = {};
    variable.variableName =  JSON.stringify(document.getElementsByClassName("variableName")[i].value);
    variable.variableType =  JSON.stringify(document.getElementsByClassName("variableType")[i].value);
    variable.variableRole =  JSON.stringify(document.getElementsByClassName("variableRole")[i].value);
    variable.variableMin =  JSON.stringify(document.getElementsByClassName("variableMin")[i].value);
    variable.variableMax =  JSON.stringify(document.getElementsByClassName("variableMax")[i].value);
    variable.variableInit =  JSON.stringify(document.getElementsByClassName("variableInit")[i].value);

    data.variables.push(variable);
  }

  data.properties = JSON.stringify(document.getElementsByClassName("textAreaProperties")[0].value);
	/*
	console.log(data.variables[0]);
	console.log(data.variables[1]);
	console.log(data.properties);
	*/
	sendJSON(data);
}

