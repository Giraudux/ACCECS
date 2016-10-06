var cptVar=0;

function printFormVariable() {
	return "<label> var : </label> <textarea rows=1 class=\"variableName\"> name </textarea>" +
	"<label> type : </label> <select class=\"variableType\"> <option> Integer </option> <option> Float </option> <option> Boolean </option>  </select> " +
	"<label> role : </label> <select class=\"variableRole\"> <option> input </option> <option> output </option> <option> control </option>  <option> internal </option> </select>" +
	"<label> Lower bound : </label> <textarea class=\"variableMin\">  </textarea>"+ 
	"<label> Upper bound : </label> <textarea class=\"variableMax\">  </textarea>" +
	"<label> Default value (optional) </label> <textarea  class=\"variableInit\"> </textarea>" +
	"<br/> ";

}

function newVariable(){
  var div = document.createElement('div');
  div.id = "variable" + cptVar;
  div.innerHTML = printFormVariable();
	document.getElementById("newvariables").appendChild(div);
	cptVar++; 
}

function removeVariable(){
	if(cptVar>1) {
		cptVar--;
		document.getElementById("newvariables").removeChild(document.getElementById("variable" + cptVar));
	}
}

function generateModelJSON(){
  var obj = { variables : []};
  for(i = 0 ; i<document.getElementsByClassName("variableName").length ; i++) {
    var variable = {};
    variable.variableName =  JSON.stringify(document.getElementsByClassName("variableName")[i].value);
    variable.variableType =  JSON.stringify(document.getElementsByClassName("variableType")[i].value);
    variable.variableRole =  JSON.stringify(document.getElementsByClassName("variableRole")[i].value);
    variable.variableMin =  JSON.stringify(document.getElementsByClassName("variableMin")[i].value);
    variable.variableMax =  JSON.stringify(document.getElementsByClassName("variableMax")[i].value);
    variable.variableInit =  JSON.stringify(document.getElementsByClassName("variableInit")[i].value);

    obj.variables.push(variable);
  }

		console.log(obj.variables[0]);
}
	

