
/**
 * Methode permettant de changer d'onglet et d'afficher le contenu voulu.
 * Prend en paramètre le numéro de l'onglet souhaité.
 */
function changeScreen(numberClicked){
	var nbPages = document.getElementsByClassName("onglet").length;
	
	// On cache le contenu non voulu.
	for (i = 1; i <= nbPages; i++) {
		if(numberClicked != i){
			document.getElementById("onglet"+i).className = "btn btn-secondary onglet";
			document.getElementById("content"+i).className = "contentInactif";
		}
	}
	
	//On affiche l'onglet actif et son contenu.
	document.getElementById("onglet"+numberClicked).className = "btn btn-primary onglet";
	document.getElementById("content"+numberClicked).className = "contentActif";
}

/**
 * Fonction permettant de passer à l'étape suivante de la création de la machine.
 * Affiche la bonne vue en générant le nécessaire.
 */
function nextStep(numberNextStep){
	// On change l'affichage.
	changeScreen(numberNextStep); 
	
	// Création des variables et des invariants
	if(numberNextStep == 2){ 
		/*addTypes(addEnumeration());*/
		
	}
	
	// Génération de la machine
	if(numberNextStep == 3){

	 
	 
		//Ecriture des events
		var variables = document.getElementsByClassName("Variable form-inline"); 
		var objJson = JSON.parse(machineToJSON());
		 
		for (var vr in objJson.variables) {
			if(objJson.variables[vr].category == 'input' || objJson.variables[vr].category == 'output'){
				var eventsBtn =  document.getElementById("addEventsBtn");
				eventsBtn.parentNode.insertBefore(newElementEvent(), eventsBtn);
				fillEventForm(objJson, vr, i);
				i++;
			}
		}
	 
		generateMachine();
	}
}


/**
 * Crée une liste de types pour les variables.
 */
function addEnumeration(){
	var enumerations = ["integer", "natural", "boolean"];
	var enums = document.getElementsByClassName("EnumerationName");
	var literal = document.getElementsByClassName("LiteralName");
	
	// On crée les enumeration a utiliser
	for(i=0; i<enums.length; i++){
		enumerations[i+3] = enums[i].value.toLowerCase();
	}

return enumerations;
}
 
 
/**
 * Ajoute le choix du type pour les variables.
 */
function addTypes(enumerations){
	var types = document.getElementsByClassName("VariableType");
	
	//On supprimer les options précédentes
	for(i=0; i<types.length; i++){
		while (types[0].firstChild) {
  		types[0].removeChild(types[0].firstChild);
		}
	}
	
	//On ajoute les types dans la selection pour les variables
	for(i=0; i<types.length; i++){
		for(j=0; j<enumerations.length; j++){
			
			var opt = newElement("OPTION", {
				value: enumerations[j]},
				[document.createTextNode(enumerations[j].charAt(0).toUpperCase() + enumerations[j].substring(1))])
			types[i].add(opt, j);
		}
	}
}
 
/**
 * Crée un nouvel élément dans le DOM. Cet élément est un objet HTML.
 */
function newElement(name, attributes, childs) {
  var attribute;
  var i;
	var element = document.createElement(name);

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
 * Crée un élément de type évènement.
 * Un évènement a un nom, une garde et un type.
 * Fonction jamais implémentée car on ne modifie pas les évènements dans cet outils mais après dans atelierB.
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
 * Crée un élément de type propriété.
 * Une propriété a une expression et une catégorie.
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

function newEnumerationOptions() {
    var options = [];

    enumerations = document.getElementsByClassName("Enumeration");
    for (i = 0; i < enumerations.length; i++) {
        enumerationName = enumerations[i].getElementsByClassName("EnumerationName")[0].value;
        if(enumerationName !== "") { //TODO: replace by regex
            options.push(newElement("OPTION", {
                                     value: enumerationName
                                 }, [document.createTextNode(enumerationName)]));
        }
    }

    return options;
}

/**
 * Crée un élément de type variable.
 * Une variable a une nom, un type, une categorie, une borne inférieure, une borne supérieure et une valeure par défaut.
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
    }, [/* Création dynamique avec les enumerations */
        newElement("OPTION", {
            value: "integer"
        }, [document.createTextNode("Integer")]),
        newElement("OPTION", {
            value: "natural"
        }, [document.createTextNode("Natural")]),
        newElement("OPTION", {
            value: "boolean"
        }, [document.createTextNode("Boolean")])
    ].concat(newEnumerationOptions())));

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
 * Crée un élément de type enumeration.
 * Une numeration n'a qu'un champ qui sert à enrichir le choix de type des variables.
 */
function newElementEnumeration() {
	return newElement("FIELDSET", {
  	class: "Enumeration  form-inline",
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
      placeholder: "Type of variable",
      class: "EnumerationName col-md-11"
		}, []),
    newElement("DIV",
    {},
    [
        newElement("BUTTON",
            {type: "button", class: "addLiteralBtn", onclick: "this.parentNode.appendChild(newElementLiteral())"},
            [document.createTextNode("Add Literal")]
        )
    ])
	]);
}

function newElementLiteral() {
	return newElement("DIV", {}, [
  	newElement("BUTTON", {
    	type: "button",
      //class: "col-sm-1 btn btn-danger",
      onclick: "this.parentNode.parentNode.removeChild(this.parentNode)"
		}, [document.createTextNode("Remove")]),
    newElement("INPUT", {
    	type: "text",
      required: true,
      //placeholder: "Type the enumeration",
      class: "LiteralName"
		}, [])
	]);
}

function getEnumerations() {
    var enumerationsValues = [];

    enumerations = document.getElementsByClassName("Enumeration");
    for (i = 0; i < enumerations.length; i++) {
        enumerationName = enumerations[i].getElementsByClassName("EnumerationName")[0].value;
        if(enumerationName !== "") { //TODO: replace by regex
            enumerationsValues.push(enumerationName);
        }
    }

    return enumerationsValues;
}

function getLiterals(enumeration) {
    var literalsValues = [];

    enumerations = document.getElementsByClassName("Enumeration");
    for (var i = 0; i < enumerations.length; i++) {
        var enumerationName = enumerations[i].getElementsByClassName("EnumerationName")[0].value;
        if(enumerationName === enumeration) {
            var literals = enumerations[i].getElementsByClassName("LiteralName");
            for (var j = 0; j < literals.length; j++) {
                if(literals[j].value !== "") { //TODO: replace by regex
                    literalsValues.push(literals[j].value);
                }
            }
        }
    }

    return literalsValues;
}

/**
 * actualise la variable que l'on modifie selon son type.
 * modifie son choix de valeur par défaut et ses bornes selon son type
 */
function updateVariable(variable) {
    var type;
    var disableBounds;
		
		
		/*type = variable.getElementsByClassName("VariableType")[0];
		var enumerations = addEnumeration();
		for(j=0; j<enumerations.length; j++){
			var opt = newElement("OPTION", {
				value: enumerations[j]},
				[document.createTextNode(enumerations[j].charAt(0).toUpperCase() + enumerations[j].substring(1))])
			type.add(opt, j);
		}*/
		
		

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
    } else if(type == "integer" || type == "natural"){
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
    } else if(getEnumerations().indexOf(type) >= 0) {
        disableBounds = true;
        var literalsOptions = [];
        var literals = getLiterals(type);
        for(var i = 0; i < literals.length; i++) {
            literalsOptions.push(newElement("OPTION", {
                                                 value: literals[i]
                                             }, [document.createTextNode(literals[i])]));
        }

        variable.appendChild(newElement("SELECT", {
            class: "VariableDefaultValue"
        }, [
            newElement("OPTION", {
                value: ""
            }, [document.createTextNode("")])
        ].concat(literalsOptions)));
    }
		else{
			disableBounds = true;
			variable.appendChild(newElement("INPUT", {
            type: "number",
            class: "VariableDefaultValue"
        }, []));
		}

    variable.getElementsByClassName("VariableLowerBound")[0].disabled = disableBounds;
    variable.getElementsByClassName("VariableUpperBound")[0].disabled = disableBounds;
		
		

}

/**
 * Place la variable dans le bon container selon sa categorie.
 * Appelé lorsque l'on change la categorie d'une variable, la place dans une div correspondant à sa categorie.
 */
function updateCategory(variable){
	var category = variable.getElementsByClassName("VariableCategory")[0].value;
	var container = document.getElementById("category"+category);
	container.appendChild(variable);
}

/**
 * Envoie une requete JSON vers le serveur pour créer la machine B.
 */
function generateMachine() {
	var xhttp = new XMLHttpRequest();
	
  xhttp.onreadystatechange = function() {
  	if (xhttp.readyState == 4 && xhttp.status == 200) {
    	var element = document.getElementById("machine-code");
      while (element.firstChild) {
    		element.removeChild(element.firstChild);
			} 
      element.appendChild(newElement("CODE",{}, [document.createTextNode(xhttp.response)]));       
    }
	};
	
  xhttp.open("POST", "/dataServlet", true);
  xhttp.responseType = "text";
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send("data=" + encodeURIComponent(machineToJSON()));
}

/**
 * Sauvegarde les champs HTML et leurs valeurs dans un fichier JSON.
 */
function saveJson() {
    console.log(machineToJSON());
    document.getElementById("dataToSave").value = machineToJSON(); 
    document.getElementById("machineName").value = document.getElementsByClassName("MachineName")[0].value; 
    document.getElementById("saveForm").submit();
}

/**
 * Permet de charger une machine sous format JSON.
 * Crée les champs et les initialise avec la machine précédemment sauvegardée.
 */
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
	    	  
	    	  var l = 0;
	    	  for(var key in objJson.enumerations){
	    		  var enumBtn =  document.getElementById("addEnumerationBtn");
	    		  enumBtn.parentNode.insertBefore(newElementEnumeration(), enumBtn);
	    		  fillEnumForm(objJson, key, l)
	    		  l++;
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

/**
 * Charger les types d'énumeration à partir du fichier Json
 * @param objJson
 * @param key
 * @param index
 * @returns
 */
function fillEnumForm(objJson, key, index){	
	//console.log(document.getElementsByClassName("Enumeration")[key]);
	var enums = document.getElementsByClassName("Enumeration");
	var enumType = enums[index]; 
	enumType.getElementsByClassName("EnumerationName")[0].value = objJson.enumerations[key].name;
	var index = 0;
	for(var k in objJson.enumerations[key].literals){
		var literalBtn = enumType.getElementsByClassName("addLiteralBtn")[0];
		literalBtn.parentNode.insertBefore(newElementLiteral(), literalBtn);
	    var literalElmts = enumType.getElementsByClassName("LiteralName");
	    literalElmts[index].value = objJson.enumerations[key].literals[k];
		index++;
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
    
    var enums = document.getElementsByClassName("Enumeration");
	var initialSizePr = enums.length;
    for (i = 0; i < initialSizePr; i++) {
    	enums[0].parentNode.removeChild(enums[0]);
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
    var enumerations;
    var enumeration;
    var literals;
    var literal;
    var i;
    var j

    machine = {
        variables: [],
        properties: [],
        events: [],
        enumerations: []
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
        if(variable.name !== "") { //TODO: replace by regex
            machine.variables.push(variable);
        }
    }

    properties = document.getElementsByClassName("Property");
    for (i = 0; i < properties.length; i++) {
        property = {};
        property.expression = properties[i].getElementsByClassName("PropertyExpression")[0].value;
        property.category = properties[i].getElementsByClassName("PropertyCategory")[0].value;
        if(property.expression !== "") { //TODO: replace by regex
            machine.properties.push(property);
        }
    }
    
    events = document.getElementsByClassName("Event");
    for (i = 0; i < events.length; i++) {
        event = {};
        event.name = events[i].getElementsByClassName("EventName")[0].value;
        event.expression = events[i].getElementsByClassName("EventExpression")[0].value;
        event.type = events[i].getElementsByClassName("EventType")[0].value;
        machine.events.push(event);
    }

    enumerations = document.getElementsByClassName("Enumeration");
    for (i = 0; i < enumerations.length; i++) {
        enumeration = {};
        enumeration.name = enumerations[i].getElementsByClassName("EnumerationName")[0].value;
        enumeration.literals = [];
        literals = enumerations[i].getElementsByClassName("LiteralName");
        for (j = 0; j < literals.length; j++) {
            if(literals[j].value !== "") { //TODO: replace by regex
                enumeration.literals.push(literals[j].value);
            }
        }
        if(enumeration.name !== "") { //TODO: replace by regex
            machine.enumerations.push(enumeration);
        }
    }

    console.log(JSON.stringify(machine));

    return JSON.stringify(machine);
}


/**
 * Selectionne tout le code B de la machine pour la mettre plus facilement sur Atelier B.
 */
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
