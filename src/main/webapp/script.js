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
function newElementProperty() {
    return newElement("FIELDSET", {
        class: "Property form-inline"
    }, [
        newElement("BUTTON", {
            type: "button",
            class: "col-sm-1 btn btn-danger",
            onclick: "this.parentNode.parentNode.removeChild(this.parentNode)"
        }, [document.createTextNode("Remove")]),
        newElement("INPUT", {
            type: "text",
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
    }, [document.createTextNode("Remove variable")]));

    variable.appendChild(newElement("INPUT", {
        type: "text",
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
        class: "VariableCategory col-sm-1"
    }, [
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
        class: "VariableLowerBound col-sm-2"
    }, []));

    variable.appendChild(newElement("INPUT", {
        type: "number",
        class: "VariableUpperBound col-sm-2"
    }, []));

    variable.appendChild(newElement("INPUT", {
        type: "text",
        class: "VariableDefaultValue col-sm-2"
    }, []));

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

/**
 *
 */
function generateMachine() {
    var xhttp;

    console.log(machineToJSON());

    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            console.log(xhttp.response);
        }
    };
    xhttp.open("POST", "/dataServlet", true);
    xhttp.responseType = "text";
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("data=" + encodeURIComponent(machineToJSON()));
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
        properties: []
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

    console.log(machine);

    return JSON.stringify(machine);
}
