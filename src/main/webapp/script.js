var cptVar=0;
function printFormVariable() {
	return "<label> var : </label> <textarea rows=1 class=\"variableName\">name</textarea>" +
	"<label> type : </label> <select id=\"selectType" + cptVar +"\" class=\"variableType\" onchange=\"checkType(" + cptVar  +")\"> <option> Integer </option> <option> Float </option> <option> Boolean </option>  </select> " +
	"<label> role : </label> <select class=\"variableRole\"> <option> input </option> <option> output </option> <option> control </option>  <option> internal </option> </select>" +
	"<label id=\"labelLowerBound" + cptVar +"\"> Lower bound : </label> <textarea id=\"lowerBound" + cptVar +"\" class=\"variableMin\"></textarea>"+ 
	"<label id=\"labelUpperBound" + cptVar +"\"> Upper bound : </label> <textarea id=\"upperBound" + cptVar +"\" class=\"variableMax\"></textarea>" +
	"<label> Default value (optional) </label> <textarea id=\"defaultValue" + cptVar +"\" class=\"variableInit\"></textarea>" + 
  "<input id=\"delete" + cptVar +"\" type=\"button\" value=\"DELETE\" onclick=\"removeVariable( " + cptVar  +")\">" +
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

function checkType(rankVar){
  var x = document.getElementById("selectType" + rankVar).value;
  if (x=="Boolean"){
    document.getElementById("lowerBound" + rankVar).style.visibility = "hidden";
    document.getElementById("upperBound" + rankVar).style.visibility = "hidden";
    document.getElementById("labelLowerBound" + rankVar).style.visibility = "hidden";
    document.getElementById("labelUpperBound" + rankVar).style.visibility = "hidden";
    document.getElementById("variable" + rankVar).removeChild(document.getElementById("defaultValue" + rankVar));
    document.getElementById("variable" + rankVar).removeChild(document.getElementById("delete" + rankVar));
    document.getElementById("variable" + rankVar).innerHTML+="<select id=\"defaultValue" + rankVar +"\"class=\"variableInit\"> <option> true </option> <option> false </option> </select>"+
      "<input id=\"delete" + rankVar +"\" type=\"button\" value=\"DELETE\" onclick=\"removeVariable( " + rankVar  +")\">";
    document.getElementById("selectType" + rankVar).value=x;
    
    } else{
      document.getElementById("lowerBound" + rankVar).style.visibility = "visible";
      document.getElementById("upperBound" + rankVar).style.visibility = "visible";
      document.getElementById("labelLowerBound" + rankVar).style.visibility = "visible";
      document.getElementById("labelUpperBound" + rankVar).style.visibility = "visible";
      document.getElementById("variable" + rankVar).removeChild(document.getElementById("defaultValue" + rankVar));
      document.getElementById("variable" + rankVar).removeChild(document.getElementById("delete" + rankVar));
      document.getElementById("variable" + rankVar).innerHTML+="<textarea id=\"defaultValue" + rankVar +"\" class=\"variableInit\"></textarea>"+
      "<input id=\"delete" + rankVar +"\" type=\"button\" value=\"DELETE\" onclick=\"removeVariable( " + rankVar  +")\">";
      document.getElementById("selectType" + rankVar).value=x;
  }
  
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



