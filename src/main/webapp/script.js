
/***
 * Methode permettant de changer d'onglet et d'afficher le contenu voulu
 * */
function changeScreen(numberClicked, nbPages){
	
	for (i = 1; i <= nbPages; i++) {
		if(numberClicked != i){
			var otherOnglet = document.getElementById("onglet"+i);
			var otherContent = document.getElementById("content"+i);
			otherOnglet.className = "btn btn-secondary onglet";
			otherContent.className = "contentInactif";
		}
	}
	
	document.getElementById("onglet"+numberClicked).className = "btn btn-primary onglet";
	document.getElementById("content"+numberClicked).className = "contentActif";
}

/**
 * 
 */
 function nextStep(numberNextStep){
	 changeScreen(numberNextStep, 3); 
	 
	 
	 //Ecriture des events
	 if(numberNextStep == 2){ 
		var variables = document.getElementsByClassName("Variable form-inline"); 
		var objJson = JSON.parse(machineToJSON());
		 console.log(objJson.variables[0].name);
		 
		for (var vr in objJson.variables) {
			if(objJson.variables[vr].category == 'input' || objJson.variables[vr].category == 'output'){
				var eventsBtn =  document.getElementById("addEventsBtn");
				eventsBtn.parentNode.insertBefore(newElementEvent(), eventsBtn);
				fillEventForm(objJson, vr, i);
				i++;
			}
		}
	 }
	 
	 //generation de la machine
	 if(numberNextStep == 3){
		generateMachine();
	}
}
 
 
 



/**
 *
 */
function newElement(name, attributes, childs) {
    var element;
    var attribute;
    var i;

    element = document.createElement(name);

    for (attribute in attributes) {
        if (attributes.hasOwnProperty(attribute)) {
            element.setAttribute(attribute, attributes[attribute]);
        }
    }

    for (i = 0; i < childs.length; i++) {
        element.appendChild(childs[i]);
    }

    return element;
}


/**
 *
 */
function newElementEvent() {
    return newElement("FIELDSET", {
        class: "Event form-inline",
        required: true
    }, [
        newElement("BUTTON", {
            type: "button",
            class: "col-sm-1 btn btn-danger",
            onclick: "this.parentNode.parentNode.removeChild(this.parentNode)"
        }, [document.createTextNode("Remove")]),
        newElement("INPUT", {
            type: "text",
            required: true,
            placeholder: "Event name",
            class: "EventName col-md-2"
        }, []),
        newElement("INPUT", {
            type: "text",
            required: true,
            placeholder: "Event expression",
            class: "EventExpression col-md-7"
        }, []),
        newElement("SELECT", {
            class: "EventType col-md-2"
        }, [
            newElement("OPTION", {
                value: "sensing"
            }, [document.createTextNode("Sensing")]),
            newElement("OPTION", {
                value: "monitoring"
            }, [document.createTextNode("Monitoring")]),
            newElement("OPTION", {
                value: "control"
            }, [document.createTextNode("Control")]),
            newElement("OPTION", {
                value: "reaction"
            }, [document.createTextNode("Reaction")])
        ])
    ]);
}


/**
 *
 */
function newElementProperty() {
    return newElement("FIELDSET", {
        class: "Property form-inline",
        required: true
    }, [
        newElement("BUTTON", {
            type: "button",
            class: "col-sm-1 btn btn-danger",
            onclick: "this.parentNode.parentNode.removeChild(this.parentNode)"
        }, [document.createTextNode("Remove")]),
        newElement("INPUT", {
            type: "text",
            required: true,
            placeholder: "Property expression",
            class: "PropertyExpression col-md-9"
        }, []),
        newElement("SELECT", {
            class: "PropertyCategory col-md-2"
        }, [
            newElement("OPTION", {
                value: "safety"
            }, [document.createTextNode("Safety")]),
            newElement("OPTION", {
                value: "non-functional"
            }, [document.createTextNode("Non-functional")]),
            newElement("OPTION", {
                value: "liveness"
            }, [document.createTextNode("Liveness")])
        ])
    ]);
}

/**
 *
 */
function newElementVariable() {
    var variable;
    var element;
    var option;

    variable = newElement("FIELDSET", {
        class: "Variable form-inline"
    }, []);

    variable.appendChild(newElement("BUTTON", {
        type: "button",
        class: "col-sm-1 btn btn-danger",
        onclick: "this.parentNode.parentNode.removeChild(this.parentNode)"
    }, [document.createTextNode("Remove")]));

    variable.appendChild(newElement("INPUT", {
        type: "text",
        required: true,
        class: "VariableName  col-sm-3",
        placeholder: "Variable name"
    }, []));

    variable.appendChild(newElement("SELECT", {
        class: "VariableType  col-sm-1",
        onchange: "updateVariable(this.parentNode)"
    }, [
        newElement("OPTION", {
            value: "integer"
        }, [document.createTextNode("Integer")]),
        newElement("OPTION", {
            value: "natural"
        }, [document.createTextNode("Natural")]),
        newElement("OPTION", {
            value: "boolean"
        }, [document.createTextNode("Boolean")])
    ]));

    variable.appendChild(newElement("SELECT", {
        class: "VariableCategory col-sm-1",
        onchange: "updateCategory(this.parentNode)"
    }, [
        newElement("OPTION", {
        	value: ""
        }, [document.createTextNode("")]),
        newElement("OPTION", {
            value: "input"
        }, [document.createTextNode("Input")]),
        newElement("OPTION", {
            value: "output"
        }, [document.createTextNode("Output")]),
        newElement("OPTION", {
            value: "control"
        }, [document.createTextNode("Control")]),
        newElement("OPTION", {
            value: "internal"
        }, [document.createTextNode("Internal")])
    ]));

    variable.appendChild(newElement("INPUT", {
        type: "number",
        required: true,
        class: "VariableLowerBound col-sm-2"
    }, []));

    variable.appendChild(newElement("INPUT", {
        type: "number",
        required: true,
        class: "VariableUpperBound col-sm-2"
    }, []));

    variable.appendChild(newElement("INPUT", {
        type: "text",
        class: "VariableDefaultValue col-sm-2"
    }, []));
    
    updateVariable(variable);

    return variable;
}

/**
 *
 */
function updateVariable(variable) {
    var type;
    var disableBounds;

    type = variable.getElementsByClassName("VariableType")[0].value;
    variable.removeChild(variable.getElementsByClassName("VariableDefaultValue")[0]);

    if (type == "boolean") {
        disableBounds = true;
        variable.appendChild(newElement("SELECT", {
            class: "VariableDefaultValue"
        }, [
            newElement("OPTION", {
                value: "true"
            }, [document.createTextNode("True")]),
            newElement("OPTION", {
                value: "false"
            }, [document.createTextNode("False")])
        ]));
    } else {
        disableBounds = false;
        if (type == "integer") {
            ;
        } else if (type == "natural") {
            ;
        }
        variable.appendChild(newElement("INPUT", {
            type: "number",
            class: "VariableDefaultValue"
        }, []));
    }

    variable.getElementsByClassName("VariableLowerBound")[0].disabled = disableBounds;
    variable.getElementsByClassName("VariableUpperBound")[0].disabled = disableBounds;
}


function updateCategory(variable){
	var category = variable.getElementsByClassName("VariableCategory")[0].value;
	var container = document.getElementById("category"+category);
	container.appendChild(variable);
}

/**
 *
 */
function generateMachine() {
    var xhttp;

    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) { 
        
        	var element = document.getElementById("machine-code");
        
            while (element.firstChild) {
    			element.removeChild(element.firstChild);
			}
        
           element.appendChild(newElement("CODE",{
           }, [document.createTextNode(xhttp.response)]));       
        }
    };
    xhttp.open("POST", "/dataServlet", true);
    xhttp.responseType = "text";
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("data=" + encodeURIComponent(machineToJSON()));
}

/**
 * 
 * @returns
 */
function saveJson() {
    console.log(machineToJSON());
    document.getElementById("dataToSave").value = machineToJSON(); 
    document.getElementById("machineName").value = document.getElementsByClassName("MachineName")[0].value; 
    document.getElementById("saveForm").submit();
}

function loadJson(files) {
	  if(files.length>0) {
		cleanForm();
	    var file = files[0];
	    var reader = new FileReader();
	    reader.onload = function(e) {
	    	  var text = reader.result;
	    	  console.log(text);
	    	  var objJson = JSON.parse(text);
	    	  var i=0;
	    	  
	    	  for(var vr in objJson.variables){
	    		  var variablesBtn =  document.getElementById("addVariableBtn");
	    		  variablesBtn.parentNode.insertBefore(newElementVariable(), variablesBtn);
	    		  fillVariableForm(objJson, vr, i);
	    	      i++;
	    	  }
	    	  var j = 0;
	    	  for(var key in objJson.properties){
	    		  var propertiesBtn =  document.getElementById("addPropertiesBtn");
	    		  propertiesBtn.parentNode.insertBefore(newElementProperty(), propertiesBtn);
	    		  fillPropertyForm(objJson, key, j)
	    		  j++;
	    	  }
	    	  var k = 0;
	    	  for(var key in objJson.events){
	    		  var eventsBtn =  document.getElementById("addEventsBtn");
	    		  eventsBtn.parentNode.insertBefore(newElementEvent(), eventsBtn);
	    		  fillEventForm(objJson, key, k)
	    		  k++;
	    	  }
	    	  document.getElementsByClassName("MachineName")[0].value = objJson.name;
	    }
	    reader.readAsText(file);
	  }
}

function fillVariableForm(objJson, key, index){
			var variables = document.getElementsByClassName("Variable");
			var variable = variables[index];
	        variable.getElementsByClassName("VariableName")[0].value = objJson.variables[key].name;
	        variable.getElementsByClassName("VariableType")[0].value = objJson.variables[key].type;
	        if(objJson.variables[key].type=='boolean'){
				variable.getElementsByClassName("VariableLowerBound")[0].disabled = true;
				variable.getElementsByClassName("VariableUpperBound")[0].disabled = true;
				}
				 else{
				variable.getElementsByClassName("VariableLowerBound")[0].value = objJson.variables[key].lowerBound;
				variable.getElementsByClassName("VariableUpperBound")[0].value = objJson.variables[key].upperBound;
				}
	        variable.getElementsByClassName("VariableCategory")[0].value = objJson.variables[key].category;
	        variable.getElementsByClassName("VariableDefaultValue")[0].value = objJson.variables[key].defaultValue;
			}


function fillPropertyForm(objJson, key, index){
	var properties = document.getElementsByClassName("Property");
	var property = properties[index];
	property.getElementsByClassName("PropertyExpression")[0].value = objJson.properties[key].expression;
	property.getElementsByClassName("PropertyCategory")[0].value = objJson.properties[key].category;
}


function fillEventForm(objJson, key, index){
	var events = document.getElementsByClassName("Event");
	var event = events[index];
	
	if(objJson.variables[key].category =='input'){
		document.getElementsByClassName("EventName")[key].value = "sense_"+objJson.variables[key].name;
		document.getElementsByClassName("EventType")[key].value = "sensing";
	}
	else{
		document.getElementsByClassName("EventName")[key].value = "reaction_"+objJson.variables[key].name;
		document.getElementsByClassName("EventType")[key].value = "reaction";
	}
	
	if(objJson.variables[key].type=='boolean'){
		document.getElementsByClassName("EventExpression")[key].value = "BOOL";
	}
	else{
		document.getElementsByClassName("EventExpression")[key].value = objJson.variables[key].name + " : " + objJson.variables[key].lowerBound+".."+objJson.variables[key].upperBound;
	}
	
	
}

function cleanForm(){
	var variables = document.getElementsByClassName("Variable");
	var initialSize = variables.length;
    for (i = 0; i < initialSize; i++) {
    	variables[0].parentNode.removeChild(variables[0]);
    }
    
    var properties = document.getElementsByClassName("Property");
	var initialSizePr = properties.length;
    for (i = 0; i < initialSizePr; i++) {
    	properties[0].parentNode.removeChild(properties[0]);
    }
    
    var events = document.getElementsByClassName("Event");
	var initialSizeEv = events.length;
    for (i = 0; i < initialSizeEv; i++) {
    	events[0].parentNode.removeChild(events[0]);
    }
    
    var element = document.getElementById("machine-code");    
    while (element.firstChild) {
		element.removeChild(element.firstChild);
	}
}

/**
 *
 */
function machineToJSON() {
    var machine;
    var variables;
    var variable;
    var properties;
    var property;
    var i;

    machine = {
        variables: [],
        properties: [],
        events: []
    };

    machine.name = document.getElementsByClassName("MachineName")[0].value;

    variables = document.getElementsByClassName("Variable");
    for (i = 0; i < variables.length; i++) {
        variable = {};
        variable.name = variables[i].getElementsByClassName("VariableName")[0].value;
        variable.type = variables[i].getElementsByClassName("VariableType")[0].value;
        variable.category = variables[i].getElementsByClassName("VariableCategory")[0].value;
        variable.lowerBound = variables[i].getElementsByClassName("VariableLowerBound")[0].value;
        variable.upperBound = variables[i].getElementsByClassName("VariableUpperBound")[0].value;
        variable.defaultValue = variables[i].getElementsByClassName("VariableDefaultValue")[0].value;
        machine.variables.push(variable);
    }

    properties = document.getElementsByClassName("Property");
    for (i = 0; i < properties.length; i++) {
        property = {};
        property.expression = properties[i].getElementsByClassName("PropertyExpression")[0].value;
        property.category = properties[i].getElementsByClassName("PropertyCategory")[0].value;
        machine.properties.push(property);
    }
    
    events = document.getElementsByClassName("Event");
    for (i = 0; i < events.length; i++) {
        event = {};
        event.name = events[i].getElementsByClassName("EventName")[0].value;
        event.expression = events[i].getElementsByClassName("EventExpression")[0].value;
        event.type = events[i].getElementsByClassName("EventType")[0].value;
        machine.events.push(event);
    }

    console.log(machine);

    return JSON.stringify(machine);
}
function selectMachine(){
        if (document.selection) {
            var range = document.body.createTextRange();
            range.moveToElementText(document.getElementById("machine-code"));
            range.select();
        } else if (window.getSelection) {
            var range = document.createRange();
            range.selectNode(document.getElementById("machine-code"));
            window.getSelection().addRange(range);
        }
}
