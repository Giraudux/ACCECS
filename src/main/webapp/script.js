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
        class: "Property"
    }, [
        newElement("BUTTON", {
            type: "button",
            onclick: "this.parentNode.parentNode.removeChild(this.parentNode)"
        }, [document.createTextNode("Remove")]),
        newElement("LABEL", {}, [document.createTextNode("Expression:")]),
        newElement("INPUT", {
            type: "text",
            class: "PropertyExpression"
        }, []),
        newElement("LABEL", {}, [document.createTextNode("Category:")]),
        newElement("SELECT", {
            class: "PropertyCategory"
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
        class: "Variable"
    }, []);

    variable.appendChild(newElement("BUTTON", {
        type: "button",
        onclick: "this.parentNode.parentNode.removeChild(this.parentNode)"
    }, [document.createTextNode("Remove")]));

    variable.appendChild(newElement("LABEL", {}, [document.createTextNode("Name:")]));
    variable.appendChild(newElement("INPUT", {
        type: "text",
        class: "VariableName"
    }, []));

    variable.appendChild(newElement("LABEL", {}, [document.createTextNode("Type:")]));
    variable.appendChild(newElement("SELECT", {
        class: "VariableType",
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

    variable.appendChild(newElement("LABEL", {}, [document.createTextNode("Category:")]));
    variable.appendChild(newElement("SELECT", {
        class: "VariableCategory"
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

    variable.appendChild(newElement("LABEL", {}, [document.createTextNode("Lower bound:")]));
    variable.appendChild(newElement("INPUT", {
        type: "number",
        class: "VariableLowerBound" /*, step: "any"*/
    }, []));

    variable.appendChild(newElement("LABEL", {}, [document.createTextNode("Upper bound:")]));
    variable.appendChild(newElement("INPUT", {
        type: "number",
        class: "VariableUpperBound" /*, step: "any"*/
    }, []));

    variable.appendChild(newElement("LABEL", {}, [document.createTextNode("Default value:")]));
    variable.appendChild(newElement("INPUT", {
        type: "text",
        class: "VariableDefaultValue"
    }, []));

    return variable;
}

/**
 *
 */
function updateVariable(variable) {
    var type;
    var disableBounds;
    var step = "any";

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
            step = "1";
        } else if (type == "float") {
            step = "any";
        }
        variable.appendChild(newElement("INPUT", {
            type: "number",
            class: "VariableDefaultValue",
            step: step
        }, []));
        variable.getElementsByClassName("VariableLowerBound")[0].setAttribute("step", step);
        variable.getElementsByClassName("VariableUpperBound")[0].setAttribute("step", step);
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
    xhttp.open("POST", "dataServlet", true);
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
