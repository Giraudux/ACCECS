MACHINE {{ name }}
{% if (variablesInput.size + variablesOutput.size + variablesControl.size + variablesInternal.size > 0) %}
VARIABLES
    // input variables
    {% for variable in variablesInput %}{{ variable }},
    {% endfor %}
    // output variables
    {% for variable in variablesOutput %}{{ variable }},
    {% endfor %}
    // control variables
    {% for variable in variablesControl %}{{ variable }},
    {% endfor %}
    // internal variables
    {% for variable in variablesInternal %}{{ variable }},
    {% endfor %}
{% endif %}
{% if (properties.size > 0) %}
PROPERTIES
    {% for property in properties %}{% if (loop.first) %}  {% else %}& {% endif %}{{ property }}
    {% endfor %}
{% endif %}
{% if (invariants.size > 0) %}
INVARIANTS
    {% for invariant in invariants %}{% if (loop.first) %}  {% else %}& {% endif %}{{ invariant }}
    {% endfor %}
{% endif %}
{% if (initialisations.size > 0) %}
INITIALISATION
    {% for initialisation in initialisations %}{% if (loop.first) %}   {% else %}|| {% endif %}{{ initialisation }}
    {% endfor %}
{% endif %}

{% if (variablesInput.size > 0 or variablesOutput.size > 0) %}
EVENTS
    // Input variables
    {% for variable in variablesInput %}
        sense_{{variable}} =
	SELECT
	    
	THEN
	    
	END{% if (loop.last == false and variablesOutput.size == 0) %} ; {% endif %}
    {% endfor %}

    // Output variables
    {% for variable in variablesOutput %}
        monitor_{{variable}} =
	SELECT
	    
	THEN
	    
	END{% if (loop.last == false) %} ; {% endif %} 
    {% endfor %}
{% endif %}
END
