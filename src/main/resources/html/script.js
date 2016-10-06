var cptVar=0;

function printFormVariable() {
	return "<label> var : </label> <textarea rows=1 name=\"variableName" + cptVar + "\"> name </textarea>" +
	"<label> type : </label> <select name=\"variableType" + cptVar + "\"> <option> Integer </option> <option> Float </option> <option> Boolean </option>  </select> " +
	"<label> role : </label> <select name=\"variableRole" + cptVar + "\"> <option> input </option> <option> output </option> <option> control </option>  <option> internal </option> </select>" +
	"<label> Lower bound : </label> <textarea name=\"variableMin" + cptVar + "\">  </textarea>"+ 
	"<label> Upper bound : </label> <textarea name=\"variableMax" + cptVar + "\">  </textarea>" +
	"<label> Default value (optional) </label> <textarea  name=\"variableInit" + cptVar + "\"> </textarea>" +
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
